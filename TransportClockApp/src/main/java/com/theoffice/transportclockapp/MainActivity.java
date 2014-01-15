package com.theoffice.transportclockapp;

import java.util.*;

import android.app.*;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.*;
import com.transportclock.RoutePoint;
import com.transportclock.TransportCar;
import com.transportclock.TransportClient;
import com.transportclock.TransportRoute;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.OverlayItem;

public class MainActivity extends FragmentActivity {
    public interface OnRouteSelectedListiner {
        public void onRouteSelected(TransportRoute route, boolean isVisiable);
    }
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.fragment_container) != null)
            getFragmentManager().beginTransaction().add(R.id.fragment_container, new MapFragment()).commit();

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


    public class MapFragment extends Fragment implements  View.OnClickListener, AsyncClientTask.TaskUpdateListener, OnRouteSelectedListiner{
        MapView mMapView;
        MapViewProxy mvProxy;
        TransportClient mClient;
        List<TransportRoute> mRouteList;
        List<TransportCar> mCarList;
        RouteSelectedListener mRouteSelectedListiner;
        UISettings mSettings;
        RoutesRender mRouteRender;
        ListPopupWindow mPopupRouteWindow;
        CarsRender mCarRender;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View mapView = inflater.inflate(R.layout.fragment_map, null, false);

            mMapView = (MapView) mapView.findViewById(R.id.mapview);
            mvProxy = new MapViewProxyOSM(mMapView, this.getActivity());
            mvProxy.setBuiltInZoomControls(true);
            mvProxy.setMultiTouchControls(true);
            mvProxy.setZoom(15);

            mRouteRender = new RoutesRender(mvProxy);
            mCarRender = new CarsRender(mvProxy);
            mRouteSelectedListiner = new RouteSelectedListener(this);

            mPopupRouteWindow = new ListPopupWindow(this.getActivity());
            mPopupRouteWindow.setAnchorView(mapView.findViewById(R.id.btnChooseRoutes));


            mvProxy.setCenter(new GeoPoint(50.90633, 34.81854));

            bindUI(mapView);
            //ListView lw = (ListView) mapView.findViewById(R.id.listView);
            //lw.setAdapter(new RouteListAdapter(this.getActivity(), 0,mRouteList));
            return mapView;
        }

        void addRouteToMap(TransportRoute route)
        {
            MapViewMarksOverlayProxy route_overlay = mvProxy.addMarksOverlay(R.drawable.ic_launcher);
            MapViewPathOverlayProxy route_overlay2 = mvProxy.addPathOverlay(Color.RED);
            for(RoutePoint p: route)
            {
                route_overlay2.addPoint(new GeoPoint(p.getLat(), p.getLng()));
            }

        }
        void loadRoutes()
        {
            new AsyncClientTask(this).execute(ClientTask.LoadAllRoutes(mClient));

        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mClient = new TransportClientLocal(this.getActivity());
            mRouteList = new Vector<TransportRoute>();
            mCarList = new Vector<TransportCar>();

            mSettings = new UISettings(new UISettingsObserver(this));

            loadRoutes();
        }

        void bindUI(View root){
            Button b = (Button) root.findViewById(R.id.btnChooseRoutes);
            b.setOnClickListener(this);
        }


        public void onChooseRouteClick(View view)
        {
            //Toast.makeText(this.getActivity(),"click", Toast.LENGTH_SHORT).show();


            DisplayMetrics displaymetrics = new DisplayMetrics();
            this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int maxWidth = displaymetrics.widthPixels;


            mPopupRouteWindow.setWidth(maxWidth);
            mPopupRouteWindow.setAdapter(new RouteListAdapter(this.getActivity(), mRouteList, mSettings, mRouteSelectedListiner));
            mPopupRouteWindow.show();
            mPopupRouteWindow.getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btnChooseRoutes:
                    onChooseRouteClick(v);
                    break;
            }
        }


        @Override
        public void onTaskUpdate(ClientTask task) {
            ClientTask.GetTaskRouteList(task, mRouteList);
            if (task instanceof ClientTask.LoadRouteCars) {
              mCarList.clear();
              ClientTask.GetTaskCarList(task, mCarList);
              mCarRender.clear();
              mCarRender.showCars(mCarList);
            }

        }

        @Override
        public void onRouteSelected(TransportRoute route, boolean isVisiable) {
            mSettings.setVisiable(route, isVisiable);
            mRouteRender.showRoute(route, isVisiable);
            if (isVisiable)
                new AsyncClientTask(this).execute(ClientTask.LoadRouteCars(mClient, route.getId()));

        }


        class RouteSelectedListener implements View.OnClickListener
        {
            OnRouteSelectedListiner mListiner;

            View mCheckedView = null;


            RouteSelectedListener(OnRouteSelectedListiner listener) {
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
        class UISettingsObserver implements Observer{
            MapFragment mMapFragmen;
            UISettingsObserver(MapFragment mapFragment) {
                mMapFragmen = mapFragment;
            }

            @Override
            public void update(Observable observable, Object data) {




            }
        }
    }

}
