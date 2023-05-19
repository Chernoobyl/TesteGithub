package br.com.chernoobyl.testegithub.domain.repositories

import br.com.chernoobyl.testegithub.domain.entities.Repository
import br.com.chernoobyl.testegithub.domain.entities.User

interface UserRepository {
    suspend fun getAllUsers(): List<User>
    suspend fun getUserByLogin(login: String): User
    suspend fun getRepositoriesByLogin(login: String): List<Repository>
}