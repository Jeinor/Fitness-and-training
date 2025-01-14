package com.example.fitnessandtraining.ui.training;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitnessandtraining.databinding.FragmentTrainingEditingBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TrainingEditingFragment extends Fragment {
    private FragmentTrainingEditingBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentTrainingEditingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Установка слушателя на поле времени
        binding.trainingDurationEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        // Установка слушателя на поле дпты
        binding.trainingDateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
    }

    private void showTimePicker() {
        // Создание MaterialTimePicker
        MaterialTimePicker picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H) // 24-часовой формат
                .setHour(0) // Начальные часы
                .setMinute(0) // Начальные минуты
                .setTitleText("Выберите время")
                .build();

        // Отображение TimePicker
        picker.show(getChildFragmentManager(), "TIME_PICKER");

        // Обработка выбор времени
        picker.addOnPositiveButtonClickListener(dialog -> {
            // Получение выбранное время
            @SuppressLint("DefaultLocale") String selectedTime = String.format("%02d:%02d", picker.getHour(), picker.getMinute());
            // Вставка выбранного время в поле training_duration_edit
            binding.trainingDurationEdit.setText(selectedTime);
        });
    }

    private void showDatePicker() {
        // Создание MaterialDatePicker
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Выберите дату")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        // Отображение DatePicker
        datePicker.show(getChildFragmentManager(), "DATE_PICKER");

        // Обработка выбора даты
        datePicker.addOnPositiveButtonClickListener(selection -> {
            // Форматирование выбранной даты
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            String selectedDate = sdf.format(new Date(selection));
            // Вставка выбранного время в поле training_date_edit
            binding.trainingDateEdit.setText(selectedDate);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}