package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class result extends AppCompatActivity {

    TextView greet;
    TextView score;
    Button exit;
    Button playagain;
    int score1=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);
        greet=findViewById(R.id.textView13);
        score=findViewById(R.id.textView14);
        exit=findViewById(R.id.button5);
        playagain=findViewById(R.id.button4);

        Intent i=getIntent();
        score1=i.getIntExtra("score",0);
        String userscore=String.valueOf(score1);
        score.setText("your score"+userscore);

        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}