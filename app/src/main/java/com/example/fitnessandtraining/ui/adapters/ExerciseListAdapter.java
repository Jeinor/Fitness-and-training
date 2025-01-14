package com.example.fitnessandtraining.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessandtraining.data.local.entity.ExerciseEntity;
import com.example.fitnessandtraining.databinding.ItemExerciseBinding;

import java.util.ArrayList;
import java.util.List;

// Адаптер для отображения списка упражнений
public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseViewHolder> {
    private final List<ExerciseEntity> exerciseList = new ArrayList<>(); // Список упражнений
    private OnItemClickListener<ExerciseEntity> onItemClickListener; // Обработчик кликов

    // Установка обработчика кликов
    public void setOnItemClickListener(OnItemClickListener<ExerciseEntity> listener) {
        this.onItemClickListener = listener;
    }

    // Метод для обновления данных в списке
    public void submitList(List<ExerciseEntity> exercises) {
        exerciseList.clear();
        exerciseList.addAll(exercises);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemExerciseBinding binding = ItemExerciseBinding.inflate(inflater, parent, false);
        return new ExerciseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        ExerciseEntity exercise = exerciseList.get(position);
        holder.bind(exercise);

        // Установка обработчика кликов
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(exercise);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
