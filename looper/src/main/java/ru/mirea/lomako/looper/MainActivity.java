package ru.mirea.lomako.looper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    MyLooper myLooper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myLooper = new MyLooper();
        myLooper.start();



    }
    public void onClick(View view){
        Message msg = new Message();
        Bundle bundle = new Bundle();

        final Runnable run = new Runnable() {
            @Override
            public void run() {

            }
        };
        Thread t = new Thread(new Runnable() {
            public void run() {
                Log.i("ThreadProject", "Запущен поток");
                long endTime = System.currentTimeMillis()
                        + 20 * 1000;
                while (System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime -
                                    System.currentTimeMillis());
                        } catch (Exception e) {
                        }
                    }
                }
                bundle.putString("KEY", "Student");
                msg.setData(bundle);
                if (myLooper != null) {
                    myLooper.handler.sendMessage(msg);
                }
                Log.d("ThreadProject", "20 год");
            }
        });
        t.start();


    }
}