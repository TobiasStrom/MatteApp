package com.tobiasstrom.s331392mappe1comtobiasstrom;

import java.util.ArrayList;

public class Statistics {
    private String yourAnwser;
    private String rightAnwser;
    private String question;


    public Statistics(String yourAnwser, String rightAnwser, String question) {
        this.yourAnwser = yourAnwser;
        this.rightAnwser = rightAnwser;
        this.question = question;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "yourAnwser=" + yourAnwser +
                ", rightAnwser=" + rightAnwser +
                ", question='" + question + '\'' +
                '}';
    }
}
