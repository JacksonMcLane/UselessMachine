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
    }

    private void setListeners() {
        selfDestruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CountDownTimer(10000, 300) {
                    int counter = 0;
                    int multiplier = 0;
                    int subtract = 1;
                    boolean colored = false;
                    @Override
                    public void onFinish() {
                        //selfDestruct.setText("Self Destruct");
                        finish();
                    }

                    @Override
                    public void onTick(long l) {
                        selfDestruct.setText(String.valueOf(l / 1000));
                        counter++;
                        if (l % 1000 == 0 || l % 1000 == 5) {
                            multiplier++;
                            subtract = 30 * multiplier;
                        }
                        if(!colored && counter % 2 == 0) {
                            new CountDownTimer(300 - subtract, 300 - subtract) {
                                @Override
                                public void onFinish() {
                                    int red = 255;
                                    int blue = 255;
                                    int green = 255;
                                    int original = Color.rgb(red, blue, green);
                                    constraintLayout.setBackgroundColor(original);
                                    colored = false;
                                }

                                @Override
                                public void onTick(long l) {
                                    colored = true;
                                    int r = 178;
                                    int g = 34;
                                    int b = 34;
                                    int red = Color.rgb(r, g, b);
                                    constraintLayout.setBackgroundColor(red);
                                }
                            }.start();
                        }
                    }
                }.start();
            }
        });

        lookBusy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                useless.setVisibility(View.GONE);
                selfDestruct.setVisibility(View.GONE);
                lookBusy.setVisibility(View.GONE);
                progress.setVisibility(View.VISIBLE);
                new CountDownTimer(5000, 40) {

                    @Override
                    public void onFinish() {
                        progressBar.setVisibility(View.INVISIBLE);
                        useless.setVisibility(View.VISIBLE);
                        selfDestruct.setVisibility(View.VISIBLE);
                        lookBusy.setVisibility(View.VISIBLE);
                        progress.setVisibility(View.INVISIBLE);
                        progressBar.setProgress(0);
                    }

                    @Override
                    public void onTick(long l) {
                        progressBar.incrementProgressBy(1);
                        progress.setText("Loading - " + String.valueOf(progressBar.getProgress()) + "%");
                    }
                }.start();
            }
        });

        useless.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, final boolean isChecked) {
                if (isChecked) {
                    int time = (int) (Math.random() * 2000);
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
