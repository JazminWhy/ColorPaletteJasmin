package com.example.jasmin.colorpalettejasmin;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView t;
    SeekBar s;
    RadioButton rb1, rb2;
    String url;
    MyThread cloud;
    Activity act;
    ProgressBar p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = findViewById(R.id.textView);
        p = findViewById(R.id.progressBar);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        p.setProgress(50);

        act = this;
        cloud = new MyThread(t, p, rb1, rb2, act);
        url = "http://www.colourlovers.com/api/palettes/random?format=json";
        String title = "TITLE";
        act.setTitle(title);
        cloud.execute(url);
    }

    public void click(View aView){
        cloud = new MyThread(t, p, rb1, rb2, act);
        cloud.execute(url);
    }
}
