package br.com.chernoobyl.testegithub.domain.entities

data class User(
    val id: Int,
    val login: String,
    val name: String?,
    val location: String?,
    val url: String?,
    val avatarUrl: String,
    val repos: Int?
)