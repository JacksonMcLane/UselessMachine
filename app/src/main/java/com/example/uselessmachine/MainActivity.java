package com.example.uselessmachine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Switch useless;
    private Button selfDestruct;
    private Button lookBusy;
    private ProgressBar progressBar;
    private ConstraintLayout constraintLayout;
    private TextView progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();
        setListeners();
        updateAndRandomize();
    }

    private void updateAndRandomize() {

    }

    private void setListeners() {
        selfDestruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CountDownTimer(10000, 10) {
                    @Override
                    public void onFinish() {
                        finish();
                    }

                    @Override
                    public void onTick(long l) {
                        selfDestruct.setText(String.valueOf(l / 1000));
                        int subtract = 0;
                        if (l / 1000 % 10 == 0) {
                            subtract = 10 * (((int) (10000 - l)) / 1000);
                        }
                        new CountDownTimer(300 - subtract, 300 - subtract) {
                            @Override
                            public void onFinish() {
                                int red = 255;
                                int blue = 255;
                                int green = 255;
                                int original = Color.rgb(red, blue, green);
                                constraintLayout.setBackgroundColor(original);
                            }

                            @Override
                            public void onTick(long l) {
                                int r = 178;
                                int g = 34;
                                int b = 34;
                                int red = Color.rgb(r, g, b);
                                constraintLayout.setBackgroundColor(red);
                            }
                        }.start();
                    }
                }.start();
            }
        });

        lookBusy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.getVisibility();
            }
        });

        useless.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, final boolean isChecked) {
                if (isChecked) {
                    int time = (int) (Math.random() * 3000);
                    new CountDownTimer(time, 10) {
                        public void onFinish() {
                            useless.setChecked(false);
                        }

                        @Override
                        public void onTick(long l) {
                            if (!useless.isChecked()) {
                                cancel(); //cancels timer if user switches the switch off
                            }
                        }
                    }.start();
                }
//                if(isChecked) {
//                    Toast.makeText(MainActivity.this, "ON", Toast.LENGTH_SHORT).show();
//                }
//                else
//                    Toast.makeText(MainActivity.this, "OFF", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void wireWidgets() {
        useless = findViewById(R.id.switch_main_useless);
        selfDestruct = findViewById(R.id.button_main_self_destruct);
        lookBusy = findViewById(R.id.button_main_look_busy);
        progressBar = findViewById(R.id.progressBar_main_progress);
        constraintLayout = findViewById(R.id.constraintlayout_main);
        progress = findViewById(R.id.textView_main_progress);
    }


}
