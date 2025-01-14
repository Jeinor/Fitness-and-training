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
import com.example.fitnessandtraining.databinding.FragmentTrainingListBinding;
import com.example.fitnessandtraining.ui.adapters.TrainingListAdapter;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TrainingListFragment extends Fragment {
    private FragmentTrainingListBinding binding; // Для ViewBinding
    private TrainingListAdapter adapter; // Адаптер для RecyclerView

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentTrainingListBinding.inflate(inflater, container, false); // Инициализация ViewBinding
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ViewModel для работы с данными
        TrainingViewModel trainingViewModel = new ViewModelProvider(this).get(TrainingViewModel.class); // Инициализация ViewModel через Hilt

        // Настройка RecyclerView
        adapter = new TrainingListAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext())); // Линейный менеджер компоновки
        binding.recyclerView.setAdapter(adapter); // Установка адаптера

        // Наблюдение за данными из ViewModel
        trainingViewModel.getAllTrainings().observe(getViewLifecycleOwner(), trainings -> {
            adapter.submitList(trainings); // Обновление списка в адаптере
        });

        // Обработка нажатий на элементы списка
        adapter.setOnItemClickListener(training -> {
            Bundle args = new Bundle(); // Передача ID тренировки в TrainingViewFragment
            args.putLong("trainingId", training.getTrainingId());
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_trainingListFragment_to_trainingViewFragment, args);
        });

        // Обработка нажатия на кнопку FAB (добавление тренировки)
        binding.fab.setOnClickListener(v ->
                NavHostFragment.findNavController(TrainingListFragment.this)
                        .navigate(R.id.action_trainingListFragment_to_trainingCreatingFragment)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}