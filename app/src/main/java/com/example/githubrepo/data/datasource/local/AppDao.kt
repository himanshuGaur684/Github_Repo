package com.example.githubrepo.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubrepo.domain.model.Github
import dagger.hilt.InstallIn

@Dao
interface AppDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGithubList(list: List<Github>)


    @Query("SELECT * FROM Github")
    suspend fun getAllGithubList(): List<Github>

}