package com.example.bai2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvThread1, tvThread2, tvThread3;
    private Button btnThread1, btnThread2, btnThread3;
    private boolean isThread1Running= false, isThread2Running=false, isThread3Running=false;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: // Thread 1
                    tvThread1.setText("Thread 1: " + msg.arg1);
                    break;
                case 2: // Thread 2
                    tvThread2.setText("Thread 2: " + msg.arg1);
                    break;
                case 3: // Thread 3
                    tvThread3.setText("Thread 3: " + msg.arg1);
                    break;
            }
        }
    };

    @SuppressLint("MissingInflatedId")
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

        tvThread1 = findViewById(R.id.tvThread1);
        tvThread2 = findViewById(R.id.tvThread2);
        tvThread3 = findViewById(R.id.tvThread3);
        btnThread1 = findViewById(R.id.btnThread1);
        btnThread2 = findViewById(R.id.btnThread2);
        btnThread3 = findViewById(R.id.btnThread3);

        btnThread1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isThread1Running) {
                    isThread1Running = true;
                    btnThread1.setText("Stop Thread 1");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Random random = new Random();
                            while (isThread1Running) {
                                int number = random.nextInt(51) + 50; // Số ngẫu nhiên từ 50-100
                                Message msg = handler.obtainMessage(1);
                                msg.arg1 = number;
                                handler.sendMessage(msg);
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
                } else {
                    isThread1Running = false;
                    btnThread1.setText("Start Thread 1");
                }
            }
        });

        btnThread2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isThread2Running)
                {
                    isThread2Running = true;
                    btnThread2.setText("Stop Thread 2");
                    new Thread(new Runnable() {
                        int oddnumber = 1;
                        @Override
                        public void run() {
                            while(isThread2Running)
                            {
                                Message msg = handler.obtainMessage(2);
                                msg.arg1 = oddnumber;
                                handler.sendMessage(msg);
                                oddnumber +=2;
                                try {

                                Thread.sleep(2500);
                                }catch (InterruptedException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
                }else
                {
                    isThread2Running = false;
                    btnThread2.setText("Start Thread 2");
                }
            }
        });

        btnThread3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isThread3Running)
                {
                    isThread3Running = true;
                    btnThread3.setText("Stop Thread 3");
                    new Thread(new Runnable() {
                        int number =0;
                        @Override
                        public void run() {
                            while(isThread3Running)
                            {
                               Message msg = handler.obtainMessage(3);
                                msg.arg1 = number;
                                handler.sendMessage(msg);
                                number++;
                                try {
                                    Thread.sleep(2000);;
                                }catch (InterruptedException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
                }else
                {
                    isThread3Running = false;
                    btnThread3.setText("Stop thread 3");
                }
            }
        });
    }
}