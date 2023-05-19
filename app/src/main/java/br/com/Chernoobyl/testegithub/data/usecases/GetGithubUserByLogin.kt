package br.com.chernoobyl.testegithub.data.usecases

import br.com.chernoobyl.testegithub.domain.entities.User
import br.com.chernoobyl.testegithub.domain.repositories.UserRepository
import br.com.chernoobyl.testegithub.domain.usecases.GetUserByLogin

class GetGithubUserByLogin(
    private val repository: UserRepository
) : GetUserByLogin {
    override suspend fun invoke(login: String): User {
        return repository.getUserByLogin(login)
    }
}