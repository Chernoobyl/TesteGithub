package br.com.chernoobyl.testegithub.data.models

import br.com.chernoobyl.testegithub.domain.entities.User
import com.google.gson.annotations.SerializedName

data class UserModel(
    val id: Int,
    val login: String,
    val name: String?,
    val location: String?,
    @SerializedName("html_url")
    val url: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("public_repos")
    val repos: Int?
) {
    fun toEntity(): User = User(
        id = id,
        login = login,
        name = name,
        location = location,
        url = url,
        avatarUrl = avatarUrl,
        repos = repos
    )
}
