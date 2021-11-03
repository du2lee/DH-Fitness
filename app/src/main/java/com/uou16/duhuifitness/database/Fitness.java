package com.uou16.duhuifitness.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Fitness {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "activity")
    private String activity;

    public Fitness(String date, String type, String activity) {
        this.date = date;
        this.type = type;
        this.activity = activity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "Fitness{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", activity='" + activity + '\'' +
                '}';
    }
}
