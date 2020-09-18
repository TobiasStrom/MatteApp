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

//Trenger denne klassen får å kunne legge inn den informsjonen som vi trenger i listview
//På den måten jeg ønsker
public class StratisticsAdapter extends ArrayAdapter {
    //opprette de variablene vi trenger
    private static final String TAG = "StratisticsAdapter";
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<Statistics> applications;

    //Konstruktør
    public StratisticsAdapter(@NonNull Context context, int resource, List<Statistics> application) {
        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.applications = application;
    }
    //Må teller hvor mange objekter det er får å kunne opprette listview
    @Override
    public int getCount() {
        return applications.size();
    }

    //Henter view vi trenger få å kunne bruke riktg viewlist
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
        //Setter infoen jeg øsker å vise i listview
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
            viewHolder.tvNumber.setText(currentApp.getRightAnwser());
        }

        return convertView;
    }
    //Trenger denne får å hente ut alle ViewIndexene som vi trenger få å setet inn.
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
