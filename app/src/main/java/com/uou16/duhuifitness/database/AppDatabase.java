package com.uou16.duhuifitness.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Fitness.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FitnessDao fitnessDao();
}
