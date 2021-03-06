package com.uou16.duhuifitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Dialog;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uou16.duhuifitness.database.AppDatabase;
import com.uou16.duhuifitness.database.Fitness;
import com.uou16.duhuifitness.database.FitnessDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PushUpActivity extends AppCompatActivity {

    TextView textGoal;
    TextView textCount;
    LinearLayout ing;
    SensorManager manager;
    SensorListener listener;
    int goal;
    int countNum;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    AppDatabase db;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_up);
        textGoal = findViewById(R.id.textGoal);
        textCount = findViewById(R.id.textCount);
        ing = findViewById(R.id.ing);

        //sensor
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        listener = new SensorListener();

        //database
        db = Room.databaseBuilder(this, AppDatabase.class, "fitness.db").build();

        //tts
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.KOREAN);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(getApplicationContext(), "???????????? ?????? ?????? ?????????.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    //??????????????? ????????? ???
    public void onStartClick(View v) {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.pushupdialog);
        dialog.setTitle("????????????");

        Button summit = dialog.findViewById(R.id.summit);
        Button cancel = dialog.findViewById(R.id.cancel);
        final EditText count = dialog.findViewById(R.id.count);

        summit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textGoal.setText(new StringBuilder().append(count.getText().toString()).append("???"));
                Toast.makeText(getApplicationContext(), "???????????????", Toast.LENGTH_SHORT).show();
                goal = Integer.parseInt(count.getText().toString());
                dialog.dismiss();
                startSensor();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "???????????????!!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //??????????????? ????????? ???
    public void onStopClick(View v) {
        stopSensor();
        Toast.makeText(getApplicationContext(), "???????????????", Toast.LENGTH_SHORT).show();
    }

    //??????????????? ????????? ???
    public void onSaveClick(View v) {
        stopSensor();
        Toast.makeText(getApplicationContext(), "???????????????", Toast.LENGTH_SHORT).show();
        String date = sdf.format(new Date(System.currentTimeMillis()));
        new InsertAsyncTask(db.fitnessDao())
                .execute(new Fitness(date, "push-up", textCount.getText().toString() + "???"));
    }

    public void startSensor() {
        countNum = 0;
        ing.setBackgroundResource(R.drawable.circle_1);
        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        boolean chk = manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI);
        if (chk == false) {
            Toast.makeText(getApplicationContext(), "??????????????? ????????? ??? ????????????", Toast.LENGTH_SHORT).show();
        }
    }

    public void stopSensor() {
        manager.unregisterListener(listener);
    }

    //sensor data ?????? listener ??????
    class SensorListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {//????????????
            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                if (event.values[0] >= 7.9) {
                    String num = "" + countNum;
                    textCount.setText(num);
                    if (!num.equals("0")) speakCount(num);
                    countNum += 1;
                    if (goal < countNum) {
                        ing.setBackgroundResource(R.drawable.circle_5);
                    } else if (goal * 0.75 < countNum) {
                        ing.setBackgroundResource(R.drawable.circle_4);
                    } else if (goal * 0.5 < countNum) {
                        ing.setBackgroundResource(R.drawable.circle_3);
                    } else if (goal * 0.25 < countNum) {
                        ing.setBackgroundResource(R.drawable.circle_2);
                    }
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {//????????????

        }
    }

    //insert ???????????????
    private static class InsertAsyncTask extends AsyncTask<Fitness, Void, Void> {
        private FitnessDao mFitnessDao;

        public InsertAsyncTask(FitnessDao fitnessDao) {
            this.mFitnessDao = fitnessDao;
        }

        @Override
        protected Void doInBackground(Fitness... fitnesses) {
            mFitnessDao.insert(fitnesses[0]);
            return null;
        }
    }

    private void speakCount(String text) {
        //tts.setPitch((float) 0.1); //??????
        //tts.setSpeechRate((float) 0.5); //????????????
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

}