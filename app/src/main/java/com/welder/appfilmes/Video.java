package com.welder.appfilmes;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Video extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.videoView);
        String video = getIntent().getExtras().getString("video");

        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(Uri.parse(video));
        videoView.requestFocus();
        videoView.start();


    }

}