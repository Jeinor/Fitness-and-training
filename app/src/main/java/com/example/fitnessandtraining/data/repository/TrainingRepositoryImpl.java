package com.example.fitnessandtraining.data.repository;

import androidx.lifecycle.LiveData;

import com.example.fitnessandtraining.data.local.dao.TrainingDao;
import com.example.fitnessandtraining.data.local.entity.TrainingEntity;

import java.util.List;

import javax.inject.Inject;

public class TrainingRepositoryImpl implements TrainingRepository {
    private final TrainingDao trainingDao;

    // Конструктор с @Inject для использования Hilt
    @Inject
    public TrainingRepositoryImpl(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    @Override
    public LiveData<List<TrainingEntity>> getAllTrainings() {
        return trainingDao.getAllTrainings();   // Вызов метода DAO
    }

    @Override
    public LiveData<TrainingEntity> getTrainingById(long id) {
        return trainingDao.getTrainingById(id);
    }

    @Override
    public void insertTraining(TrainingEntity training) {
        new Thread(() -> trainingDao.insertTraining(training)).start();
    }

    @Override
    public void updateTraining(TrainingEntity training) {
        new Thread(() -> trainingDao.updateTraining(training)).start(); // Операция выполняется в отдельном потоке
    }

    @Override
    public void deleteTraining(TrainingEntity training) {
        new Thread(() -> trainingDao.deleteTraining(training)).start();
    }
}
