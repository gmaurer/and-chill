package com.joe.chill.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.joe.chill.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Glide.with(this)
                .load(R.drawable.tutorial_phone).fitCenter()
                .into((ImageView)findViewById(R.id.imageViewWelcome));
    }
}
