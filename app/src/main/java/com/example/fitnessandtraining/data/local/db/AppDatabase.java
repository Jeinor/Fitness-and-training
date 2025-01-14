package com.example.fitnessandtraining.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.fitnessandtraining.data.local.dao.ExerciseDao;
import com.example.fitnessandtraining.data.local.dao.TrainingDao;
import com.example.fitnessandtraining.data.local.entity.ExerciseEntity;
import com.example.fitnessandtraining.data.local.entity.TrainingEntity;

// Создание базы данных
@Database(entities = {TrainingEntity.class, ExerciseEntity.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    // Методы для доступа к DAO
    public abstract TrainingDao trainingDao();
    public abstract ExerciseDao exerciseDao();
}
