package br.com.chernoobyl.testegithub.data.usecases

import br.com.chernoobyl.testegithub.domain.entities.Repository
import br.com.chernoobyl.testegithub.domain.repositories.UserRepository
import br.com.chernoobyl.testegithub.domain.usecases.GetRepositoriesByLogin

class GetGithubRepositoriesByLogin(
    private val repository: UserRepository
) : GetRepositoriesByLogin {
    override suspend fun invoke(login: String): List<Repository> {
        return repository.getRepositoriesByLogin(login)
    }
}