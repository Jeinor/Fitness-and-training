package com.example.fitnessandtraining.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fitnessandtraining.data.local.entity.TrainingEntity;

import java.util.List;

// Интерфейс DAO (Data Access Objects) для тренировки, содержащий методы для работы с базой данных
@Dao
public interface TrainingDao {
    @Query("SELECT * FROM trainings")   // SQL-запрос на выбор всех записей
    LiveData<List<TrainingEntity>> getAllTrainings();

    @Query("SELECT * FROM trainings WHERE TrainingId = :id")    // SQL-запрос на выбор записи по id
    LiveData<TrainingEntity> getTrainingById(long id);

    @Insert
    void insertTraining(TrainingEntity training);   // Метод для добавления новой записи в таблицу trainings

    @Update
    void updateTraining(TrainingEntity training);   // Метод для обновления существующей записи

    @Delete
    void deleteTraining(TrainingEntity training);   // Метод для удаления записи
}
