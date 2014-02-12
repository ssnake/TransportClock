package com.snake.transportclockapp;

import java.util.*;

import android.app.*;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.*;

import android.widget.*;
import com.snake.mapviewproxy.MapViewProxy;
import com.snake.mapviewproxy.MapViewProxyOSM;
import com.transportclock.*;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MainActivity extends Activity implements  View.OnClickListener, AsyncClientTask.TaskUpdateListener, AppHelper.OnRouteSelectedListiner {
    MapView mMapView;
    MapViewProxy mvProxy;
    TextView mRouteName;
    Button mButton;
    TransportClient mClient;
    List<TransportRoute> mRouteList;
    List<TransportCar> mCarList;
    RouteSelectedListener mRouteSelectedListiner;
    UISettings mSettings;
    RoutesRender mRouteRender;
    PopupWindow mPopupRouteWindow;
    CarsRender mCarRender;
    Timer mCarUpdateTimer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.fragment_map);
//        mMapView = (MapView) findViewById(R.id.mapview);
 //       mRouteName = (TextView) findViewById(R.id.routeName);

       createActivityLayout();

        mClient = new TransportClientLocal(this);
        mRouteList = new Vector<TransportRoute>();
        mCarList = new Vector<TransportCar>();

        mSettings = new UISettings();


        mvProxy = new MapViewProxyOSM(mMapView, this);
        mvProxy.setBuiltInZoomControls(true);
        mvProxy.setMultiTouchControls(true);
        mvProxy.setZoom(15);


        mRouteRender = new RoutesRender(mvProxy);
        mCarRender = new CarsRender(mvProxy);
        mRouteSelectedListiner = new RouteSelectedListener(this);
        mPopupRouteWindow = new PopupWindow(this);

        mPopupRouteWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupRouteWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);



        mvProxy.setCenter(new GeoPoint(50.90633, 34.81854));



        bindUI();

        loadRoutes();


    }
    void createActivityLayout() {
        RelativeLayout layout = new RelativeLayout(this);

        mMapView = new MapView(this, 0);
        MapView.LayoutParams layoutParams =
            new MapView.LayoutParams(
                    MapView.LayoutParams.WRAP_CONTENT,
                    MapView.LayoutParams.WRAP_CONTENT,
                    null, 0, 0, 0

            );
        mMapView.setLayoutParams(layoutParams);
        mMapView.setId(R.id.mapview);
        mMapView.setTileSource(TileSourceFactory.MAPNIK);
        layout.addView(mMapView, ViewGroup.LayoutParams.MATCH_PARENT,  ViewGroup.LayoutParams.MATCH_PARENT);

        mButton = new Button(this);
        mButton.setId(R.id.btnChooseRoutes);
        mButton.setText(R.string.Routes);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_LEFT, R.id.mapview);
        //params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mButton.setLayoutParams(params);
        layout.addView(mButton);


        mRouteName = new TextView(this);
        mRouteName.setId(R.id.routeName);
        mRouteName.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mRouteName.setTextColor(Color.BLACK);
        mRouteName.setTextSize(16);
        mRouteName.setGravity(Gravity.CENTER);
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params2.addRule(RelativeLayout.RIGHT_OF, R.id.btnChooseRoutes);
        params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mRouteName.setLayoutParams(params2);


        layout.addView(mRouteName);

        /*LayoutInflater inflater = (LayoutInflater)this.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.main_controlls, layout);


        mRouteName = (TextView) layout.findViewById(R.id.routeName);
        mButton = (Button) layout.findViewById(R.id.btnChooseRoutes);
        mMapView.addView(layout, 200, 200);
        setContentView(mMapView);
        */


        setContentView(layout);
        mMapView.invalidate();
        layout.invalidate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadSettings();
        int id = mSettings.getCurrentRouteID();
        if (id != TransportConst.NOID) {
            TransportRoute r = TransportRouteList.findByID(id, mRouteList);
            if (r != null)
                onRouteSelected(r, true);
        };


    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onResume() {
        super.onResume();
        enableTimer(mSettings.getTimerEnabled());
    }

    @Override
    protected void onPause() {
        super.onPause();
        enableTimer(mSettings.getTimerEnabled());
        saveSetting();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    void loadSettings() {
        mSettings.load(getPreferences(Context.MODE_PRIVATE));
    }
    void saveSetting() {
        mSettings.save(getPreferences(Context.MODE_PRIVATE));
    }


    void loadRoutes() {
        ClientTask task = ClientTask.MakeLoadRouteNames(mClient);
        task.run();
        onTaskUpdate(task);
        //new AsyncClientTask(this).execute(ClientTask.MakeLoadRouteNames(mClient));

    }



    void bindUI() {

            mButton.setOnClickListener(this);
    }


     public void onChooseRouteClick(View view) {

            if (mPopupRouteWindow.isShowing())
            {
                mPopupRouteWindow.dismiss();
                return;
            }

            DisplayMetrics displaymetrics = new DisplayMetrics();
            this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int maxWidth = displaymetrics.widthPixels;

            ListView lv = new ListView(this);
            lv.setItemsCanFocus(false);
            lv.setChoiceMode(lv.CHOICE_MODE_SINGLE);
            lv.setAdapter(new RouteListAdapter(this, mRouteList, mSettings, mRouteSelectedListiner));
            if (mRouteList.size() > 0) {

                mPopupRouteWindow.setContentView(lv);
                mPopupRouteWindow.showAsDropDown(this.findViewById(R.id.btnChooseRoutes));
            };
     }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnChooseRoutes:
                onChooseRouteClick(v);
                break;
        }
    }
    private void showRoutePath(TransportRoute route, boolean isVisiable) {

        mRouteRender.showRoute(route, isVisiable);



        if (isVisiable) {
            if (route.size() ==0)
                Toast.makeText(this, R.string.routeHasNoPath, Toast.LENGTH_SHORT).show();
            else
                enableTimer(true);

            //Sumy center
            GeoPoint center = new GeoPoint(50.912775, 34.799141);
            if (route.size() > 0) {
                mvProxy.setScrollableAreaLimit(MapViewProxy.BoundingBox.MakeBoundingBox(route));
                center = new GeoPoint(route.getCenter().getLat(), route.getCenter().getLng());
            }
            mvProxy.setCenter(center);
            mvProxy.setZoom(13);
        }
    }
    private void enableTimer(Boolean enabled) {
        mSettings.setTimerEnabled(enabled);
        if (enabled) {
            final AsyncClientTask.TaskUpdateListener listener = this;
            mCarUpdateTimer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new AsyncClientTask(listener, false).execute(ClientTask.MakeLoadRouteCars(mClient, mSettings.getCurrentRouteID()));
                        }
                    });

                }
            };
            mCarUpdateTimer.scheduleAtFixedRate(task, 0, 10000);
        } else {
            if (mCarUpdateTimer != null)
                mCarUpdateTimer.cancel();
            mCarUpdateTimer = null;
        }


    }
    void glueCarsToRoute() {
        for(TransportCar car: mCarList) {
            RoutePoint carPoint = TransportRouteList.findNearestPoint(car.getPoint(), mRouteList, 50);
            if (carPoint != null)
                car.setPoint(carPoint);
        }
    }

    @Override
    public void onTaskUpdate(ClientTask task) {
        if (task instanceof ClientTask.LoadRouteNames) {
            ClientTask.GetTaskRouteList(task, mRouteList);
        }

        if (task instanceof ClientTask.LoadRouteCars) {
          ClientTask.GetTaskCarList(task, mCarList);
          mCarRender.clear();
          if (mCarUpdateTimer != null){
              glueCarsToRoute();
              mCarRender.showCars(mCarList);
          }
        }

        if (task instanceof ClientTask.LoadRouteDetails) {
            new AsyncClientTask(this).execute(ClientTask.MakeLoadRoutePath(mClient, task.mRoute));
        }

        if (task instanceof ClientTask.LoadRoutePath) {
            showRoutePath(task.mRoute, true);
        }


    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onRouteSelected(TransportRoute route, boolean isVisiable) {
        mSettings.setVisiable(route, isVisiable);

        if (isVisiable) {
            mSettings.setCurrentRouteID(route.getId());
            mRouteName.setText(route.getName());
            enableTimer(false);
            mCarRender.clear();
            new AsyncClientTask(this).execute(ClientTask.MakeLoadRouteDetails(mClient, route));

        } else
            showRoutePath(route, false);
    }

    @Override
    public TransportRoute getLastSelectedRoute() {
        return TransportRouteList.findByID(mSettings.getCurrentRouteID(), mRouteList);
    }


    public class RouteSelectedListener implements View.OnClickListener
        {
            AppHelper.OnRouteSelectedListiner mListiner;




            RouteSelectedListener(AppHelper.OnRouteSelectedListiner listener) {
                mListiner = listener;

            }

            @Override
            public void onClick(View v) {
                RadioButton rb = (RadioButton) v;
                TransportRoute lastRoute = mListiner.getLastSelectedRoute();
                TransportRoute route = (TransportRoute) v.getTag();
                if (lastRoute != route && lastRoute != null)
                    mListiner.onRouteSelected(lastRoute, false);

                mListiner.onRouteSelected(route, rb.isChecked());

                mPopupRouteWindow.dismiss();


            }


        }



}
