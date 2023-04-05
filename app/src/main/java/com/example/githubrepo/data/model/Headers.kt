package com.example.githubrepo.data.model

import com.google.gson.annotations.SerializedName

data class Headers(
    @SerializedName("X-GitHub-Api-Version")
    val version: String = "2022-11-28"
)