package com.tobiasstrom.s331392mappe1comtobiasstrom;


import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Collections;
import java.util.Locale;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class GameActivity extends AppCompatActivity implements MyDialog.DialogClickListener {
    //Tag for debug
    private static final String TAG = "GameActivity";

    //Oppretter vaiablene vi trenger.
    private String[] questions; //liste med spørsmålene fra arrays.xml
    private String[] answers;  //Liste med svarene fra arrays.xml
    private ArrayList<Integer> randomAmount = new ArrayList<>(); //Liste med 25 elementer i random rekkefølge
    private int numberOfQuestions = 5; //default verdi dersom den fantes ikke i preferanser (just in case)
    private int whichQuestion = 0;
    private int questionNumber = 0;
    private int rightAwser = 0;
    private int wrongAwser = 0;
    private int totalRightAnswer = 0;
    private int totalWrongAnswer = 0;
    private int number;
    private EditText newNumber;
    private Dialog myDialog;
    private ImageButton btnClose, btn_restart;
    private TextView txt_game_question;
    private TextView txt_right_awser;
    private TextView txt_wrong_awser;
    private TextView txt_question_number;
    private TextView txt_question_total;
    private TextView txt_result_of;
    private TextView txt_result;
    private SharedPreferences sharedPreferences;
    ListView lvGame;



    //Oppretter variabler spm brukes når vi lagrer ved rotasjon
    private static final String STATE_NUMBEROFQUESTION = "NumberOfQuestion";
    private static final String STATE_WHICHQUESTION = "WhitsQuestion";
    private static final String STATE_QUESTIONNUMBER = "QuestionNumber";
    private static final String STATE_RIGHTAWSER = "RightAwser";
    private static final String STATE_WRONGAWSER = "WrongAwser";
    private static final String STATE_RANDOM = "RandomQuestion";
    private static final String STATE_NUMBER = "Number";


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
    //Får opp en dialog boks hvis vi trykker på tilbakeknappen
    @Override
    public void onBackPressed() {
        DialogFragment dialog = new MyDialog();
        dialog.show(getFragmentManager(),"Avslutt");
    }
    //Setter liktig spåk på siden.
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
    //Fyller opp randomAmount array og shuffler det.
    public void randomArray(){
        randomAmount.clear();
        int i = 0;
        while (i < 25){
            randomAmount.add(i);
            i++;
        }
        Collections.shuffle(randomAmount);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Henter derdien vi trenger fra sheardprefferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        setLang(sharedPreferences.getString("languagePref",""));
        setAmountOfQuestions(sharedPreferences.getString("questionPref",""));

        totalRightAnswer = getStats(sharedPreferences,"stats_right");
        totalWrongAnswer = getStats(sharedPreferences, "stats_wrong");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        randomArray(); //Oppretter rabdin arrat

        //Akvivere knappenen og textview sånn at de kan brukes.
        newNumber = (EditText) findViewById(R.id.inp_newNumber);
        txt_game_question = (TextView)findViewById(R.id.txt_game_question);
        txt_right_awser = (TextView)findViewById(R.id.txt_right_awser);
        txt_wrong_awser = (TextView)findViewById(R.id.txt_wrong_awser);
        txt_question_total = (TextView)findViewById(R.id.txt_question_total);
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


        //ser hvilken verdi som er tastet inn og legger den inn i inputfelte.
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                newNumber.append(b.getText().toString());
            }
        };

        //Kjører metoden listener hvis man trykker på kanppene
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
        txt_question_total.setText(String.valueOf(numberOfQuestions));

        //Starter et spill med nextQuestion sånn at det ikke altid blir samme spørsmål som man starter med.
        //den gjøres til det ikke er noen flere spørsmål
        nextQuestion();



    }

    //Åpner en dialog boks som spør om du ønsker å avslutte
    public void btnExitGame(View view) {
        DialogFragment dialog = new MyDialog();
        dialog.show(getFragmentManager(),"Avslutt");
    }

    //Hvis man ønsker å fjeren teksten man har skrevet inn
    public void bntRemoveText(View view){
        newNumber.setText("");
    }

    //Hvis man trykker jeg ønsker å avslutte spillet
    @Override
    public void onYesClick() {
        finish();
    }

    //Hvis man ikke ønsker å avslutte spillet.
    @Override
    public void onNoClick() {
        //return;
    }
    //Metoden som kjøres når du trykker på skjekk knappen.
    public void btnCheck(View view){
        //Spiller lyd hvis man ikke har skrevet inn noe
        final MediaPlayer nullinput = MediaPlayer.create(this, R.raw.null_input_sound);
        //Skjekker om noe er skrevet inn
        if(!newNumber.getText().toString().isEmpty()){
            chechQuestion(view);
        }else{
            nullinput.start();
            newNumber.setHint(R.string.accessiblity_input_number);
        }

    }
    //Metoden som skjekker spørsmål
    public void chechQuestion(View view){
        //Setter lydene her siden de bare skal brukes i denne metoden.
        final MediaPlayer rigthSound = MediaPlayer.create(this, R.raw.happykids);
        final MediaPlayer wrongSound = MediaPlayer.create(this, R.raw.boo);
        //Henter inn veriden som ble skrevet inn
        String youAnswerd = newNumber.getText().toString();
        //Skjekker om du har noen flere spørsmål igjen.
        if (whichQuestion < numberOfQuestions) {
            //Legger inn svaret med spørsmålet inn i klassen sån at man kan bruke det senere
            Statistics anser = new Statistics(youAnswerd,answers[number],questions[number]);
            StatisticsBrain.statistics.add(anser);

            //Hvis svaret er riktig så plusser den på riktig svar og spiller lyd.
            if (answers[number].equals(youAnswerd)){
                rightAwser++;
                txt_right_awser.setText(String.valueOf(rightAwser));
                newNumber.setText("");
                rigthSound.start();
            }
            //Hvis svaret er feil så plusser den på feilsvar og spiller lyd.
            else {
                wrongAwser++;
                txt_wrong_awser.setText(String.valueOf(wrongAwser));
                newNumber.setText("");
                wrongSound.start();
            }
            //Kjører metoden neste spørrsmål
            nextQuestion();
        }
        //Når det er det siste sprøsmålet kjører den denne metoden
        else{
            if (answers[number].equals(newNumber.getText().toString())){
                rightAwser++;
                rigthSound.start();
                txt_right_awser.setText(String.valueOf(rightAwser));
            }
            else {
                wrongAwser++;
                txt_wrong_awser.setText(String.valueOf(wrongAwser));
                wrongSound.start();
            }
            //Legger inn statestikk
            Statistics anser = new Statistics(youAnswerd,answers[number],questions[number]);
            StatisticsBrain.statistics.add(anser);

            //Viser popup med statestikk
            showPopup();

            //oppdatere session statistikk
            totalRightAnswer += rightAwser;
            totalWrongAnswer += wrongAwser;

            //lagre session statistikk til sharedPreferences
            sharedPreferences.edit().putInt("stats_right", totalRightAnswer).apply();
            sharedPreferences.edit().putInt("stats_wrong", totalWrongAnswer).apply();


        }
    }
    //Finner det neste spørrsmålet fra en liste med tall som er i random rekkefølge
    //sånn at du ikke nå bruke Math.random da det kan kasje hvis den ikke finne den siste på mange forskjøk
    public void nextQuestion() {
        //nummer er hvilke spørsmål fra listen med spørsmål.
        //randomAmount er en liste med tal i en random rekkefølge
        number = randomAmount.indexOf(whichQuestion);
        //Finner id og skriver ut det som har skjedd
        txt_question_number = (TextView)findViewById(R.id.txt_question_number);
        txt_game_question.setText(questions[number]);
        //Øker hvilke spørsmål man er på og legger det inn sånn at man ka se hvor langt man har kommet seg.
        whichQuestion++;
        txt_question_number.setText(String.valueOf(whichQuestion));
    }

    //Metoden som lagrer informasjonen når du vir skjermen.
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_NUMBEROFQUESTION, numberOfQuestions);
        outState.putInt(STATE_WHICHQUESTION, whichQuestion);
        outState.putInt(STATE_QUESTIONNUMBER, questionNumber);
        outState.putInt(STATE_RIGHTAWSER,rightAwser);
        outState.putInt(STATE_WRONGAWSER, wrongAwser);
        outState.putIntegerArrayList(STATE_RANDOM, randomAmount);
        outState.putInt(STATE_NUMBER,number);
        super.onSaveInstanceState(outState);
    }
    //Henter ut informasjon og lagrer den i de satte variablene
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        numberOfQuestions = savedInstanceState.getInt(STATE_NUMBEROFQUESTION);
        whichQuestion = savedInstanceState.getInt(STATE_WHICHQUESTION);
        questionNumber = savedInstanceState.getInt(STATE_QUESTIONNUMBER);
        rightAwser = savedInstanceState.getInt(STATE_RIGHTAWSER);
        wrongAwser = savedInstanceState.getInt(STATE_WRONGAWSER);
        number = savedInstanceState.getInt(STATE_NUMBER);
        txt_game_question.setText(String.valueOf(questions[number]));
        txt_right_awser.setText(String.valueOf(rightAwser));
        txt_wrong_awser.setText(String.valueOf(wrongAwser));
        //Viser bare popup hvis spillet er fedrig
        if (numberOfQuestions == whichQuestion) {
            showPopup();
        }
    }
    //Metode for å vise popup
    public void showPopup(){
        //Oppretter en dialogbox og setter verdien til den custom_pop_up boks
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.custon_pop_up);
        //Henter ut informasjonen av det som er i popupen
        lvGame = (ListView) myDialog.findViewById(R.id.lvGame);
        txt_result = (TextView) myDialog.findViewById(R.id.txt_result);
        txt_result_of = (TextView) myDialog.findViewById(R.id.txt_result_of);
        btnClose = (ImageButton) myDialog.findViewById(R.id.btn_Close);
        btn_restart = (ImageButton) myDialog.findViewById(R.id.btn_restart);

        //Oppretter en StatestikAdaper for å hvise listen på den måten jeg ønsker
        StratisticsAdapter feedAdapter = new StratisticsAdapter(this, R.layout.listview_row, StatisticsBrain.statistics);
        //Oppretter listen med en bestemt adapter
        lvGame.setAdapter(feedAdapter);

        //Metoder hvis du trykker på knappene
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatisticsBrain.statistics.clear();
                myDialog.dismiss();
                finish();
            }
        });
        btn_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
                myDialog.dismiss();
                StatisticsBrain.statistics.clear();
            }
        });
        //Skriver ut verdiene
        txt_result.setText(String.valueOf(rightAwser));
        txt_result_of.setText(String.valueOf(numberOfQuestions));
        //Gjør sånn at det ikke går ann å trykke utenfor
        myDialog.setCancelable(false);
        //viser dialogboken.
        myDialog.show();
    }

    //Restarter hele spille og setter alle verdien tilbake til de de var.
    //Og setter fram et nytt spørsmål
    public void restartGame(){

        whichQuestion = 0;
        questionNumber = 0;
        rightAwser = 0;
        wrongAwser = 0;
        randomAmount.clear();
        randomArray();
        StatisticsBrain.statistics.clear();

        newNumber.setText("");
        questions = getResources().getStringArray(R.array.questions);
        answers = getResources().getStringArray(R.array.answers);

        txt_game_question.setText(questions[questionNumber]+ " =");
        txt_right_awser.setText(rightAwser+"");
        txt_wrong_awser.setText(wrongAwser+"");

        nextQuestion();
    }
}