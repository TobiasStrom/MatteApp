package com.tobiasstrom.s331392mappe1comtobiasstrom;


import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Collections;
import java.util.Locale;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class GameActivity extends AppCompatActivity implements MyDialog.DialogClickListener {
    private static final String TAG = "GameActivity";

    private EditText newNumber;
    private String[] questions;
    private String[] answers;
    private ArrayList<Integer> selectedQuestions = new ArrayList();
    private ArrayList<Integer> randomAmount = new ArrayList<>();
    private ArrayList<Statistics> statistics = new ArrayList<>();
    private int numberOfQuestions = 5; //default verdi dersom den fantes ikke i preferanser (just in case)
    private int whichQuestion = 0;
    TextView txt_game_question;
    TextView txt_right_awser;
    TextView txt_wrong_awser;
    private int questionNumber = 0;
    private int rightAwser = 0;
    private int wrongAwser = 0;
    private int totalRightAnswer = 0;
    private int totalWrongAnswer = 0;
    Dialog myDialog;
    Button btnClose;
    TextView txt_result_of, txt_result;
    ArrayList<Statistics> statistics = new ArrayList<>();
    SharedPreferences sharedPreferences;
    View view;


    private static final String STATE_NUMBEROFQUESTION = "NumberOfQuestion";
    private static final String STATE_WHICHQUESTION = "WhitsQuestion";
    private static final String STATE_SELECTEDQUESTION = "SelectedQuestion";
    private static final String STATE_QUESTIONNUMBER = "QuestionNumber";
    private static final String STATE_RIGHTAWSER = "RightAwser";
    private static final String STATE_WRONGAWSER = "WrongAwser";

    //metoden som prøver å hente antall spørsmål fra sharedPreferences, gjør ingenting dersom den feiler
    private void setAmountOfQuestions(String preferance) {
        try {
            numberOfQuestions = Integer.parseInt(preferance);
        } catch (Exception e) {
            //gjør ingenting dersom default verdi er deklarert
        }
    }

    //metoden som prøver å hente verdier fra sharedPreferences, setter verdi til 0 dersom den feiler
    private int getStats(SharedPreferences sharedPreferences, String key) {
        int value;
        try {
            value = sharedPreferences.getInt(key, 0);
        } catch (Exception e) {
            value = 0;
        }
        return value;
    }


    @Override
    public void onBackPressed() {
        DialogFragment dialog = new MyDialog();
        dialog.show(getFragmentManager(),"Avslutt");
    }

    public void setLang(String prefix) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration cf = res.getConfiguration();
        //setLocale krever api lvl 17 (current definert api lvl er 15) Dette er en av forslagene til AndroidStudio
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            cf.setLocale(new Locale(prefix));
        }
        res.updateConfiguration(cf,dm);
    }

    public void randomArray(){
        int i = 0;
        while (i < 25){
            randomAmount.add(i);
            i++;
        }
        Collections.shuffle(randomAmount);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        setLang(sharedPreferences.getString("languagePref",""));
        setAmountOfQuestions(sharedPreferences.getString("questionPref",""));

        totalRightAnswer = getStats(sharedPreferences,"stats_right");
        totalWrongAnswer = getStats(sharedPreferences, "stats_wrong");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        randomArray();

        newNumber = (EditText) findViewById(R.id.inp_newNumber);

        txt_game_question = (TextView)findViewById(R.id.txt_game_question);
        txt_right_awser = (TextView)findViewById(R.id.txt_right_awser);
        txt_wrong_awser = (TextView)findViewById(R.id.txt_wrong_awser);
        Button btn_game_0 = (Button) findViewById(R.id.btn_game_0);
        Button btn_game_1 = (Button) findViewById(R.id.btn_game_1);
        Button btn_game_2 = (Button) findViewById(R.id.btn_game_2);
        Button btn_game_3 = (Button) findViewById(R.id.btn_game_3);
        Button btn_game_4 = (Button) findViewById(R.id.btn_game_4);
        Button btn_game_5 = (Button) findViewById(R.id.btn_game_5);
        Button btn_game_6 = (Button) findViewById(R.id.btn_game_6);
        Button btn_game_7 = (Button) findViewById(R.id.btn_game_7);
        Button btn_game_8 = (Button) findViewById(R.id.btn_game_8);
        Button btn_game_9 = (Button) findViewById(R.id.btn_game_9);



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
        questions = getResources().getStringArray(R.array.questions);
        answers = getResources().getStringArray(R.array.answers);

        //printer verdier til brukeren
        txt_game_question.setText(String.valueOf(questions[questionNumber]));
        txt_right_awser.setText(String.valueOf(rightAwser));
        txt_wrong_awser.setText(String.valueOf(wrongAwser));
        nextQuestion();

        myDialog = new Dialog(this);
        myDialog.setCancelable(false);
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
        //return;
    }

    public void btnCheck(View view){
        chechQuestion(view);
    }

    public void chechQuestion(View view){

        if (whichQuestion < numberOfQuestions) {
            String youAnswerd = newNumber.getText().toString();
            if (youAnswerd.isEmpty()){
                youAnswerd = "0";
            }
            Statistics anser = new Statistics(youAnswerd,answers[questionNumber],questions[questionNumber]);
            statistics.add(anser);
            Log.d(TAG, "chechQuestion: " + statistics.toString());

            if (answers[questionNumber].equals(youAnswerd)){
                rightAwser++;
                txt_right_awser.setText(String.valueOf(rightAwser));
                newNumber.setText("");
                
            }
            else {
                wrongAwser++;
                txt_wrong_awser.setText(String.valueOf(wrongAwser));
                newNumber.setText("");
            }
            youAnswerd = "";
            nextQuestion();
        }
        else{
            if (answers[questionNumber].equals(newNumber.getText().toString())){
                rightAwser++;
                txt_right_awser.setText(String.valueOf(rightAwser));
            }
            else {
                wrongAwser++;
                txt_wrong_awser.setText(String.valueOf(wrongAwser));
            }
            //txt_game_question.setText("Du er ferdig");
            //Context context = getApplicationContext();
            //CharSequence text = "Du klarte " + wrongAwser + " feil";
            //int duration = Toast.LENGTH_SHORT;

            showPopup();

            //oppdatere session statistikk
            totalRightAnswer += rightAwser;
            totalWrongAnswer += wrongAwser;

            //lagre session statistikk til sharedPreferences
            sharedPreferences.edit().putInt("stats_right", totalRightAnswer).apply();
            sharedPreferences.edit().putInt("stats_wrong", totalWrongAnswer).apply();


            Statistics anser = new Statistics(rightAwser,numberOfQuestions);
            Statistics statistics1 = new Statistics(2,6);
            statistics.add(statistics1);
            statistics.add(anser);

            Log.d(TAG, statistics.toString());

            //Toast toast = Toast.makeText(context, text, duration);
            //toast.show();
        }
    }

    public void nextQuestion() {
        int number = randomAmount.indexOf(whichQuestion);
        txt_game_question.setText(questions[number]);
        whichQuestion++;
    }

    public void restartGame(){
        whichQuestion = 0;
        questionNumber = 0;
        rightAwser = 0;
        wrongAwser = 0;
        //randomAmount.clear();
        //randomArray();
        statistics.clear();
        newNumber.setText("");
        questions = getResources().getStringArray(R.array.questions);
        answers = getResources().getStringArray(R.array.answers);
        txt_game_question.setText(questions[questionNumber]+ " =");
        txt_right_awser.setText(rightAwser+"");
        txt_wrong_awser.setText(wrongAwser+"");
        nextQuestion();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_NUMBEROFQUESTION, numberOfQuestions);
        outState.putInt(STATE_WHICHQUESTION, whichQuestion);
        outState.putIntegerArrayList(STATE_SELECTEDQUESTION, selectedQuestions);
        outState.putInt(STATE_QUESTIONNUMBER, questionNumber);
        outState.putInt(STATE_RIGHTAWSER,rightAwser);
        outState.putInt(STATE_WRONGAWSER, wrongAwser);
        myDialog = new Dialog(this);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPostResume() {
        myDialog = new Dialog(this);
        super.onPostResume();
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
        txt_game_question.setText(String.valueOf(questions[questionNumber]));
        txt_right_awser.setText(String.valueOf(rightAwser));
        txt_wrong_awser.setText(String.valueOf(wrongAwser));

        if (numberOfQuestions == whichQuestion) {
            showPopup();
        }

    }
    public void showPopup(){

        /*txt_result = (TextView) myDialog.findViewById(R.id.txt_result);
        txt_result_of = (TextView) myDialog.findViewById(R.id.txt_result_of);
        myDialog.setContentView(R.layout.custon_pop_up);
        //txt_result.setTextSize(50);
        btnClose = (Button) myDialog.findViewById(R.id.btn_Close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                finish();
            }
        });
        myDialog.show();
        //txt_result.setText("Hei");*/
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Spillen din ble ferdig");
        builder.setMessage(rightAwser + " av: " + numberOfQuestions);
        builder.setPositiveButton("Igjen", new Dialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                restartGame();
            }
        });
        builder.setNegativeButton(R.string.ikkeok, new Dialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        });
        builder.setCancelable(false);
        builder.show();


    }



}