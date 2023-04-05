package com.example.githubrepo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Github(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val description: String,
    val browserUrl: String
)
