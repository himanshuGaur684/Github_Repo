package com.example.githubrepo.data.repository

import android.util.Log
import com.example.githubrepo.data.datasource.local.AppDao
import com.example.githubrepo.data.datasource.network.ApiService
import com.example.githubrepo.data.mappers.toDomain
import com.example.githubrepo.data.model.RepoPostBody
import com.example.githubrepo.domain.model.Github
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val apiService: ApiService,
    private val appDao: AppDao
) {
    suspend fun getRepos(userName: String): List<Github> {
        return try {
            cacheThenReturnList(appDao, apiService.getRepository(userName).toDomain())
        } catch (e: java.lang.Exception) {
            Log.d("TAG", "getRepos: ${e.printStackTrace()}")
            e.printStackTrace()
            appDao.getAllGithubList()
        }
    }


    suspend fun postRepo(
        token:String,
        repoPostBody: RepoPostBody
    ): Github {
        return apiService.postRepo(
            token = token,
            repoPostBody = repoPostBody).toDomain()
    }


    private suspend fun cacheThenReturnList(appDao: AppDao, list: List<Github>): List<Github> {
        appDao.insertGithubList(list)
        return list
    }
}