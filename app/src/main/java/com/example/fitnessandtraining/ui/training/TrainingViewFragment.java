package com.example.fitnessandtraining.ui.training;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitnessandtraining.R;
import com.example.fitnessandtraining.data.local.entity.TrainingEntity;
import com.example.fitnessandtraining.databinding.FragmentTrainingViewBinding;
import com.example.fitnessandtraining.ui.adapters.ExerciseListAdapter;
import com.example.fitnessandtraining.ui.exercise.ExerciseViewModel;

import java.text.SimpleDateFormat;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TrainingViewFragment extends Fragment {
    private FragmentTrainingViewBinding binding;
    private long trainingId = -1;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Инициализация View Binding
        binding = FragmentTrainingViewBinding.inflate(inflater, container, false);

        // Получение переданного ID
        assert getArguments() != null;
        trainingId = getArguments().getLong("trainingId", -1);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Инициализация ViewModel для работы с данными тренировок через Hilt
        TrainingViewModel trainingViewModel = new ViewModelProvider(this).get(TrainingViewModel.class);

        // Инициализация ViewModel работы с данными для упражнений через Hilt
        ExerciseViewModel exerciseViewModel = new ViewModelProvider(this).get(ExerciseViewModel.class);

        // Загрузка данных выбранной тренировки
        trainingViewModel.getTrainingById(trainingId).observe(getViewLifecycleOwner(), training -> {
            if (training != null) {
                binding.trainingType.setText(formattedType(training));
                binding.trainingDuration.setText(formattedTime(training));
                binding.trainingDate.setText(formattedDate(training));
            }
        });

        // Настройка RecyclerView
        ExerciseListAdapter adapter = new ExerciseListAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter); // Устанавливаем адаптер

        // Наблюдение за данными упражнений
        exerciseViewModel.getExercisesForTraining(trainingId).observe(getViewLifecycleOwner(), exercises -> {
            if (exercises != null) {
                adapter.submitList(exercises);
            }
        });

        adapter.setOnItemClickListener(exercise -> {
            Bundle args = new Bundle();
            args.putLong("exerciseId", exercise.getExerciseId()); // Передаём ID выбранного упражнения
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_trainingViewFragment_to_exerciseViewFragment, args); // Переход на новый фрагмент
        });

        // Обработка нажатия на кнопку FAB (добавление тренировки)
        binding.fab.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putLong("trainingId", trainingId);

            NavHostFragment.findNavController(TrainingViewFragment.this)
                    .navigate(R.id.action_trainingViewFragment_to_exerciseCreatingFragment, args);
                }
        );
    }

    // Форматирование типа
    private String formattedType (TrainingEntity training) {
        return "Type: " + training.getTrainingType();
    }

    // Форматирование времени
    private String formattedTime (TrainingEntity training) {

        long hours = training.getTrainingDuration() / 60;
        long minutes = training.getTrainingDuration() % 60;

        if (minutes == 0) {
            return "Duration: " + String.format(Locale.getDefault(), "%d hours", hours);
        }
        return "Duration: " + String.format(Locale.getDefault(), "%d hours %d minutes", hours, minutes);
    }

    // Форматирование даты
    private String formattedDate (TrainingEntity training) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return "Date: " + dateFormat.format(training.getTrainingDate());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}