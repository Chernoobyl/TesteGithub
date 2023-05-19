package br.com.chernoobyl.testegithub.data.models

import br.com.chernoobyl.testegithub.domain.entities.Repository
import com.google.gson.annotations.SerializedName

data class RepositoryModel(
    @SerializedName("full_name")
    val name: String,
    val language: String?,
    @SerializedName("html_url")
    val url: String,
    val private: Boolean,
) {
    fun toEntity(): Repository = Repository(
        name = name,
        language = language,
        url = url,
        private = private
    )
}
