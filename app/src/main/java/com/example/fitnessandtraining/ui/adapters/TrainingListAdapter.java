package com.example.fitnessandtraining.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessandtraining.data.local.entity.TrainingEntity;
import com.example.fitnessandtraining.databinding.ItemCardViewBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// Адаптер для отображения списка тренировок
public class TrainingListAdapter extends RecyclerView.Adapter<TrainingListAdapter.TrainingViewHolder> {
    private final List<TrainingEntity> trainingList = new ArrayList<>(); // Список тренировок

    private OnItemClickListener onItemClickListener; // Обработчик нажатий

    // Интерфейс для обработки нажатий
    public interface OnItemClickListener {
        void onItemClick(TrainingEntity training); // Метод для передачи выбранного элемента
    }

    // Установка обработчика нажатий
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener; // Привязка обработчика
    }

    // Обновление списка тренировок
    public void submitList(List<TrainingEntity> trainings) {
        trainingList.clear(); // Очистка текущего списка
        trainingList.addAll(trainings); // Добавление новых данных
        notifyDataSetChanged(); // Уведомление об изменениях
    }

    @NonNull
    @Override
    public TrainingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemCardViewBinding binding = ItemCardViewBinding.inflate(inflater, parent, false);
        // Создание нового ViewHolder
        return new TrainingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingViewHolder holder, int position) {
        TrainingEntity training = trainingList.get(position); // Получение текущей тренировки
        holder.bind(training); // Привязка данных
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(training); // Передача события во фрагмент
            }
        });
    }

    @Override
    public int getItemCount() {
        return trainingList.size(); // Количество элементов
    }

    // ViewHolder для элемента списка
    static class TrainingViewHolder extends RecyclerView.ViewHolder {
        private final ItemCardViewBinding binding;

        public TrainingViewHolder(@NonNull ItemCardViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        // Привязка данных к элементам интерфейса
        public void bind(TrainingEntity training) {
            binding.tvTrainingType.setText(training.getTrainingType()); // Установка типа тренировки
            binding.tvTrainingDuration.setText(String.format(Locale.getDefault(), "%d минут", training.getTrainingDuration())); // Установка длительности

            // Форматирование даты из long в строку
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            String formattedDate = dateFormat.format(training.getTrainingDate());
            binding.tvTrainingDate.setText(formattedDate); // Установка отформатированной даты
        }
    }
}
