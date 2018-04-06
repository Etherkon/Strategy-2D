package com.gwar.thewesternfront;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    private Button start;
    public MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        start = (Button) findViewById(R.id.start_button);

        player =MediaPlayer.create(StartActivity.this,R.raw.prussia);
        player.start();
        player.setLooping(true);

        start.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(StartActivity.this, MenuActivity.class);
                startActivity(i);
            }
        });

    }
}
