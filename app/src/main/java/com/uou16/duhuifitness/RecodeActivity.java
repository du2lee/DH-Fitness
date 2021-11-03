package com.uou16.duhuifitness;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.uou16.duhuifitness.database.AppDatabase;
import com.uou16.duhuifitness.database.Fitness;
import com.uou16.duhuifitness.database.FitnessDao;

import java.util.ArrayList;
import java.util.List;

public class RecodeActivity extends AppCompatActivity {

    ListView listView;
    AppDatabase db;
    Integer[] images;
    String[] types;
    String[] counts;
    String[] dates;
    String[] primaryKeys;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recode);

        listView = findViewById(R.id.listView);

        //database
        db = Room.databaseBuilder(this, AppDatabase.class, "fitness.db").allowMainThreadQueries().build();

        //datebase 갱신 시
        db.fitnessDao().getAll().observe(this, new Observer<List<Fitness>>() {
            @Override
            public void onChanged(List<Fitness> fitnesses) {
                getData(fitnesses);
                CustomList adapter = new CustomList(RecodeActivity.this);
                listView.setAdapter(adapter);
            }
        });

        //선택 시
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String selectItem = parent.getAdapter().getItem(position).toString();
                db.fitnessDao().delete(Integer.parseInt(selectItem));
                Toast.makeText(getApplicationContext(), "삭제되었습니다", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    public void getData(List<Fitness> fitnesses) {
        int len = fitnesses.size();
        images = new Integer[len];
        types = new String[len];
        counts = new String[len];
        dates = new String[len];
        primaryKeys = new String[len];
        int idx = 0;
        for (Fitness fitness : fitnesses) {
            if (fitness.getType().toString().equals("push-up")) {
                images[idx] = R.drawable.pushup;
            } else if (fitness.getType().toString().equals("Jogging")) {
                images[idx] = R.drawable.jogging;
            }
            types[idx] = fitness.getType().toString();
            counts[idx] = fitness.getActivity().toString();
            dates[idx] = fitness.getDate().toString();
            primaryKeys[idx] = String.valueOf(fitness.getId());
            idx += 1;
        }
    }

    public class CustomList extends ArrayAdapter<String> {
        private final Activity context;

        public CustomList(Activity context) {
            super(context, R.layout.listitem, primaryKeys);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.listitem, null, true);
            ImageView imageView = rowView.findViewById(R.id.image);
            TextView type = rowView.findViewById(R.id.title);
            TextView count = rowView.findViewById(R.id.count);
            TextView date = rowView.findViewById(R.id.date);
            TextView primaryKey = rowView.findViewById(R.id.primaryKey);

            imageView.setImageResource(images[position]);
            type.setText(types[position]);
            count.setText(counts[position]);
            date.setText(dates[position]);
            primaryKey.setText(primaryKeys[position]);
            return rowView;
        }
    }
}