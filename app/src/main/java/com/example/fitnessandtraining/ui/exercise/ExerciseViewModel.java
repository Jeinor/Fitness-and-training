package com.example.fitnessandtraining.ui.exercise;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fitnessandtraining.data.local.entity.ExerciseEntity;
import com.example.fitnessandtraining.data.repository.ExerciseRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ExerciseViewModel extends ViewModel {
    private final ExerciseRepository exerciseRepository;

    // Инъекция через Hilt
    @Inject
    public ExerciseViewModel(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    // Получение упражнений для конкретной тренировки
    public LiveData<List<ExerciseEntity>> getExercisesForTraining(long trainingId) {
        return exerciseRepository.getExercisesForTraining(trainingId);
    }

    // Получение упражнения по id
    public LiveData<ExerciseEntity> getExerciseById(long id) {
        return exerciseRepository.getExerciseById(id);
    }

    // Добавление упражнения
    public void insertExercise(ExerciseEntity exercise) {
        exerciseRepository.insertExercise(exercise);
    }

    // Обновление тренировки
    public void updateExercise(ExerciseEntity exercise) {
        exerciseRepository.updateExercise(exercise);
    }

    // Удаление тренировки
    public void deleteExercise(ExerciseEntity exercise) {
        exerciseRepository.deleteExercise(exercise);
    }

}
