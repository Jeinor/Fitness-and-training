package com.example.fitnessandtraining.ui.exercise;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fitnessandtraining.data.local.entity.ExerciseEntity;
import com.example.fitnessandtraining.databinding.FragmentExerciseCreatingBinding;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ExerciseCreatingFragment extends Fragment {
    private FragmentExerciseCreatingBinding binding; // Для ViewBinding
    private ExerciseViewModel exerciseViewModel; // Подключаем ViewModel
    private long trainingId = -1; // id тренировки

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Инициализация View Binding
        binding = FragmentExerciseCreatingBinding.inflate(inflater, container, false);

        // Получение переданного ID
        assert getArguments() != null;
        trainingId = getArguments().getLong("trainingId", -1);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Инициализация ViewModel для упражнения
        exerciseViewModel = new ViewModelProvider(this).get(ExerciseViewModel.class);

        // Обработчик кнопки "Сохранить"
        binding.saveButton.setOnClickListener(v -> saveTraining());
    }

    private void saveTraining() {
        // Получение данных из полей
        String exerciseName = Objects.requireNonNull(binding.exerciseNameEdit.getText()).toString();
        String exerciseDescription = Objects.requireNonNull(binding.exerciseDescriptionEdit.getText()).toString();

        // Проверка на заполнение всех полей
        if (exerciseName.isEmpty() || exerciseDescription.isEmpty()) {
            Toast.makeText(getContext(), "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        // Получение текущей даты
        long exerciseDateEdited = System.currentTimeMillis();

        // Создание объекта TrainingEntity
        ExerciseEntity exercise = new ExerciseEntity(exerciseName, exerciseDescription, exerciseDateEdited, trainingId);

        // Сохранение тренировки через ViewModel
        exerciseViewModel.insertExercise(exercise);

        Log.i("fieldValue", exerciseName + " " + exerciseDescription + " " + exerciseDateEdited + " " + trainingId);

        // Уведомление пользователя
        Toast.makeText(getContext(), "Упражнение сохранено", Toast.LENGTH_SHORT).show();

        // Закрытие текущего фрагмента
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}