package com.example.fitnessandtraining.data.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Таблица тренировки
@Entity(tableName = "trainings")
public class TrainingEntity {
    @PrimaryKey(autoGenerate = true)
    private long TrainingId; // Поле id тренировки

    @ColumnInfo(name = "training_type")
    private String trainingType; // Тип тренировки

    @ColumnInfo(name = "training_duration")
    private int trainingDuration; // Продолжительность тренировки

    @ColumnInfo(name = "training_date")
    private long trainingDate; // Дата тренировки

    public TrainingEntity(String trainingType, int trainingDuration, long trainingDate) {
        this.trainingType = trainingType;
        this.trainingDuration = trainingDuration;
        this.trainingDate = trainingDate;
    }

    // Геттеры
    public long getTrainingId() {
        return TrainingId;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public int getTrainingDuration() {
        return trainingDuration;
    }

    public long getTrainingDate() {
        return trainingDate;
    }

    // Сеттеры
    public void setTrainingId(long TrainingId) {
        this.TrainingId = TrainingId;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public void setTrainingDuration(int trainingDuration) {
        this.trainingDuration = trainingDuration;
    }

    public void setTrainingDate(long trainingDate) {
        this.trainingDate = trainingDate;
    }
}
