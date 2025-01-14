package com.example.fitnessandtraining.data.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Таблица упражнения
@Entity(tableName = "exercises")
public class ExerciseEntity {
    @PrimaryKey(autoGenerate = true)
    private long exerciseId;

    @ColumnInfo(name = "exercise_name")
    private String exerciseName;

    @ColumnInfo(name = "exercise_description")
    private String exerciseDescription;

    @ColumnInfo(name = "exercise_date_edited")
    private long exerciseDateEdited;

    @ColumnInfo(name = "training_id")
    private long trainingId; // Связь с TrainingEntity

    public ExerciseEntity(String exerciseName, String exerciseDescription, long exerciseDateEdited, long trainingId) {
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
        this.exerciseDateEdited = exerciseDateEdited;
        this.trainingId = trainingId;
    }

    // Геттеры
    public long getExerciseId() {
        return exerciseId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public long getExerciseDateEdited() {
        return exerciseDateEdited;
    }

    public long getTrainingId() {
        return trainingId;
    }

    // Сеттеры
    public void setExerciseId(long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    public void setExerciseDateEdited(long exerciseDateEdited) {
        this.exerciseDateEdited = exerciseDateEdited;
    }

    public void setTrainingId(long trainingId) {
        this.trainingId = trainingId;
    }
}
