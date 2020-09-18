package com.tobiasstrom.s331392mappe1comtobiasstrom;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
        if (currentApp.getRightAnwser().equals(currentApp.getYourAnwser())){
            viewHolder.tvQuestion.setText(currentApp.getQuestion());
            viewHolder.tvRigth.setText(currentApp.getYourAnwser());
            viewHolder.tvIcon.setImageResource(R.drawable.check);
            viewHolder.txtLvRight.setText("");
            viewHolder.tvNumber.setText("");
        }else {
            viewHolder.tvQuestion.setText(currentApp.getQuestion());
            viewHolder.tvRigth.setText(currentApp.getYourAnwser());
            viewHolder.tvIcon.setImageResource(R.drawable.cross);
            //viewHolder.txtLvRight.setText(String.valueOf(R.string.right));
            viewHolder.tvNumber.setText(currentApp.getRightAnwser());
        }

        //Statistics currentApp = applications.get(position);
        //viewHolder.tvRigth.setText(currentApp.getRightAnwser()+"");
        //viewHolder.tvNumber.setText(currentApp.getNumberQuestions()+"");
        return convertView;
    }
    private class ViewHolder{
        final TextView tvQuestion;
        final TextView tvRigth;
        final TextView tvNumber;
        final ImageView tvIcon;
        final TextView txtLvRight;

        public ViewHolder(View v) {
            this.tvQuestion = v.findViewById(R.id.QuestionName);
            this.tvRigth = v.findViewById(R.id.tvRigth);
            this.tvNumber = v.findViewById(R.id.tvNumber);
            this.tvIcon = v.findViewById(R.id.tvIcon);
            this.txtLvRight = v.findViewById(R.id.txtLvRight);
        }
    }

}
