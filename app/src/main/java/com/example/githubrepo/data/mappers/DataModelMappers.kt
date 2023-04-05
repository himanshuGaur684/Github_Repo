package com.example.githubrepo.data.mappers

import com.example.githubrepo.data.model.GithubDTO
import com.example.githubrepo.domain.model.Github


fun List<GithubDTO>.toDomain(): List<Github> {
    return map {
        Github(
            id=it.id,
            title = it.name,
            description = it.description?:"",
            browserUrl = it.html_url
        )
    }
}

fun  GithubDTO.toDomain(): Github {
    val it = this
    return Github(
            id=it.id,
            title = it.name,
            description = it.description?:"",
            browserUrl = it.html_url
        )

}