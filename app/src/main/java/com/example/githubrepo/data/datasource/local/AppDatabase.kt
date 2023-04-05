package com.example.githubrepo.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubrepo.domain.model.Github

@Database(entities = [Github::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "db")
                .fallbackToDestructiveMigration().build()
        }
    }

    abstract fun getAppDao():AppDao

}