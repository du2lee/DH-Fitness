package com.uou16.duhuifitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView pushUp;
    ImageView jogging;
    ImageView recode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_DUHUIFITNESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pushUp = findViewById(R.id.btnPushUp);
        jogging = findViewById(R.id.btnJogging);
        recode = findViewById(R.id.btnRecode);

        pushUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PushUpActivity.class);
                startActivity(intent);
            }
        });

        jogging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JoggingActivity.class);
                startActivity(intent);
            }
        });

        recode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecodeActivity.class);
                startActivity(intent);
            }
        });
    }
}