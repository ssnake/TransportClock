package com.theoffice.transportclockapp;

import java.io.*;

import android.app.*;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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


    public class MapFragment extends Fragment{
        MapView mMapView;
        MapViewProxy mvProxy;

        String readRawJSON(int resource_id)
        {
            InputStream is = getResources().openRawResource(resource_id);
            Writer w = new StringWriter();
            char[] buffer = new char[1024];
            try {
                Reader r = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while((n= r.read(buffer)) != -1)
                {
                    w.write(buffer, 0, n);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return w.toString();

        }
        void addRoute()
        {
            String json = readRawJSON(R.raw.route1);
            TransportRoute r = RouteGPSImporter.load(json, false);
            addRouteToMap(r);


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
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View mapView = inflater.inflate(R.layout.fragment_map, null, false);

            mMapView = (MapView) mapView.findViewById(R.id.mapview);
            mvProxy = new MapViewProxyOSM(mMapView, this.getActivity());
            mvProxy.setBuiltInZoomControls(true);
            mvProxy.setMultiTouchControls(true);
            mvProxy.setZoom(15);
            mvProxy.showCompassOverlay(true);
            TransportRoute tr = new TransportRoute();

            MapViewMarksOverlayProxy marks = mvProxy.addMarksOverlay(R.drawable.ic_launcher);
            marks.setOnMarkClickListiner(new MapViewMarksOverlayProxy.OnMarkClickListiner()
            {
                @Override
                public void onClick(OverlayItem item) {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
                    dlg.setTitle(item.getTitle());
                    dlg.setMessage(item.getSnippet());
                    dlg.show();

                }
            });
            marks.addPoint(new GeoPoint(0,0), "test1","ttt");

            mvProxy.setCenter(new GeoPoint(50.90633, 34.81854));
            addRoute();
            return mapView;
        }
    }

}
