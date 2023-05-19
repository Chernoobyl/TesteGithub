package br.com.chernoobyl.testegithub.domain.usecases

import br.com.chernoobyl.testegithub.domain.entities.User

interface GetAllUsers {
    suspend operator fun invoke(): List<User>
}