package com.tobiasstrom.s331392mappe1comtobiasstrom;

import java.util.ArrayList;

public class Statistics {
    private int rightAnwser;
    private int numberQuestions;

    ArrayList<Statistics> statistics = new ArrayList<>();

    public Statistics(int rightAnwser, int numberQuestions) {
        this.rightAnwser = rightAnwser;
        this.numberQuestions = numberQuestions;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "rightAnwser=" + rightAnwser +
                ", numberQuestions=" + numberQuestions +
                '}';
    }

}
