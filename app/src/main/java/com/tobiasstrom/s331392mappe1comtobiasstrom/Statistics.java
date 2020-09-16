package com.tobiasstrom.s331392mappe1comtobiasstrom;

import android.util.Log;

import java.util.ArrayList;

public class Statistics {
    private int rightAnwser;
    private int numberQuestions;
    private static final String TAG = "Statistics";

    public Statistics(int rightAnwser, int numberQuestions) {

        this.rightAnwser = rightAnwser;
        this.numberQuestions = numberQuestions;
    }
    public void addStatistics(Statistics statistic){
        StatisticsBrain.statistics.add(statistic);
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "rightAnwser=" + rightAnwser +
                ", numberQuestions=" + numberQuestions +
                '}';
    }

    public int getRightAnwser() {
        return rightAnwser;
    }

    public void setRightAnwser(int rightAnwser) {
        this.rightAnwser = rightAnwser;
    }

    public int getNumberQuestions() {
        return numberQuestions;
    }

    public void setNumberQuestions(int numberQuestions) {
        this.numberQuestions = numberQuestions;
    }
    public static int prosent(){

        int numberRigth = 0;
        int numberQuestion = 0;
        int prosent = 0;
        if (!StatisticsBrain.statistics.isEmpty()){
            for (Statistics statistics : StatisticsBrain.statistics){
                numberRigth += statistics.getRightAnwser();
                numberQuestion += statistics.getNumberQuestions();

            }

            prosent = ((numberRigth/numberQuestion)*100);
            Log.e(TAG, "Riktige: " + numberRigth + " Totalt: " + numberQuestion + " Prosent: " + prosent );
            return prosent;
        }
        System.out.println("Det er ingen spørmål");
        return prosent;
    }
}
