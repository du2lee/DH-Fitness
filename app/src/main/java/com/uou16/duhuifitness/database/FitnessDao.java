package com.uou16.duhuifitness.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface  FitnessDao {
    @Query("SELECT * FROM Fitness")
    LiveData<List<Fitness>> getAll();

    @Insert
    void insert(Fitness fitness);

    @Query("DELETE FROM Fitness WHERE id = :id")
    void delete(int id);

}
