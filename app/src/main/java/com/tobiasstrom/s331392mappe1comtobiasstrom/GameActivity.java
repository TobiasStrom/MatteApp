package com.tobiasstrom.s331392mappe1comtobiasstrom;


import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class GameActivity extends AppCompatActivity implements MyDialog.DialogClickListener {
    private static final String TAG = "GameActivity";
    Button btn_game_0 ;
    Button btn_game_1 ;
    Button btn_game_2;
    Button btn_game_3;
    Button btn_game_4;
    Button btn_game_5;
    Button btn_game_6;
    Button btn_game_7;
    Button btn_game_8;
    Button btn_game_9;
    ImageButton btn_remove;
    ImageButton btn_game_submit;


    private EditText newNumber;
    private String[] questions;
    private String[] answers;
    private ArrayList<Integer> selectedQuestions = new ArrayList();
    private static int numberOfQuestions = 5;
    private int whichQuestion = 0;
    TextView txt_game_question;
    TextView txt_right_awser;
    TextView txt_wrong_awser;
    private  int questionNumber = 0;
    private int rightAwser = 0;
    private int wrongAwser = numberOfQuestions;
    Dialog myDialog;
    ImageButton btnClose;
    ImageButton btn_restart;
    TextView txt_result_of, txt_result;


    private static final String STATE_NUMBEROFQUESTION = "NumberOfQuestion";
    private static final String STATE_WHICHQUESTION = "WhitsQuestion";
    private static final String STATE_SELECTEDQUESTION = "SelectedQuestion";
    private static final String STATE_QUESTIONNUMBER = "QuestionNumber";
    private static final String STATE_RIGHTAWSER = "RightAwser";
    private static final String STATE_WRONGAWSER = "WrongAwser";




    public void settland(String landskode) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration cf = res.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            cf.setLocale(new Locale(landskode));
        }
        res.updateConfiguration(cf,dm);
        getSharedPreferences("LANGUAGE",MODE_PRIVATE).edit().putString("landskode",landskode).apply();
    }

    public static int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public static void setNumberOfQuestions(int numberOfQuestion) {
        numberOfQuestions = numberOfQuestion;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        settland(getSharedPreferences("LANGUAGE",MODE_PRIVATE).getString("landskode",""));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        newNumber = (EditText) findViewById(R.id.inp_newNumber);

        txt_game_question = (TextView)findViewById(R.id.txt_game_question);
        txt_right_awser = (TextView)findViewById(R.id.txt_right_awser);
        txt_wrong_awser = (TextView)findViewById(R.id.txt_wrong_awser);
        findViewBtn();


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                newNumber.append(b.getText().toString());
            }
        };
        btn_game_0.setOnClickListener(listener);
        btn_game_1.setOnClickListener(listener);
        btn_game_2.setOnClickListener(listener);
        btn_game_3.setOnClickListener(listener);
        btn_game_4.setOnClickListener(listener);
        btn_game_5.setOnClickListener(listener);
        btn_game_6.setOnClickListener(listener);
        btn_game_7.setOnClickListener(listener);
        btn_game_8.setOnClickListener(listener);
        btn_game_9.setOnClickListener(listener);

        //Henter spørsmålene fra array.xml og legger det inn i et array
        restartGame();
        myDialog = new Dialog(this);
    }

    public void btnExitGame(View view) {
        DialogFragment dialog = new MyDialog();
        dialog.show(getFragmentManager(),"Avslutt");
        //this.finish();
        //startActivity(new Intent(this, StatisticsActivity.class));
    }
    public void bntRemoveText(View view){
        newNumber.setText("");
    }

    @Override
    public void onYesClick() {
        finish();
    }
    @Override
    public void onNoClick() {
        return;
    }

    public void btnCheck(View view){
        chechQuestion(view);
    }

    public void chechQuestion(View view){
        if (whichQuestion < numberOfQuestions) {
            String youAnswerd = newNumber.getText().toString();

            if (answers[questionNumber].equals(youAnswerd)){
                rightAwser++;
                txt_right_awser.setText(rightAwser+"");
                newNumber.setText("");
            }
            else {
                wrongAwser++;
                txt_wrong_awser.setText(wrongAwser+"");
                newNumber.setText("");
            }
            youAnswerd = "";
            nextQuestion();
        }
        else{
            if (answers[questionNumber].equals(newNumber.getText().toString())){
                rightAwser++;
                txt_right_awser.setText(rightAwser+"");
            }
            else {
                wrongAwser++;
                txt_wrong_awser.setText(wrongAwser+"");
            }
            findViewBtn();
            btn_game_0.setEnabled(false);
            btn_game_2.setEnabled(false);
            btn_game_3.setEnabled(false);
            btn_game_4.setEnabled(false);
            btn_game_5.setEnabled(false);
            btn_game_6.setEnabled(false);
            btn_game_7.setEnabled(false);
            btn_game_8.setEnabled(false);
            btn_game_9.setEnabled(false);
            btn_remove.setEnabled(false);
            btn_game_submit.setEnabled(false);


            showPopup(view);
            Statistics anser = new Statistics(rightAwser,numberOfQuestions);
            StatisticsBrain.statistics.add(anser);


            //Toast toast = Toast.makeText(context, text, duration);
            //toast.show();
        }
    }

    public void nextQuestion() {
        boolean approved = false;
        while (!approved) {
            int number = (int) (Math.random() * 24) + 1;
            if (!selectedQuestions.contains(number)) {
                selectedQuestions.add(number);
                questionNumber = number;
                txt_game_question.setText(questions[questionNumber] + " =");
                whichQuestion++;
                approved = true;
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_NUMBEROFQUESTION, numberOfQuestions);
        outState.putInt(STATE_WHICHQUESTION, whichQuestion);
        outState.putIntegerArrayList(STATE_SELECTEDQUESTION, selectedQuestions);
        outState.putInt(STATE_QUESTIONNUMBER, questionNumber);
        outState.putInt(STATE_RIGHTAWSER,rightAwser);
        outState.putInt(STATE_WRONGAWSER, wrongAwser);
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        numberOfQuestions = savedInstanceState.getInt(STATE_NUMBEROFQUESTION);
        whichQuestion = savedInstanceState.getInt(STATE_WHICHQUESTION);
        selectedQuestions = savedInstanceState.getIntegerArrayList(STATE_SELECTEDQUESTION);
        questionNumber = savedInstanceState.getInt(STATE_QUESTIONNUMBER);
        rightAwser = savedInstanceState.getInt(STATE_RIGHTAWSER);
        wrongAwser = savedInstanceState.getInt(STATE_WRONGAWSER);
        txt_game_question.setText(questions[questionNumber]+" =");
        txt_right_awser.setText(rightAwser+"");
        txt_wrong_awser.setText(wrongAwser+"");

    }
    public void showPopup(View v){

        txt_result = (TextView) myDialog.findViewById(R.id.txt_result);
        txt_result_of = (TextView) findViewById(R.id.txt_result_of);


        myDialog.setContentView(R.layout.custon_pop_up);
        btn_restart = (ImageButton) myDialog.findViewById(R.id.btn_restart);
        btnClose = (ImageButton) myDialog.findViewById(R.id.btn_Close);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                finish();
            }
        });
        btn_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
                myDialog.dismiss();

            }
        });

        myDialog.show();
        //txt_result.setText("Hei");
    }
    public void restartGame(){
        whichQuestion = 0;
        questionNumber = 0;
        rightAwser = 0;
        wrongAwser = 0;

        newNumber.setText("");
        questions = getResources().getStringArray(R.array.questions);
        answers = getResources().getStringArray(R.array.answers);

        txt_game_question.setText(questions[questionNumber]+ " =");
        txt_right_awser.setText(rightAwser+"");
        txt_wrong_awser.setText(wrongAwser+"");

        nextQuestion();
        btn_game_0.setEnabled(true);
        btn_game_2.setEnabled(true);
        btn_game_3.setEnabled(true);
        btn_game_4.setEnabled(true);
        btn_game_5.setEnabled(true);
        btn_game_6.setEnabled(true);
        btn_game_7.setEnabled(true);
        btn_game_8.setEnabled(true);
        btn_game_9.setEnabled(true);
        btn_remove.setEnabled(true);
        btn_game_submit.setEnabled(true);


    }
    public void findViewBtn(){
        btn_game_0 = (Button) findViewById(R.id.btn_game_0);
        btn_game_1 = (Button) findViewById(R.id.btn_game_1);
        btn_game_2 = (Button) findViewById(R.id.btn_game_2);
        btn_game_3 = (Button) findViewById(R.id.btn_game_3);
        btn_game_4 = (Button) findViewById(R.id.btn_game_4);
        btn_game_5 = (Button) findViewById(R.id.btn_game_5);
        btn_game_6 = (Button) findViewById(R.id.btn_game_6);
        btn_game_7 = (Button) findViewById(R.id.btn_game_7);
        btn_game_8 = (Button) findViewById(R.id.btn_game_8);
        btn_game_9 = (Button) findViewById(R.id.btn_game_9);
        btn_remove = (ImageButton) findViewById(R.id.btn_remove);
        btn_game_submit = (ImageButton) findViewById(R.id.btn_game_submit);
    }


}