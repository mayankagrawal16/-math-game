package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class game extends AppCompatActivity {

    TextView score;
    TextView scoreval;

    TextView life;
    TextView lifeval;

    TextView time;
    TextView timeval;


    TextView ques;
    EditText ans;

    Button ok,next;

    //this is used to generate random ques
    Random random=new Random();


    //this is the variable for the two number
    int num1;
    int num2;

    //this are the real and user answer
    int userans;
    int realans;

    // this is the by default user score
    int userscore=0;

    // this is the by default user life
    int userlife=3;

    // this is for the timer
    CountDownTimer timer;

    // this is used to set the time limit
    public static final long START_TIMER_IN_MILIS=10000;

    Boolean timer_running;
    long time_left_in_milis=START_TIMER_IN_MILIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        score=findViewById(R.id.textView);
        scoreval=findViewById(R.id.textView2);
        life=findViewById(R.id.textView3);
        lifeval=findViewById(R.id.textView4);
        time=findViewById(R.id.textView5);
        timeval=findViewById(R.id.textView8);


        ques=findViewById(R.id.textView7);
        ans=findViewById(R.id.editTextText);

        ok=findViewById(R.id.button6);
        next=findViewById(R.id.button7);
        gamecontinue();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userans=Integer.valueOf(ans.getText().toString());

                // when the user has given the ans time should be paused

                pauseTimer();
                if(userans==realans)
                {
                    userscore=userscore+10;
                    scoreval.setText(""+userscore);
                    ques.setText("correct");
                }
                else
                {
                    userlife=userlife-1;
                    lifeval.setText(""+userlife);
                    ques.setText("wrong");
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans.setText(" ");


                // when the user click on the next the time should be reset
                resetTimer();

                if(userlife<=0)
                {
                    Toast.makeText(getApplicationContext(), "game over", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(), result.class);
                    i.putExtra("score",userscore);
                    startActivity(i);
                    finish();
                }
                else {
                    gamecontinue();
                }

            }
        });
    }
    public void gamecontinue()
    {
        //it means we will get the random number between 1 to 100
        num1=random.nextInt(100);
        num2=random.nextInt(100);
        realans=num1+num2;

        ques.setText(num1+" + "+num2);

        startTimer();

    }

    public  void startTimer()
    {

        timer=new CountDownTimer(time_left_in_milis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                time_left_in_milis=millisUntilFinished;
                updateText();

            }

            @Override
            public void onFinish() {

                timer_running=false;
                updateText();
                pauseTimer();
                resetTimer();
                userlife=userlife-1;
                lifeval.setText(""+userlife);
                ques.setText("sorry times up");

            }
        }.start();
        timer_running=true;
    }

    private void resetTimer() {

        time_left_in_milis=START_TIMER_IN_MILIS;

        // call the method update text
        updateText();
    }

    private void pauseTimer() {

        // in this when the time comes to 00 is has to be stoped so
        timer.cancel();

        // it means time will be stopped
        timer_running=false;
    }

    private void updateText() {

        // this basically tells us how much time is left
        // means if 2 sec hai passed we still have 58 sec
        // so to do this we make an int variable i.e sec that tells us how much time is left

        //we know that time left=start time i.e 60000
        // so (60000/1000)%60=1
        // it determine the sec interval
        int second=(int)(time_left_in_milis/1000)%60;

        // now in order to display the time in text view we will convert it into string format
        //%02d means time in 2 digit i.e 01,45,00 etc
        String time_left = String.format(Locale.getDefault(),"%02d",second);


        // here time is the textview
        timeval.setText(time_left);

    }

}