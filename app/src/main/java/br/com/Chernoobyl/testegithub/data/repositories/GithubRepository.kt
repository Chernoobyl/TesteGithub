package br.com.chernoobyl.testegithub.data.repositories

import br.com.chernoobyl.testegithub.data.services.GithubService
import br.com.chernoobyl.testegithub.domain.entities.Repository
import br.com.chernoobyl.testegithub.domain.entities.User
import br.com.chernoobyl.testegithub.domain.exceptions.RepositoryListException
import br.com.chernoobyl.testegithub.domain.exceptions.UserDetailException
import br.com.chernoobyl.testegithub.domain.exceptions.UserListException
import br.com.chernoobyl.testegithub.domain.repositories.UserRepository

class GithubRepositoryImpl(
    private val service: GithubService
) : UserRepository {
    override suspend fun getAllUsers(): List<User> {
        val response = service.getAllUsers()
        if (response.isSuccessful) {
            val userList = response.body() ?: listOf()
            return userList.map { it.toEntity() }
        }
        throw UserListException(USER_LIST_EXCEPTION)
    }

    override suspend fun getUserByLogin(login: String): User {
        val response = service.getUserByLogin(login)
        if (response.isSuccessful) {
            return response.body()?.toEntity()
                ?: throw UserDetailException(USER_DETAIL_EXCEPTION)
        }
        throw UserDetailException(USER_DETAIL_EXCEPTION)
    }

    override suspend fun getRepositoriesByLogin(login: String): List<Repository> {
        val response = service.getRepositoriesByLogin(login)
        if (response.isSuccessful) {
            val repoList = response.body() ?: listOf()
            return repoList.map { it.toEntity() }
        }
        throw RepositoryListException(REPOSITORY_LIST_EXCEPTION)
    }

    companion object {
        private const val USER_LIST_EXCEPTION = "Unable to load users"
        private const val USER_DETAIL_EXCEPTION = "Unable to load this user's data"
        private const val REPOSITORY_LIST_EXCEPTION = "Unable to load repos"
    }
}
