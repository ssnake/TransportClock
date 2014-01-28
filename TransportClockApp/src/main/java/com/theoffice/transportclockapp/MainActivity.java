package com.theoffice.transportclockapp;

import java.util.*;

import android.app.*;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.*;

import android.widget.*;
import com.snake.mapviewproxy.MapViewProxy;
import com.snake.mapviewproxy.MapViewProxyOSM;
import com.transportclock.TransportCar;
import com.transportclock.TransportClient;
import com.transportclock.TransportRoute;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MainActivity extends Activity implements  View.OnClickListener, AsyncClientTask.TaskUpdateListener, AppHelper.OnRouteSelectedListiner {
    MapView mMapView;
    MapViewProxy mvProxy;
    TransportClient mClient;
    List<TransportRoute> mRouteList;
    List<TransportCar> mCarList;
    RouteSelectedListener mRouteSelectedListiner;
    UISettings mSettings;
    RoutesRender mRouteRender;
    PopupWindow mPopupRouteWindow;
    CarsRender mCarRender;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_map);
        mClient = new TransportClientLocal(this);
        mRouteList = new Vector<TransportRoute>();
        mCarList = new Vector<TransportCar>();

        mSettings = new UISettings();

        mMapView = (MapView) findViewById(R.id.mapview);
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

    @Override
    protected void onStart() {
        super.onStart();

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





    void loadRoutes() {
            new AsyncClientTask(this).execute(ClientTask.MakeLoadRouteNames(mClient));

    }



    void bindUI() {
            Button b = (Button) findViewById(R.id.btnChooseRoutes);
            b.setOnClickListener(this);
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
            lv.setAdapter(new RouteListAdapter(this, mRouteList, mSettings, mRouteSelectedListiner));
            if (mRouteList.size() > 0) {

                mPopupRouteWindow.setContentView(lv);
                mPopupRouteWindow.showAsDropDown(this.findViewById(R.id.btnChooseRoutes));
            } else
                Toast.makeText(this, R.string.waitingForRoutes, Toast.LENGTH_SHORT ).show();
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

            mvProxy.setScrollableAreaLimit(MapViewProxy.BoundingBox.MakeBoundingBox(route));
            mvProxy.setZoom(13);
        }
    }

    @Override
    public void onTaskUpdate(ClientTask task) {
        if (task instanceof ClientTask.LoadRouteNames) {
            ClientTask.GetTaskRouteList(task, mRouteList);

        }
        if (task instanceof ClientTask.LoadRouteCars) {
          mCarList.clear();
          ClientTask.GetTaskCarList(task, mCarList);
          mCarRender.clear();
          mCarRender.showCars(mCarList);
        }
        if (task instanceof ClientTask.LoadRouteDetails) {
            new AsyncClientTask(this).execute(ClientTask.MakeLoadRoutePath(mClient, task.mRoute));

        }
        if (task instanceof ClientTask.LoadRoutePath) {
            showRoutePath(task.mRoute, true);


        }


    }

    @Override
    public void onRouteSelected(TransportRoute route, boolean isVisiable) {
        mSettings.setVisiable(route, isVisiable);

        if (isVisiable) {
            new AsyncClientTask(this).execute(ClientTask.MakeLoadRouteDetails(mClient, route));
            new AsyncClientTask(this).execute(ClientTask.MakeLoadRouteCars(mClient, route.getId()));
        } else
            showRoutePath(route, isVisiable);
    }




    public class RouteSelectedListener implements View.OnClickListener
        {
            AppHelper.OnRouteSelectedListiner mListiner;

            View mCheckedView = null;


            RouteSelectedListener(AppHelper.OnRouteSelectedListiner listener) {
                mListiner = listener;

            }

            @Override
            public void onClick(View v) {
                RadioButton rb = (RadioButton) v;
                if (mCheckedView != null && mCheckedView != v) {
                    ((RadioButton) mCheckedView).setChecked(false);
                    TransportRoute r = (TransportRoute) mCheckedView.getTag();
                    mListiner.onRouteSelected(r, false);

                }
                mCheckedView = v;
                TransportRoute r = (TransportRoute) v.getTag();

                mListiner.onRouteSelected(r, rb.isChecked());

                mPopupRouteWindow.dismiss();


            }


        }



}
