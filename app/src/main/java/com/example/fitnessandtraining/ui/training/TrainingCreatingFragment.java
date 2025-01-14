package com.example.fitnessandtraining.ui.training;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fitnessandtraining.data.local.entity.TrainingEntity;
import com.example.fitnessandtraining.databinding.FragmentTrainingCreatingBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TrainingCreatingFragment extends Fragment {
   private FragmentTrainingCreatingBinding binding; // Для ViewBinding
   private TrainingViewModel trainingViewModel; // Подключаем ViewModel

   @Override
    public View onCreateView(
           @NonNull LayoutInflater inflater, ViewGroup container,
           Bundle savedInstanceState
   ) {
       binding = FragmentTrainingCreatingBinding.inflate(inflater, container, false);
       return binding.getRoot();
   }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Инициализация ViewModel для тренировки
        trainingViewModel = new ViewModelProvider(this).get(TrainingViewModel.class);

        // Установка обработчика для выбора времени
        binding.trainingDurationEdit.setOnClickListener(v -> showTimePicker());

        // Установка обработчика для выбора даты
        binding.trainingDateEdit.setOnClickListener(v -> showDatePicker());

        // Обработчик кнопки "Сохранить"
        binding.saveButton.setOnClickListener(v -> saveTraining());
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
            String selectedTime = String.format(Locale.US, "%02d:%02d", picker.getHour(), picker.getMinute());
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

    private void saveTraining() {
       // Получение данных из полей
        String trainingType = Objects.requireNonNull(binding.trainingTypeEdit.getText()).toString();
        String trainingDurationStr = Objects.requireNonNull(binding.trainingDurationEdit.getText()).toString();
        String trainingDateStr = Objects.requireNonNull(binding.trainingDateEdit.getText()).toString();

        // Проверка на заполнение всех полей
        if (trainingType.isEmpty() || trainingDurationStr.isEmpty() || trainingDateStr.isEmpty()) {
            Toast.makeText(getContext(), "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        // Преобразование времени в минуты
        int trainingDuration = convertTimeToMinutes(trainingDurationStr);

        // Преобразование даты в миллисекунды
        long trainingDate = convertDateToMillis(trainingDateStr);

        // Создание объекта TrainingEntity
        TrainingEntity training = new TrainingEntity(trainingType, trainingDuration, trainingDate);

        // Сохранение тренировки через ViewModel
        trainingViewModel.insertTraining(training);

        Log.i("fieldValue", trainingType + " " + trainingDuration + " " + trainingDate);

        // Уведомление пользователя
        Toast.makeText(getContext(), "Тренировка сохранена", Toast.LENGTH_SHORT).show();

        // Закрытие текущего фрагмента
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    // конвертирование выбранного времени в минуты
    private int convertTimeToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    // конвертирование выбранной даты в миллисекунды
    private long convertDateToMillis(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());

        try {
            Date parsedDate = sdf.parse(date);
            return parsedDate != null ? parsedDate.getTime() : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}