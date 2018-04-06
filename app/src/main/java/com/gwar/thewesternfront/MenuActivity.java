package com.gwar.thewesternfront;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

/**
 * Created by Petri on 19.7.2016.
 */
public class MenuActivity  extends AppCompatActivity {

    private Spinner spinner1;
    private String[] allied = { "France","United Kingdom","USA"};
    private Spinner spinner2;
    private String[] central = { "Germany","Austria-Hungary"};
    private ImageView allied_flag;
    private ImageView central_flag;
    private int choice1 = 1;
    private int choice2 = 4;
    private int is_allied = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        allied_flag = (ImageView) findViewById(R.id.flag1);
        central_flag = (ImageView) findViewById(R.id.flag2);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allied);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter2, View v,
                                       int position, long id) {
                if (position == 0) {
                    allied_flag.setImageResource(R.drawable.france);
                    choice1 = 1;
                }
                else if (position == 1) {
                    allied_flag.setImageResource(R.drawable.uk);
                    choice1 = 2;
                }
                else if (position == 2 ) {
                    allied_flag.setImageResource(R.drawable.usa);
                    choice1 = 3;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, central);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter2, View v,
                                       int position, long id) {
                if (position == 0) {
                    central_flag.setImageResource(R.drawable.germany);
                    choice2 = 4;
                } else if (position == 1) {
                    central_flag.setImageResource(R.drawable.austria);
                    choice2 = 5;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        allied_flag.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, MapActivity.class);
                i.putExtra("allied", choice1);
                i.putExtra("central",choice2);
                i.putExtra("side",is_allied);
                startActivity(i);
            }
        });
        central_flag.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                is_allied = 0;
                Intent i = new Intent(MenuActivity.this, MapActivity.class);
                i.putExtra("allied", choice1);
                i.putExtra("central",choice2);
                i.putExtra("side",is_allied);
                startActivity(i);
            }
        });
    }
}
