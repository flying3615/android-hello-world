package com.sqisland.android.hello.ui;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.media.MediaControlIntent;
import android.widget.MediaController;
import android.widget.VideoView;

import com.sqisland.android.hello.R;

public class VideoActivity extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoPath("http://clips.vorwaerts-gmbh.de/VfE_html5.mp4");

        //Player controls
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);


        videoView.start();

    }
}
