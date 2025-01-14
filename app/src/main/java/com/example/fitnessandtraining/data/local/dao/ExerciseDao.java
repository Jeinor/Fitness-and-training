package com.example.fitnessandtraining.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fitnessandtraining.data.local.entity.ExerciseEntity;

import java.util.List;

// Интерфейс DAO (Data Access Objects) для упражнения, содержащий методы для работы с базой данных
@Dao
public interface ExerciseDao {
    @Query("SELECT * FROM exercises WHERE training_id = :trainingId")   // SQL-запрос на выбор всех записей
    LiveData<List<ExerciseEntity>> getExercisesForTraining(long trainingId);

    @Query("SELECT * FROM exercises WHERE exerciseId = :id")    // SQL-запрос на выбор записи по id
    LiveData<ExerciseEntity> getExerciseById(long id);

    @Insert
    void insertExercise(ExerciseEntity exercise);   // Метод для добавления новой записи в таблицу exercises

    @Update
    void updateExercise(ExerciseEntity exercise);   // Метод для обновления существующей записи

    @Delete
    void deleteExercise(ExerciseEntity exercise);   // Метод для удаления записи
}
