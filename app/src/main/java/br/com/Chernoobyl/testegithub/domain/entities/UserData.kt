package br.com.chernoobyl.testegithub.domain.entities

data class UserData(
    val user: User,
    val repositories: List<Repository>
)