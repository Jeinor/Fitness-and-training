package com.example.fitnessandtraining.ui.adapters;

import com.example.fitnessandtraining.data.local.entity.TrainingEntity;

// Интерфейс для обработки нажатий
//public interface OnItemClickListener {
//    void onItemClick(TrainingEntity training); // Метод для передачи выбранного элемента
//}

public interface OnItemClickListener<T> {
    void onItemClick(T item);
}
