package br.com.chernoobyl.testegithub.data.services

import br.com.chernoobyl.testegithub.data.models.RepositoryModel
import br.com.chernoobyl.testegithub.data.models.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("/users")
    suspend fun getAllUsers(): Response<List<UserModel>>

    @GET("/users/{login}")
    suspend fun getUserByLogin(@Path("login") login: String): Response<UserModel>

    @GET("/users/{login}/repos?per_page=5")
    suspend fun getRepositoriesByLogin(@Path("login") login: String): Response<List<RepositoryModel>>
}