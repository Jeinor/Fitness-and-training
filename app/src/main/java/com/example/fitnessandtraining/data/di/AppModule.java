package com.example.fitnessandtraining.data.di;

import android.content.Context;

import androidx.room.Room;

import com.example.fitnessandtraining.data.local.db.AppDatabase;
import com.example.fitnessandtraining.data.local.dao.ExerciseDao;
import com.example.fitnessandtraining.data.local.dao.TrainingDao;
import com.example.fitnessandtraining.data.repository.ExerciseRepository;
import com.example.fitnessandtraining.data.repository.ExerciseRepositoryImpl;
import com.example.fitnessandtraining.data.repository.TrainingRepository;
import com.example.fitnessandtraining.data.repository.TrainingRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

// Интеграция базы данных через Hilt (Dependency Injection)
@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    // Предоставление базы данных
    @Provides
    @Singleton
    public AppDatabase provideDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "fitness_and_training.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    // Предоставление DAO для тренировок
    @Provides
    public TrainingDao provideTrainingDao(AppDatabase database) {
        return database.trainingDao();
    }

    // Предоставление DAO для упражнений
    @Provides
    public ExerciseDao provideExerciseDao(AppDatabase database) {
        return database.exerciseDao();
    }

    // Репозиторий для тренировок
    @Provides
    public TrainingRepository provideTrainingRepository(TrainingDao trainingDao) {
        return new TrainingRepositoryImpl(trainingDao);
    }

    // Репозиторий для упражнений
    @Provides
    public ExerciseRepository provideExerciseRepository(ExerciseDao exerciseDao) {
        return new ExerciseRepositoryImpl(exerciseDao);
    }
}
