package com.theoffice.transportclockapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.transportclock.TransportRoute;

import java.util.List;
import java.util.Vector;

/**
 * Created by snake on 27.12.13.
 */
public class RouteListAdapter extends ArrayAdapter<TransportRoute> {
    List<TransportRoute> mRouteList;
    View.OnClickListener mClickListener;
    UISettings mSettings;

    public RouteListAdapter(Context context, List<TransportRoute> objects, UISettings settings, View.OnClickListener clickListener) {
        super(context, 0 , objects);
        mRouteList = objects;
        mClickListener = clickListener;
        mSettings = settings;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder holder;
        TransportRoute route = mRouteList.get(position);
        if (rowView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            rowView = View.inflate(this.getContext(), R.layout.route_row, null);
            holder = new ViewHolder();
            holder.routeName = (RadioButton) rowView.findViewById(R.id.radioButton);
            holder.routeName.setOnClickListener(mClickListener);
            rowView.setTag(holder);
        } else
          holder = (ViewHolder) rowView.getTag();
        holder.routeName.setText(mRouteList.get(position).getName());
        holder.routeName.setChecked(mSettings.IsVisiable(route));
        //associate route with this checkbox
        holder.routeName.setTag(route);
        return rowView;
    }



    static class ViewHolder{
        public RadioButton routeName;
    }
}
