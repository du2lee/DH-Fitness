package com.uou16.duhuifitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.uou16.duhuifitness.database.AppDatabase;
import com.uou16.duhuifitness.database.Fitness;
import com.uou16.duhuifitness.database.FitnessDao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JoggingActivity extends AppCompatActivity {

    TextView textCount;
    TextView time;
    TextView startTime;
    TextView endTime;
    SensorManager manager;
    SensorListener listener;
    int countStep;
    long start;
    long end;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogging);
        textCount = findViewById(R.id.textCount);
        time = findViewById(R.id.workOutTime);
        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);

        //Sensor
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        listener = new SensorListener();

        //database
        db = Room.databaseBuilder(this, AppDatabase.class, "fitness.db").build();
    }

    //시작버튼을 눌렀을 때
    public void onStartClick(View v) {
        startSensor();
    }

    //정지버튼을 눌렀을 때
    public void onStopClick(View v) {
        stopSensor();
        Toast.makeText(getApplicationContext(), "멈쳤습니다", Toast.LENGTH_SHORT).show();
    }

    //저장버튼을 눌렀을 때
    public void onSaveClick(View v) {
        stopSensor();
        Toast.makeText(getApplicationContext(), "저장합니다", Toast.LENGTH_SHORT).show();
        String date = sdf.format(new Date(System.currentTimeMillis()));
        new InsertAsyncTask(db.fitnessDao())
                .execute(new Fitness(date, "Jogging", textCount.getText().toString() + "step"));
    }

    public void startSensor() {
        countStep = 0;
        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        boolean chk = manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_FASTEST);
        if (chk == false) {
            Toast.makeText(getApplicationContext(), "모션센서를 이용할 수 없습니다.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "시작합니다", Toast.LENGTH_SHORT).show();
            start = System.currentTimeMillis();
            String current = textFormat.format(new Date(start));
            time.setText("00:00");
            startTime.setText(current);
            endTime.setText("-");
        }
    }

    public void stopSensor() {
        manager.unregisterListener(listener);
        if (endTime.getText().toString().equals("-")) {
            end = System.currentTimeMillis();
            String current = textFormat.format(new Date(end));
            time.setText(timeFormat.format(end - start));
            endTime.setText(current);
        }
    }

    //sensor data 받기 listener 설정
    class SensorListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {            //센서변화
            if (event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
                if (event.values[0] == 1.0f) {
                    countStep++;
                    textCount.setText(String.valueOf(countStep));
                }
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {//감도변화
        }
    }

    //insert 비동기처리
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
}