package com.gwar.thewesternfront;

import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Petri on 21.7.2016.
 */
public class BattleActivity extends AppCompatActivity {

    private Bundle extras;
    private ImageView soldier1;
    private ImageView soldier2;
    private ImageView flag1;
    private ImageView flag2;
    private TextView hit;
    private int choice1 = 0;
    private int choice2 = 0;
    private int situation = 0;
    private int hitter = 0;
    private String happened;
    private android.view.ViewGroup.LayoutParams layoutParams;


    Handler fireHandler = new Handler();
    Handler resultHandler = new Handler();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle);
        happened = "What !";

        soldier1 = (ImageView) findViewById(R.id.soldier1);
        soldier2 = (ImageView) findViewById(R.id.soldier2);
        flag1 = (ImageView) findViewById(R.id.flag1);
        flag2 = (ImageView) findViewById(R.id.flag2);
        hit = (TextView) findViewById(R.id.hit);

        extras = getIntent().getExtras();
        choice1 = extras.getInt("allied");
        choice2 = extras.getInt("central");
        situation = extras.getInt("hit");
        hitter = extras.getInt("hitter");

        flags1(choice1);
        flags2(choice2);

        if(situation == 1){
            happened = "Hit !";
        }
        else if(situation == 0){
            happened = "Missed !";
        }

        if (hitter == 1) {
            soldier1.setImageResource(R.drawable.asoldier2);
        } else if (hitter == 0) {
            soldier2.setImageResource(R.drawable.csoldier2);
        } else if (hitter == 2) {
            soldier1.setImageResource(R.drawable.aartillery2);
            soldier1.getLayoutParams().width = 700;
            soldier1.getLayoutParams().height = 530;
            soldier1.setScaleType(ImageView.ScaleType.FIT_XY);
        } else if (hitter == 3) {
            soldier2.setImageResource(R.drawable.cartillery2);
            soldier2.getLayoutParams().width = 700;
            soldier2.getLayoutParams().height = 530;
            soldier2.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        fire_action();
        result();
    }

    private void flags1(int choice){
        if(choice == 1){
            flag1.setImageResource(R.drawable.france);
        }
        else if(choice == 2){
            flag1.setImageResource(R.drawable.uk);
        }
        else if(choice == 3){
            flag1.setImageResource(R.drawable.usa);
        }
        else if(choice == 4){
            flag1.setImageResource(R.drawable.germany);
        }
        else if(choice == 5){
            flag1.setImageResource(R.drawable.austria);
        }
    }
    private void flags2(int choice){
        if(choice == 1){
            flag2.setImageResource(R.drawable.france);
        }
        else if(choice == 2){
            flag2.setImageResource(R.drawable.uk);
        }
        else if(choice == 3){
            flag2.setImageResource(R.drawable.usa);
        }
        else if(choice == 4){
            flag2.setImageResource(R.drawable.germany);
        }
        else if(choice == 5){
            flag2.setImageResource(R.drawable.austria);
        }
    }

    private void fire_action(){
        fireHandler.postDelayed(new Runnable() {
            public void run() {
                    soldier1.setImageResource(R.drawable.asoldier);
                    soldier2.setImageResource(R.drawable.csoldier);
                    if (hitter == 2) {
                    soldier1.setImageResource(R.drawable.aartillery);}
                   if (hitter == 3) {
                    soldier2.setImageResource(R.drawable.cartillery);}
                    hit.setText(happened);
                if (hitter == 0 & situation == 1) {
                    soldier1.setVisibility(View.INVISIBLE);
                } else if (hitter == 1 & situation == 1) {
                    soldier2.setVisibility(View.INVISIBLE);
                } else if (hitter == 3 & situation == 1) {
                    soldier1.setVisibility(View.INVISIBLE);
                } else if (hitter == 2 & situation == 1) {
                    soldier2.setVisibility(View.INVISIBLE);
                }
                }

        }, 2000);
    }
    private void result(){
        resultHandler.postDelayed(new Runnable() {
            public void run() {

                hit.setText("");
                setResult(1);
                finish();
            }

        }, 3000);
    }




}
