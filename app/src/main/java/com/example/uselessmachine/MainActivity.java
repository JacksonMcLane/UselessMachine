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

public class MainActivity extends AppCompatActivity {

    private Switch switchUseless;
    private Button buttonSelfDestruct;
    private Button buttonLookBusy;
    private ProgressBar progressBar;
    private ConstraintLayout constraintLayout;
    private TextView textViewProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();
        setListeners();
    }

    private void setListeners() {
        buttonSelfDestruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CountDownTimer(10000, 300) {
                    int counter = 0;
                    int multiplier = 0;
                    int subtract = 1;
                    boolean colored = false;
                    @Override
                    public void onFinish() {
                        finish();
                    }

                    @Override
                    public void onTick(long l) {
                        buttonSelfDestruct.setText(String.valueOf(l / 1000));
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

        buttonLookBusy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                switchUseless.setVisibility(View.GONE);
                buttonSelfDestruct.setVisibility(View.GONE);
                buttonLookBusy.setVisibility(View.GONE);
                textViewProgress.setVisibility(View.VISIBLE);
                new CountDownTimer(5000, 40) {

                    @Override
                    public void onFinish() {
                        progressBar.setVisibility(View.INVISIBLE);
                        switchUseless.setVisibility(View.VISIBLE);
                        buttonSelfDestruct.setVisibility(View.VISIBLE);
                        buttonLookBusy.setVisibility(View.VISIBLE);
                        textViewProgress.setVisibility(View.INVISIBLE);
                        progressBar.setProgress(0);
                    }

                    @Override
                    public void onTick(long l) {
                        progressBar.incrementProgressBy(1);
                        textViewProgress.setText("Loading - " + String.valueOf(progressBar.getProgress()) + "%");
                    }
                }.start();
            }
        });

        switchUseless.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, final boolean isChecked) {
                if (isChecked) {
                    int time = (int) (Math.random() * 2000);
                    new CountDownTimer(time, 10) {
                        public void onFinish() {
                            switchUseless.setChecked(false);
                        }

                        @Override
                        public void onTick(long l) {
                            if (!switchUseless.isChecked()) {
                                cancel(); //cancels timer if user switches the switch off
                            }
                        }
                    }.start();
                }

            }
        });


    }

    private void wireWidgets() {
        switchUseless = findViewById(R.id.switch_main_useless);
        buttonSelfDestruct = findViewById(R.id.button_main_self_destruct);
        buttonLookBusy = findViewById(R.id.button_main_look_busy);
        progressBar = findViewById(R.id.progressBar_main_progress);
        constraintLayout = findViewById(R.id.constraintlayout_main);
        textViewProgress = findViewById(R.id.textView_main_progress);
    }


}
