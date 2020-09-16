package com.tobiasstrom.s331392mappe1comtobiasstrom;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.List;

public class StratisticsAdapter extends ArrayAdapter {
    private static final String TAG = "StratisticsAdapter";
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<Statistics> applications;


    public StratisticsAdapter(@NonNull Context context, int resource, List<Statistics> application) {
        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.applications = application;
    }
    @Override
    public int getCount() {
        return applications.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = layoutInflater.inflate(layoutResource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Statistics currentApp = applications.get(position);
        viewHolder.tvRigth.setText(currentApp.getRightAnwser()+"");
        viewHolder.tvNumber.setText(currentApp.getNumberQuestions()+"");
        return convertView;
    }
    private class ViewHolder{
        final TextView tvRigth;
        final TextView tvNumber;

        public ViewHolder(View v) {
            this.tvRigth = v.findViewById(R.id.tvRigth);
            this.tvNumber = v.findViewById(R.id.tvNumber);
        }
    }

}
