package com.gwar.thewesternfront;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Petri on 19.7.2016.
 */

// 1 = Allied
// 0 = Central Powers

public class MapActivity extends AppCompatActivity {

    private Bundle extras;
    private int choice1 = 1;
    private int choice2 = 1;
    private int side = 0;
    private int turn = 1;
    private int soldier_speed = 140;
    private int artillery_speed = 150;
    private int ai_selection = 1;
    private int random = 0;
    private Boolean ai_moves = false;
    private int points1 = 100;
    private int max_moves = 11;
    private int points2 = 100;
    private int hit = 20;
    private int heal = 5;
    private int ai_time = 1000;
    private Boolean fired = false;
    private Boolean fired_ai = false;
    private int died = -1;

    private static final int request = 1;

    Handler aiHandler = new Handler();
    Handler delay = new Handler();

    private ImageView turnFlag;
    private TextView player1;
    private TextView player2;
    private TextView test;
    private TextView testaus;
    private ImageView asoldier1;
    private ImageView asoldier2;
    private ImageView asoldier3;
    private ImageView asoldier4;
    private ImageView asoldier5;
    private ImageView aartillery;
    private ImageView csoldier1;
    private ImageView csoldier2;
    private ImageView csoldier3;
    private ImageView csoldier4;
    private ImageView csoldier5;
    private ImageView cartillery;

    Random rand = new Random();

    private int[] soldier_moves = { 0,0,0,0,0,0,0,0,0,0};
    private int[] artillery_moves = { 0,0 };
    private Boolean[] dead = { false,false,false,false,false,false,false,false,false,false};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        extras = getIntent().getExtras();
        choice1 = extras.getInt("allied");
        choice2 = extras.getInt("central");
        side = extras.getInt("side");

        turnFlag = (ImageView) findViewById(R.id.turnFlag);
        player1 = (TextView) findViewById(R.id.player1);
        player2 = (TextView) findViewById(R.id.player2);
        test = (TextView) findViewById(R.id.textView3);
        testaus = (TextView) findViewById(R.id.textView4);
        asoldier1 = (ImageView) findViewById(R.id.asoldier1);
        asoldier2 = (ImageView) findViewById(R.id.asoldier2);
        asoldier3 = (ImageView) findViewById(R.id.asoldier3);
        asoldier4 = (ImageView) findViewById(R.id.asoldier4);
        asoldier5 = (ImageView) findViewById(R.id.asoldier5);
        aartillery= (ImageView) findViewById(R.id.aartillery);
        csoldier1 = (ImageView) findViewById(R.id.csoldier1);
        csoldier2 = (ImageView) findViewById(R.id.csoldier2);
        csoldier3 = (ImageView) findViewById(R.id.csoldier3);
        csoldier4 = (ImageView) findViewById(R.id.csoldier4);
        csoldier5 = (ImageView) findViewById(R.id.csoldier5);
        cartillery= (ImageView) findViewById(R.id.cartillery);

        // Allies

        if(side == 1){
                asoldier1.setOnTouchListener(new OnSwipeTouchListener(MapActivity.this) {
                public void onSwipeTop() {
                    }
                  public void onSwipeRight() {
                      testaus.setText("");
                      if(soldier_moves[0] != max_moves & ai_moves == false){
                      asoldier1.setX(asoldier1.getX() + soldier_speed);
                      ++soldier_moves[0];
                      passed(0);
                      turn_ends();}
                  }
                  public void onSwipeLeft() {
                      fired = true;
                      fire(1);
                  }
                public void onSwipeBottom() {
                 }
        });
                asoldier2.setOnTouchListener(new OnSwipeTouchListener(MapActivity.this) {
                    public void onSwipeTop() {
                    }

                    public void onSwipeRight() {
                        if(soldier_moves[1] != max_moves & ai_moves == false){
                        asoldier2.setX(asoldier2.getX() + soldier_speed);
                        ++soldier_moves[1];
                        passed(1);
                        turn_ends();}
                    }
                    public void onSwipeLeft() {
                        fired = true;
                        fire(2);
                    }
                    public void onSwipeBottom() {
                    }
                });
                asoldier3.setOnTouchListener(new OnSwipeTouchListener(MapActivity.this) {
                    public void onSwipeTop() {
                    }
                    public void onSwipeRight() {
                        if(soldier_moves[2] != max_moves & ai_moves == false){
                        asoldier3.setX(asoldier3.getX() + soldier_speed);
                        ++soldier_moves[2];
                        passed(2);
                        turn_ends();}
                    }
                    public void onSwipeLeft() {
                        fired = true;
                        fire(3);
                    }
                    public void onSwipeBottom() {
                    }
                });
            asoldier4.setOnTouchListener(new OnSwipeTouchListener(MapActivity.this) {
                public void onSwipeTop() {}
                public void onSwipeRight() {
                    if(soldier_moves[3] != max_moves & ai_moves == false){
                        asoldier4.setX(asoldier4.getX() + soldier_speed);
                        ++soldier_moves[3];
                        passed(3);
                        turn_ends();}
                }
                public void onSwipeLeft() {
                    fired = true;
                    fire(4);
                }
                public void onSwipeBottom() {
                }
            });
            asoldier5.setOnTouchListener(new OnSwipeTouchListener(MapActivity.this) {
                public void onSwipeTop() {}
                public void onSwipeRight() {
                    if(soldier_moves[4] != max_moves & ai_moves == false){
                    asoldier5.setX(asoldier5.getX() + soldier_speed);
                    ++soldier_moves[4];
                    passed(4);
                    turn_ends();}
                }
                public void onSwipeLeft() {
                    fired = true;
                    fire(5);
                }
                public void onSwipeBottom() {
                }
            });
            aartillery.setOnTouchListener(new OnSwipeTouchListener(MapActivity.this) {
                public void onSwipeTop() {
                    if(artillery_moves[0] != -2 & ai_moves == false){
                    aartillery.setY(aartillery.getY() - artillery_speed);
                    --artillery_moves[0];
                    turn_ends();}
                }

                public void onSwipeRight() {
                }

                public void onSwipeLeft() {
                    fired = true;
                    fire(6);
                }

                public void onSwipeBottom() {
                    if(artillery_moves[0] != 2 & ai_moves == false){
                    aartillery.setY(aartillery.getY() + artillery_speed);
                    ++artillery_moves[0];
                    turn_ends();}
                }
            });
        }

        // Centrals

        else if(side == 0) {
                 csoldier1.setOnTouchListener(new OnSwipeTouchListener(MapActivity.this) {
                  public void onSwipeTop() {
                }
                   public void onSwipeRight() {
                       fired = true;
                       fire(7);
                   }
                    public void onSwipeLeft() {
                        if(soldier_moves[5] != max_moves & ai_moves == false){
                        csoldier1.setX(csoldier1.getX() - soldier_speed);
                        turn_ends();
                        ++soldier_moves[5];
                            passed(5);}
                }
                     public void onSwipeBottom() {
                }
               });
                csoldier2.setOnTouchListener(new OnSwipeTouchListener(MapActivity.this) {
                public void onSwipeTop() {
                }
                public void onSwipeRight() {
                    fired = true;
                    fire(8);
                }
                public void onSwipeLeft() {
                    if(soldier_moves[6] != max_moves & ai_moves == false){
                    csoldier2.setX(csoldier2.getX() - soldier_speed);
                    ++soldier_moves[6];
                        passed(6);
                    turn_ends();}
                }
                public void onSwipeBottom() {
                }
            });
                csoldier3.setOnTouchListener(new OnSwipeTouchListener(MapActivity.this) {
                    public void onSwipeTop() {
                    }
                    public void onSwipeRight() {
                        fired = true;
                        fire(9);
                    }
                    public void onSwipeLeft() {
                        if(soldier_moves[7] != max_moves & ai_moves == false){
                        csoldier3.setX(csoldier3.getX() - soldier_speed);
                        ++soldier_moves[7];
                            passed(7);
                        turn_ends();}
                    }
                    public void onSwipeBottom() {
                    }
                });
            csoldier4.setOnTouchListener(new OnSwipeTouchListener(MapActivity.this) {
                public void onSwipeTop() {
                }
                public void onSwipeRight() {
                    fired = true;
                    fire(10);
                }
                public void onSwipeLeft() {
                    if(soldier_moves[8] != max_moves & ai_moves == false){
                    csoldier4.setX(csoldier4.getX() - soldier_speed);
                    ++soldier_moves[8];
                        passed(8);
                    turn_ends();}
                }
                public void onSwipeBottom() {
                }
            });
            csoldier5.setOnTouchListener(new OnSwipeTouchListener(MapActivity.this) {
                public void onSwipeTop() {
                }
                public void onSwipeRight() {
                    fired = true;
                    fire(11);
                }
                public void onSwipeLeft() {
                    if(soldier_moves[9] != max_moves & ai_moves == false){
                    csoldier5.setX(csoldier5.getX() - soldier_speed);
                    ++soldier_moves[9];
                        passed(9);
                    turn_ends();}
                }
                public void onSwipeBottom() {
                }
            });

            cartillery.setOnTouchListener(new OnSwipeTouchListener(MapActivity.this) {
                public void onSwipeTop() {
                    if(artillery_moves[1] != -2 & ai_moves == false){
                       --artillery_moves[1];
                        cartillery.setY(cartillery.getY() - artillery_speed);
                        turn_ends();}
                    }
                public void onSwipeRight() {
                    fired = true;
                    fire(12);
                }
                public void onSwipeLeft() {
                }
                public void onSwipeBottom() {
                    if(artillery_moves[1] != 2 & ai_moves == false){
                    ++artillery_moves[1];
                    cartillery.setY(cartillery.getY() + artillery_speed);
                    turn_ends();}
                }
            });
            }
        turnFlag.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                healing(false);
                turn_ends();
            }
        });


        if(side == 0) {
                turn_flags(choice2);
        } else {
            turn_flags(choice1);
        }
    }

    private void turn_ends(){
        status();
    //    wait_some();
        if (side == 0 & fired_ai == false) {
            turn_flags(choice1);
            unclick(true);
            ai();
        } else if(side == 1 & fired_ai == false) {
            turn_flags(choice2);
            unclick(true);
            ai();
        }
        fired_ai = false;
        fired = false;
        status();
    }

    private void turn_flags(int choice){
        if(choice == 1){
            turnFlag.setImageResource(R.drawable.france);
        }
        else if(choice == 2){
            turnFlag.setImageResource(R.drawable.uk);
        }
        else if(choice == 3){
            turnFlag.setImageResource(R.drawable.usa);
        }
        else if(choice == 4){
            turnFlag.setImageResource(R.drawable.germany);
        }
        else if(choice == 5){
            turnFlag.setImageResource(R.drawable.austria);
        }
    }

    // 2,4,1,3,5
    // 4,3,1,2,5

    private void fire(int who){

        if(who == 1){
            target(0, 5);
        } else if(who == 2){
            target(1, 8);
        } else if(who == 3){
            target(2, 6);
        } else if(who == 4){
            target(3, 7);
        } else if(who == 5){
            target(4, 9);
        } else if(who == 6){
            target_artillery(0);
        } else if(who == 7){
            target(5, 0);
        } else if(who == 8){;
            target(6, 2);
        } else if(who == 9){
            target(7, 3);
        } else if(who == 10){
            target(8, 1);
        } else if(who == 11){
            target(9, 4);
        } else if(who == 12) {
            target_artillery(1);
        }
    }

    private void hitting(int soldier){
        if(soldier < 5){
            points2 -= hit;
            player2.setText(String.valueOf(points2));
        }
        else {
            points1 -= hit;
            player1.setText(String.valueOf(points1));
        }
    }

    private void target(int soldier1, int soldier2){
        int max = 0;
        int hitter = 0;

        if(soldier1 > soldier2){
            max = max_moves-soldier_moves[soldier1]-soldier_moves[soldier2];
            hitter = 0;
        }
        else
        {
            max = max_moves-soldier_moves[soldier2]-soldier_moves[soldier1];
            hitter = 1;
        }
      //  testaus.setText(String.valueOf(max));
        if(max < 1 || dead[soldier2] == true) {
            hitting(soldier1);
            turn_ends();
            return;
        }

        random = rand.nextInt((max - 1) + 1) + 1;
        if(random == 1) {
            Intent i = new Intent(MapActivity.this, BattleActivity.class);
            i.putExtra("hit",1);
            i.putExtra("allied",choice1);
            i.putExtra("central",choice2);
            i.putExtra("hitter", hitter);
            died = soldier2;
            startActivityForResult(i,request);
        }
        else{
            Intent i = new Intent(MapActivity.this, BattleActivity.class);
            i.putExtra("hit",0);
            i.putExtra("allied",choice1);
            i.putExtra("central",choice2);
            i.putExtra("hitter", hitter);
            startActivityForResult(i, request);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(died != -1){
            death(died);
        }
        died = -1;
        turn_ends();
    }

    // 2,4,1,3,5
    // 4,3,1,2,5

    private void target_artillery(int artillery){
        random = rand.nextInt((4 - 0) + 1) + 0;

        if(artillery_moves[artillery] == -2 & artillery == 0 & random == 0){
            died = 8;
        }
        else if(artillery_moves[artillery] == -1 & artillery == 0 & random == 0){
            died = 7;
        }
        else if(artillery_moves[artillery] == 0 & artillery == 0 & random == 0){
            died = 5;
        }
        else if(artillery_moves[artillery] == 1 & artillery == 0 & random == 0){
            died = 6;
        }
        else if(artillery_moves[artillery] == 2 & artillery == 0 & random == 0){
            died = 9;
        }
        else if(artillery_moves[artillery] == -2 & artillery == 1 & random == 0){
            died = 1;
        }
        else if(artillery_moves[artillery] == -1 & artillery == 1 & random == 0){
            died = 3;
        }
        else if(artillery_moves[artillery] == 0 & artillery == 1 & random == 0){
            died = 0;
        }
        else if(artillery_moves[artillery] == 1 & artillery == 1 & random == 0){
            died = 2;
        }
        else if(artillery_moves[artillery] == 2 & artillery == 1 & random == 0){
            died = 4;
        }

        if(artillery == 0 & random != 0){
            Intent i = new Intent(MapActivity.this, BattleActivity.class);
            i.putExtra("hit",0);
            i.putExtra("allied",choice1);
            i.putExtra("central",choice2);
            i.putExtra("hitter",2);
            startActivityForResult(i, request);
        }
        else if(artillery == 1 & random != 0){
            Intent i = new Intent(MapActivity.this, BattleActivity.class);
            i.putExtra("hit",0);
            i.putExtra("allied",choice1);
            i.putExtra("central",choice2);
            i.putExtra("hitter",3);
            startActivityForResult(i, request);
        }
        if(artillery == 0 & random == 0){
            Intent i = new Intent(MapActivity.this, BattleActivity.class);
            i.putExtra("hit",1);
            i.putExtra("allied",choice1);
            i.putExtra("central",choice2);
            i.putExtra("hitter",2);
            startActivityForResult(i, request);
        }
        else if(artillery == 1 & random == 0){
            Intent i = new Intent(MapActivity.this, BattleActivity.class);
            i.putExtra("hit",1);
            i.putExtra("allied",choice1);
            i.putExtra("central",choice2);
            i.putExtra("hitter",3);
            startActivityForResult(i,request);
        }

    }

    public void death(int soldier){
        dead[soldier] = true;
        if(soldier == 0){
            asoldier1.setVisibility(View.INVISIBLE);
        }
        else if(soldier == 1){
            asoldier2.setVisibility(View.INVISIBLE);
        }
        else if(soldier == 2){
            asoldier3.setVisibility(View.INVISIBLE);
        }
        else if(soldier == 3){
            asoldier4.setVisibility(View.INVISIBLE);
        }
        else if(soldier == 4){
            asoldier5.setVisibility(View.INVISIBLE);
        }
        else if(soldier == 5){
            csoldier1.setVisibility(View.INVISIBLE);
        }
        else if(soldier == 6){
            csoldier2.setVisibility(View.INVISIBLE);
        }
        else if(soldier == 7){
            csoldier3.setVisibility(View.INVISIBLE);
        }
        else if(soldier == 8){
            csoldier4.setVisibility(View.INVISIBLE);
        }
        else if(soldier == 9){
            csoldier5.setVisibility(View.INVISIBLE);
        }

}

    private void healing(Boolean ai){
        if(ai == false & side == 0) {
            points2 += heal;
            player2.setText(String.valueOf(points2));
        } else if(ai == false & side == 1) {
            points1 += heal;
            player1.setText(String.valueOf(points1));
        } else if(ai == true & side == 0) {
            points1 += heal;
            player1.setText(String.valueOf(points1)); }
        else if(ai == true & side == 1) {
            points2 += heal;
            player2.setText(String.valueOf(points2)); }
    }

    private void ai() {

        // 1 = Soldier 1 moves
        // 2 = Soldier 2 moves
        // 3 = Soldier 3 moves
        // 4 = Soldier 4 moves
        // 5 = Soldier 5 moves
        // 6 = Artillery moves up
        // 7 = Artillery moves down   --------
        // 8 = Soldier 1 fires
        // 9 = Soldier 2 fires
        // 10 = Soldier 3 fires
        // 11 = Soldier 4 fires
        // 12 = Soldier 5 fires
        // 13 = Artillery fires

        random = rand.nextInt((13 - 1) + 1) + 1;
        int vice = random;

       // testaus.setText(String.valueOf(random)+String.valueOf(fired));


        if(random != 6 || random != 13 || random != 7){
            if(vice > 7){
                vice -= 7;
            }
            if(dead[vice-1] == true || soldier_moves[vice-1] == max_moves+1){
                healing(true);
                ai_end(2000);
                ++turn;
                return;
            }}

        else if(random == 6 & artillery_moves[0] == -2 & side == 0){
            healing(true);
            ai_end(2000);
            ++turn;
            return;
        }
        else if(random == 6 & artillery_moves[1] == -2 & side == 1){
            healing(true);
            ai_end(2000);
            ++turn;
            return;
        }
        else if(random == 7 & artillery_moves[0] == 2 & side == 0){
            healing(true);
            ai_end(2000);
            ++turn;
            return;
        }
        else if(random == 7 & artillery_moves[1] == 2 & side == 1){
            healing(true);
            ai_end(2000);
            ++turn;
            return;
        }
        ai_move(ai_time, random);
        ai_end(2000);
        ++turn;
    }

    private void ai_move(long time, int soldier) {
        ai_selection = soldier;
        aiHandler.postDelayed(new Runnable() {
            public void run() {

                // Moves

                if (ai_selection == 1 & side == 0) {
                    asoldier1.setX(asoldier1.getX() + soldier_speed);
                    ++soldier_moves[0];
                    passed(0);
                } else if (ai_selection == 1 & side == 1) {
                    csoldier1.setX(csoldier1.getX() - soldier_speed);
                    ++soldier_moves[5];
                    passed(5);
                } else if (ai_selection == 2 & side == 0) {
                    asoldier2.setX(asoldier2.getX() + soldier_speed);
                    ++soldier_moves[1];
                    passed(1);
                } else if (ai_selection == 2 & side == 1) {
                    csoldier2.setX(csoldier2.getX() - soldier_speed);
                    ++soldier_moves[6];
                    passed(6);
                } else if (ai_selection == 3 & side == 0) {
                    ++soldier_moves[2];
                    passed(2);
                    asoldier3.setX(asoldier3.getX() + soldier_speed);
                } else if (ai_selection == 3 & side == 1) {
                    csoldier3.setX(csoldier3.getX() - soldier_speed);
                    ++soldier_moves[7];
                    passed(7);
                } else if (ai_selection == 4 & side == 0) {
                    asoldier4.setX(asoldier4.getX() + soldier_speed);
                    ++soldier_moves[3];
                    passed(3);
               } else if (ai_selection == 4 & side == 1) {
                    csoldier4.setX(csoldier4.getX() - soldier_speed);
                    ++soldier_moves[8];
                    passed(8);
                } else if (ai_selection == 5 & side == 0) {
                    asoldier5.setX(asoldier5.getX() + soldier_speed);
                    ++soldier_moves[4];
                    passed(4);
                } else if (ai_selection == 5 & side == 1) {
                    csoldier5.setX(csoldier5.getX() - soldier_speed);
                    ++soldier_moves[9];
                    passed(9);
                } else if (ai_selection == 6 & side == 0) {
                    aartillery.setY(aartillery.getY() - artillery_speed);
                    --artillery_moves[0];
                } else if (ai_selection == 6 & side == 1) {
                    cartillery.setY(cartillery.getY() - artillery_speed);
                    --artillery_moves[1];
                } else if (ai_selection == 7 & side == 0) {
                    if(artillery_moves[0]==2){
                        healing(true); return;
                    }
                    aartillery.setY(aartillery.getY() + artillery_speed);
                    ++artillery_moves[0];
                } else if (ai_selection == 7 & side == 1) {
                    if(artillery_moves[1]==2){
                        healing(true); return;
                    }
                    cartillery.setY(cartillery.getY() + artillery_speed);
                    ++artillery_moves[1];
                }

                // fires

                else if (ai_selection == 8 & side == 0) {
                    fired_ai = true;
                    target(0, 5);}
                else if (ai_selection == 8 & side == 1) {
                    fired_ai = true;
                    target(5, 0);}
                else if (ai_selection == 9 & side == 0) {
                    fired_ai = true;
                    target(1, 8);}
                else if (ai_selection == 9 & side == 1) {
                    fired_ai = true;
                    target(6, 2);}
                else if (ai_selection == 10 & side == 0) {
                    fired_ai = true;
                    target(2, 6);}
                else if (ai_selection == 10 & side == 1) {
                    fired_ai = true;
                    target(7, 3);}
                else if (ai_selection == 11 & side == 0) {
                    fired_ai = true;
                    target(3, 7);}
                else if (ai_selection == 11 & side == 1) {
                    fired_ai = true;
                    target(8, 1);}
                else if (ai_selection == 12 & side == 0) {
                    fired_ai = true;
                    target(4, 9);}
                else if (ai_selection == 12 & side == 1) {
                    fired_ai = true;
                    target(9, 4);}
                else if (ai_selection == 13 & side == 0) {
                    fired_ai = true;
                    target_artillery(0);}
                else if (ai_selection == 13 & side == 1) {
                    fired_ai = true;
                    target_artillery(1);}
            }
        }, time);
    }

    private void ai_end(long time) {
        aiHandler.postDelayed(new Runnable() {
            public void run() {
                if(side == 0){
                    turn_flags(choice2);
                    unclick(false);

                }
                else if(side == 1){
                    turn_flags(choice1);
                    unclick(false);
                }
            }
        }, time);
    }


    private void passed(int soldier){

       // if(soldier_moves[soldier] == max_moves){
        //    dead[soldier] = true;
        //    if(soldier > 4){
       //         points1 -= 50;
       //         player1.setText(String.valueOf(points1));
        //    }
      //      else {
       //         points2 -= 50;
       //         player2.setText(String.valueOf(points2));
     //       }
    //    }
    }

    private void status(){
        Boolean dead1 = true;
        Boolean dead2 = true;

        if(points1 <= 0){
            Intent i = new Intent(MapActivity.this, EndActivity.class);
            i.putExtra("winner", choice2);
            startActivity(i);
        }
        if(points2 <= 0){
            Intent i = new Intent(MapActivity.this, EndActivity.class);
            i.putExtra("winner", choice1);
            startActivity(i);
        }
        for(int i = 0; i < 5; ++i){
            if(dead[i] == false){
                dead1 = false;
            }
        }
        for(int i = 5; i < 10; ++i){
            if(dead[i] == false){
                dead2 = false;
            }
        }
        if(dead1 == true){
            Intent i = new Intent(MapActivity.this, EndActivity.class);
            i.putExtra("winner", choice2);
            startActivity(i);
        }
        else if(dead2 == true){
            Intent i = new Intent(MapActivity.this, EndActivity.class);
            i.putExtra("winner",choice1);
            startActivity(i);
        }
    }

    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        finish();
        super.onBackPressed();  // optional depending on your needs
    }


    private void unclick(Boolean ai){
        if(ai == true){
            turnFlag.setClickable(false);
            ai_moves = true;
        }

        else if(ai == false){
            turnFlag.setClickable(true);
            ai_moves = false;
        }
    }

}
