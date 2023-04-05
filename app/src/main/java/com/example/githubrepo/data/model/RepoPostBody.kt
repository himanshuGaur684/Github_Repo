package com.example.githubrepo.data.model

data class RepoPostBody(
    val description: String,
    val headers: Headers = Headers(),
    val homepage: String = "https://github.com",
    val is_template: Boolean = true,
    val name: String,
    val `private`: Boolean = false
)