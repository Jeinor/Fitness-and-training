package com.example.fitnessandtraining.data.repository;

import androidx.lifecycle.LiveData;

import com.example.fitnessandtraining.data.local.entity.ExerciseEntity;

import java.util.List;

// Интерфейс репозитория упражнения
public interface ExerciseRepository {
    LiveData<List<ExerciseEntity>> getExercisesForTraining(long trainingId);    // Получить все упражнения для конкретной тренировки

    LiveData<ExerciseEntity> getExerciseById(long id);  // Получить упражнение по id

    void insertExercise(ExerciseEntity exercise);   // Добавить упражнение

    void updateExercise(ExerciseEntity exercise);   // Обновить упражнение

    void deleteExercise(ExerciseEntity exercise);   // Удалить упражнение
}
