package com.example.fitnessandtraining.ui.training;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fitnessandtraining.data.local.entity.TrainingEntity;
import com.example.fitnessandtraining.data.repository.TrainingRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class TrainingViewModel extends ViewModel {
    private final TrainingRepository trainingRepository;

    // Инъекция через Hilt
    @Inject
    public TrainingViewModel(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    // Получение всех тренировок
    public LiveData<List<TrainingEntity>> getAllTrainings() {
        return trainingRepository.getAllTrainings();
    }

    // Получение тренировки по ID
    public LiveData<TrainingEntity> getTrainingById(long id) {
        return trainingRepository.getTrainingById(id);
    }

    // Добавление новой тренировки
    public void insertTraining(TrainingEntity training) {
        trainingRepository.insertTraining(training);
    }

    // Обновление информации о тренировке
    public void updateTraining(TrainingEntity training) {
        trainingRepository.updateTraining(training);
    }

    // Удаление тренировки
    public void deleteTraining (TrainingEntity training) {
        trainingRepository.deleteTraining(training);
    }
}
