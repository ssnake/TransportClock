package com.theoffice.transportclockapp;

import java.io.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import android.app.*;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.*;
import com.transportclock.RouteGPSImporter;
import com.transportclock.RoutePoint;
import com.transportclock.TransportRoute;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.OverlayItem;

public class MainActivity extends FragmentActivity {

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


    public class MapFragment extends Fragment implements  View.OnClickListener{
        MapView mMapView;
        MapViewProxy mvProxy;
        TransportClient mClient;
        List<TransportRoute> mRouteList;
        RouteSelectedListener mRouteSelectedListiner;
        UISettings mSettings;
        RoutesRender mRouteRender;



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
            mClient.loadAllRoutes(mRouteList);

        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mClient = new TransportClientLocal(this.getActivity());
            mRouteList = new Vector<TransportRoute>();

            mSettings = new UISettings(new UISettingsObserver(this));

            loadRoutes();
        }

        void bindUI(View root){
            Button b = (Button) root.findViewById(R.id.btnChooseRoutes);
            b.setOnClickListener(this);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View mapView = inflater.inflate(R.layout.fragment_map, null, false);

            mMapView = (MapView) mapView.findViewById(R.id.mapview);
            mvProxy = new MapViewProxyOSM(mMapView, this.getActivity());
            mvProxy.setBuiltInZoomControls(true);
            mvProxy.setMultiTouchControls(true);
            mvProxy.setZoom(15);

            mRouteRender = new RoutesRender(mvProxy);
            mRouteSelectedListiner = new RouteSelectedListener(mSettings, mRouteRender);



            MapViewMarksOverlayProxy marks = mvProxy.addMarksOverlay(R.drawable.ic_launcher);
            marks.setOnMarkClickListiner(new MapViewMarksOverlayProxy.OnMarkClickListiner() {
                @Override
                public void onClick(OverlayItem item) {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
                    dlg.setTitle(item.getTitle());
                    dlg.setMessage(item.getSnippet());
                    dlg.show();

                }
            });

            mvProxy.setCenter(new GeoPoint(50.90633, 34.81854));

            bindUI(mapView);
            //ListView lw = (ListView) mapView.findViewById(R.id.listView);
            //lw.setAdapter(new RouteListAdapter(this.getActivity(), 0,mRouteList));
            return mapView;
        }
        public void onChooseRouteClick(View view)
        {
            //Toast.makeText(this.getActivity(),"click", Toast.LENGTH_SHORT).show();
            ListPopupWindow pop = new ListPopupWindow(this.getActivity());
            pop.setAnchorView(view);
            pop.setAdapter(new RouteListAdapter(this.getActivity(), mRouteList, mSettings, mRouteSelectedListiner));
            pop.show();
        }

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btnChooseRoutes:
                    onChooseRouteClick(v);
                    break;
            }
        }
        class RouteSelectedListener implements View.OnClickListener
        {
            UISettings mSettings;
            RoutesRender mRouteRender;
            RouteSelectedListener(UISettings settings, RoutesRender render) {
                mSettings = settings;
                mRouteRender = render;
            }

            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                TransportRoute r = (TransportRoute) v.getTag();
                mSettings.setVisiable(r, cb.isChecked());
                mRouteRender.showRoute(r, cb.isChecked());


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
