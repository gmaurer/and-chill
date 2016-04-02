package com.joe.chill.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.joe.chill.R;

public class WelcomeActivity extends AppCompatActivity {

    private ImageButton mButtonStart;
    private Button mButtonSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //Glide.with(this)
        //        .load(R.drawable.tutorial_phone).fitCenter()
        //        .into((ImageView)findViewById(R.id.imageViewWelcome));
        mButtonStart = (ImageButton) findViewById(R.id.tutorial_start);
        mButtonSkip = (Button) findViewById(R.id.tutorial_skip);


        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                launchTutorial();
            }
        });
        mButtonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                launchLogin();
            }
        });
    }


    private void launchTutorial() {
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
    }
    private void launchLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
