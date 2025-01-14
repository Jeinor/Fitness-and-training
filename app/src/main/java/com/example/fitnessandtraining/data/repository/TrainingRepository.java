package com.example.fitnessandtraining.data.repository;

import androidx.lifecycle.LiveData;

import com.example.fitnessandtraining.data.local.entity.TrainingEntity;

import java.util.List;

// Интерфейс репозитория тренировки
public interface TrainingRepository {
    LiveData<List<TrainingEntity>> getAllTrainings();   // Получить все тренировки

    LiveData<TrainingEntity> getTrainingById(long id);  // Получить тренировку по id

    void insertTraining(TrainingEntity training);   // Добавить тренировку

    void updateTraining(TrainingEntity training);   // Обновить тренировку

    void deleteTraining(TrainingEntity training);   // Удалить тренировку
}
