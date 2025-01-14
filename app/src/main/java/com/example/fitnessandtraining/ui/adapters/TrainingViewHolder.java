package com.example.fitnessandtraining.ui.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessandtraining.data.local.entity.TrainingEntity;
import com.example.fitnessandtraining.databinding.ItemCardViewBinding;

import java.text.SimpleDateFormat;
import java.util.Locale;

// класс ViewHolder для элемента списка
public class TrainingViewHolder extends RecyclerView.ViewHolder {
    private final ItemCardViewBinding binding;

    public TrainingViewHolder(@NonNull ItemCardViewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    // Привязка данных к элементам интерфейса
    public void bind(TrainingEntity training) {
        // Установка типа тренировки
        binding.itemCardTrainingType.setText(training.getTrainingType());

        // Установка продолжительности тренировки
        binding.itemCardTrainingDuration.setText(formattedTime(training));

        // Установка даты тренировки
        binding.itemCardTrainingDate.setText(formattedDate(training));
    }

    // Форматирование времени
    private String formattedTime (TrainingEntity training) {

        long hours = training.getTrainingDuration() / 60;
        long minutes = training.getTrainingDuration() % 60;

        if (minutes == 0) {
            return String.format(Locale.getDefault(), "%d hours", hours);
        }
        return String.format(Locale.getDefault(), "%d hours %d minutes", hours, minutes);
    }

    // Форматирование даты
    private String formattedDate (TrainingEntity training) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return dateFormat.format(training.getTrainingDate());
    }
}
