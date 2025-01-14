package com.example.fitnessandtraining.data.repository;

import androidx.lifecycle.LiveData;

import com.example.fitnessandtraining.data.local.dao.ExerciseDao;
import com.example.fitnessandtraining.data.local.entity.ExerciseEntity;

import java.util.List;

import javax.inject.Inject;

public class ExerciseRepositoryImpl implements ExerciseRepository {
    private final ExerciseDao exerciseDao;

    // Конструктор с @Inject для использования Hilt
    @Inject
    public ExerciseRepositoryImpl(ExerciseDao exerciseDao) {
        this.exerciseDao = exerciseDao;
    }

    @Override
    public LiveData<List<ExerciseEntity>> getExercisesForTraining(long trainingId) {
        return exerciseDao.getExercisesForTraining(trainingId);
    }

    @Override
    public LiveData<ExerciseEntity> getExerciseById(long id) {
        return exerciseDao.getExerciseById(id);
    }

    @Override
    public void insertExercise(ExerciseEntity exercise) {
        new Thread(() -> exerciseDao.insertExercise(exercise)).start();
    }

    @Override
    public void updateExercise(ExerciseEntity exercise) {
        new Thread(() -> exerciseDao.updateExercise(exercise)).start();
    }

    @Override
    public void deleteExercise(ExerciseEntity exercise) {
        new Thread(() -> exerciseDao.deleteExercise(exercise)).start();
    }
}
