package com.gwar.thewesternfront;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Petri on 20.7.2016.
 */
public class EndActivity  extends AppCompatActivity {

    private Bundle extras;
    private int winner = 0;

    private TextView text;
    private ImageView pic;
    private Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end);

        text = (TextView) findViewById(R.id.vic_name);
        pic = (ImageView) findViewById(R.id.vic_flag);
        finish = (Button) findViewById(R.id.vic_button);

        extras = getIntent().getExtras();
        winner = extras.getInt("winner");

        if(winner == 1){
            pic.setImageResource(R.drawable.france);
            text.setText("France wins");
        }
        else if(winner == 2){
            pic.setImageResource(R.drawable.uk);
            text.setText("United Kingdom wins");
        }
        else if(winner == 3){
            pic.setImageResource(R.drawable.usa);
            text.setText("USA wins");
        }
        else if(winner == 4){
            pic.setImageResource(R.drawable.germany);
            text.setText("Germany wins");
        }
        else if(winner == 5){
            pic.setImageResource(R.drawable.austria);
            text.setText("Austria-Hungary wins");
        }

        finish.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(EndActivity.this, MenuActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });




    }

    }
