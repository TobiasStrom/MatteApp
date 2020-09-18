package com.tobiasstrom.s331392mappe1comtobiasstrom;

public class Statistics {
    private String yourAnwser;
    private String rightAnwser;
    private String question;


    public Statistics(String yourAnwser, String rightAnwser, String question) {
        this.yourAnwser = yourAnwser;
        this.rightAnwser = rightAnwser;
        this.question = question;
    }

    public String getYourAnwser() {
        return yourAnwser;
    }

    public void setYourAnwser(String yourAnwser) {
        this.yourAnwser = yourAnwser;
    }

    public String getRightAnwser() {
        return rightAnwser;
    }

    public void setRightAnwser(String rightAnwser) {
        this.rightAnwser = rightAnwser;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return '\n'+
                 "question=: " + question + " yourAnwser: " + yourAnwser + " rightAnwser: " + rightAnwser;
    }
}
