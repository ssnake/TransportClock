package com.theoffice.transportclockapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.transportclock.TransportRoute;

import java.util.List;
import java.util.Vector;

/**
 * Created by snake on 27.12.13.
 */
public class RouteListAdapter extends ArrayAdapter<TransportRoute> {
    List<TransportRoute> mRouteList;

    public RouteListAdapter(Context context, int resource, List<TransportRoute> objects) {
        super(context, resource, objects);
        mRouteList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder holder;
        if (rowView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            rowView = View.inflate(this.getContext(), R.layout.route_row, null);
            holder = new ViewHolder();
            holder.routeName = (TextView) rowView.findViewById(R.id.routeName);
            rowView.setTag(holder);
        } else
          holder = (ViewHolder) rowView.getTag();
        holder.routeName.setText(""+position);
        return rowView;
    }
    static class ViewHolder{
        public TextView routeName;
    }
}
