package com.joe.chill.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import com.joe.chill.R;

public class TutorialActivity extends AppCompatActivity {

    private VideoView video_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        video_view = (VideoView)findViewById(R.id.tutorial_video_view);
        video_view.setVideoPath("@drawable/tutorial_gif.gif");
        video_view.setZOrderOnTop(true);
        video_view.setMediaController(new MediaController(this));
        video_view.start();
    }
}
