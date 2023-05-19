package br.com.chernoobyl.testegithub.domain.usecases

import br.com.chernoobyl.testegithub.domain.entities.Repository

interface GetRepositoriesByLogin {
    suspend operator fun invoke(login: String): List<Repository>
}