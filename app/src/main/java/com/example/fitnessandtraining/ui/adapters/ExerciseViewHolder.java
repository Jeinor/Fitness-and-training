package com.example.fitnessandtraining.ui.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessandtraining.data.local.entity.ExerciseEntity;
import com.example.fitnessandtraining.databinding.ItemExerciseBinding;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ExerciseViewHolder extends RecyclerView.ViewHolder {
    private final ItemExerciseBinding binding;

    public ExerciseViewHolder(@NonNull ItemExerciseBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    // Привязка данных к элементам интерфейса
    public void bind(ExerciseEntity exercise) {
        binding.itemExerciseName.setText(exercise.getExerciseName()); // Отображение названия упражнения
        binding.itemExerciseDateEdited.setText(formattedDate(exercise)); // Отображение даты редактирования
    }

    private String formattedDate (ExerciseEntity exercise) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
        return "Date Edited: " + dateFormat.format(exercise.getExerciseDateEdited());
    }
}
