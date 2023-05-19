package br.com.chernoobyl.testegithub.data.usecases

import br.com.chernoobyl.testegithub.domain.entities.User
import br.com.chernoobyl.testegithub.domain.repositories.UserRepository
import br.com.chernoobyl.testegithub.domain.usecases.GetAllUsers

class GetAllGithubUsers(
    private val repository: UserRepository
) : GetAllUsers {
    override suspend fun invoke(): List<User> {
        return repository.getAllUsers()
    }
}