package com.example.bai3;

import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TimePicker timePicker;
    private EditText timeInterval;
    private Button btnSetAlarm, btnStopAlarm;
    private Vibrator vibrator;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        timeInterval = findViewById(R.id.timeInterval);
        btnSetAlarm = findViewById(R.id.btnSetAlarm);
        btnStopAlarm = findViewById(R.id.btnStopAlarm);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        timePicker = findViewById(R.id.timePicker);

        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                int interval = Integer.parseInt(timeInterval.getText().toString());

                Calendar alarmTime = Calendar.getInstance();
                alarmTime.set(Calendar.HOUR_OF_DAY, hour);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startVibrating(interval);
                    }
                }, alarmTime.getTimeInMillis()-System.currentTimeMillis());
            }
        });

        btnStopAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.cancel(); // Dừng rung
            }
        });
    }

    private void startVibrating(int interval)
    {
        long[] pattern = {0,1000,1000};
        vibrator.vibrate(pattern, 0);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                vibrator.cancel();
            }
        }, interval * 60 * 1000);
    }
}