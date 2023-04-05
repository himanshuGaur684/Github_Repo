package com.example.githubrepo.data.datasource.network

import com.example.githubrepo.data.model.GithubDTO
import com.example.githubrepo.data.model.RepoPostBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users/{userName}/repos")
    suspend fun getRepository(
        @Path("userName") userName: String,
        @Query("sort") sort: String = "updated",
        @Query("direction") direction: String = "desc"
    ): List<GithubDTO>

    @POST("user/repos")
    suspend fun postRepo(
        @Header("Authorization") token: String,
        @Body repoPostBody: RepoPostBody
    ): GithubDTO

}