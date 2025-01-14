package com.example.fitnessandtraining.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessandtraining.data.local.entity.TrainingEntity;
import com.example.fitnessandtraining.databinding.ItemCardViewBinding;

import java.util.ArrayList;
import java.util.List;

// Адаптер для отображения списка тренировок
public class TrainingListAdapter extends RecyclerView.Adapter<TrainingViewHolder> {
    private final List<TrainingEntity> trainingList = new ArrayList<>(); // Список тренировок
    private OnItemClickListener<TrainingEntity> onItemClickListener; // Обработчик нажатий

    // Установка обработчика нажатий
    public void setOnItemClickListener(OnItemClickListener<TrainingEntity> listener) {
        this.onItemClickListener = listener;
    }

    // Обновление списка тренировок
    public void submitList(List<TrainingEntity> trainings) {
        trainingList.clear(); // Очистка текущего списка
        trainingList.addAll(trainings); // Добавление новых данных
        notifyDataSetChanged(); // Уведомление об изменениях
    }

    // Создание ViewHolder
    @NonNull
    @Override
    public TrainingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // Создаёт binding, использует разметку item_card_view.xml, обернутую через ItemCardViewBinding
        ItemCardViewBinding binding = ItemCardViewBinding.inflate(inflater, parent, false);

        // Создание нового ViewHolder
        return new TrainingViewHolder(binding);
    }

    // Заполнение данных в ViewHolder
    @Override
    public void onBindViewHolder(@NonNull TrainingViewHolder holder, int position) {
        TrainingEntity training = trainingList.get(position); // Получение текущей тренировки по индексу

        holder.bind(training); // Привязка данных через метод bind

        // Обработка кликов
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(training); // Передача события во фрагмент
            }
        });
    }

    // Определяет, сколько элементов нужно отобразить
    @Override
    public int getItemCount() {
        return trainingList.size(); // Количество элементов
    }
}
