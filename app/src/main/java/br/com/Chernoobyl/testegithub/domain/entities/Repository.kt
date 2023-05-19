package br.com.chernoobyl.testegithub.domain.entities

data class Repository(
    val name: String,
    val language: String?,
    val url: String,
    val private: Boolean,
) {
    val visibility: String get() = if (private) "Private" else "Public"
}
