package br.com.chernoobyl.testegithub.presentation.shared

interface AdapterListener<T> {
    fun performItemClicked(value: T)
}