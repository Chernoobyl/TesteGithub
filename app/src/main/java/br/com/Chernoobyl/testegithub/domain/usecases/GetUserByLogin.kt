package br.com.chernoobyl.testegithub.domain.usecases

import br.com.chernoobyl.testegithub.domain.entities.User

interface GetUserByLogin {
    suspend operator fun invoke(login: String): User
}