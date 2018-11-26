package com.example.raulbarbosa.ihclogin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import java.io.InputStream;

public class Machine extends YouTubeBaseActivity {

    Button btn;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.machine);
        InputStream stream = null;
        ImageView imageView = findViewById(R.id.imageView);

        Glide.with(this)
                .load(R.drawable.giphy)
                .into(imageView);

        btn=findViewById(R.id.button2);
        Typeface font = Typeface.createFromAsset(getAssets(), "Basic-Regular.ttf");
        btn.setTypeface(font);

    }

    public void playVideo(View v) {
        Context context=this;
        String ytId="XSza8hVTlmM";
        if (ytId != null && ytId != "") {
            Intent videoIntent = YouTubeStandalonePlayer.createVideoIntent((Activity)context,"AIzaSyBh3l2tGeLxeHtI2AUEeYw-ESI_Kiv46MU", ytId, 0, false, false);
            context.startActivity(videoIntent);
        } else {
            Toast.makeText(context, "this is not youtube url!", Toast.LENGTH_SHORT).show();
        }
    }


}