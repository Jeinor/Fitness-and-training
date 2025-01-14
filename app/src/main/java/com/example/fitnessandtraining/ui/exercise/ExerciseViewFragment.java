package com.example.fitnessandtraining.ui.exercise;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitnessandtraining.data.local.entity.ExerciseEntity;
import com.example.fitnessandtraining.data.local.entity.TrainingEntity;
import com.example.fitnessandtraining.databinding.FragmentExerciseViewBinding;

import java.text.SimpleDateFormat;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ExerciseViewFragment extends Fragment {
    private FragmentExerciseViewBinding binding;
    private long exerciseId = -1;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Инициализация View Binding
        binding = FragmentExerciseViewBinding.inflate(inflater, container, false);

        // Получение переданного id
        assert getArguments() != null;
        exerciseId = getArguments().getLong("exerciseId", -1);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Инициализация ViewModel работы с данными для упражнений через Hilt
        ExerciseViewModel exerciseViewModel = new ViewModelProvider(this).get(ExerciseViewModel.class);

        // Загрузка данных выбранного упражнения
        exerciseViewModel.getExerciseById(exerciseId).observe(getViewLifecycleOwner(), exercise -> {
            if (exercise != null) {
                binding.exerciseName.setText(exercise.getExerciseName());
                binding.exerciseDescription.setText(exercise.getExerciseDescription());
                binding.exerciseDateEdited.setText(formattedDate(exercise));
            }
        });
    }

    // Форматирование даты
    private String formattedDate (ExerciseEntity exercise) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
        return "Date Edited: " + dateFormat.format(exercise.getExerciseDateEdited());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}