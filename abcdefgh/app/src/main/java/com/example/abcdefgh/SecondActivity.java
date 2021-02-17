package com.example.abcdefgh;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.abcdefgh.server.Server;

import java.io.IOException;
import java.io.ObjectInputStream;

public class SecondActivity extends AppCompatActivity {

    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        info = findViewById(R.id.terv);

        new Thread(new Te()).start();
    }

    private class Te implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {

                        Activity activity = null;
                        final String message = (String) Server.getInstance(activity).objectInputStream.readObject();
                        if (message != null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    info.append(message);
                                }
                            });
                        }

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
