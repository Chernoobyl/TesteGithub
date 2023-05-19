package br.com.chernoobyl.testegithub.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.chernoobyl.testegithub.domain.entities.UserData
import br.com.chernoobyl.testegithub.domain.usecases.GetRepositoriesByLogin
import br.com.chernoobyl.testegithub.domain.usecases.GetUserByLogin
import br.com.chernoobyl.testegithub.presentation.shared.State
import kotlinx.coroutines.launch

class UserDetailViewModel(
    private val GetUserByLogin: GetUserByLogin,
    private val GetRepositoriesByLogin: GetRepositoriesByLogin
) : ViewModel() {

    private val _userState = MutableLiveData<State<UserData>>()
    val userState get() = _userState

    fun getUserByLogin(login: String) {
        viewModelScope.launch {
            try {
                _userState.postValue(State.Loading)
                val user = GetUserByLogin(login)
                val repos = GetRepositoriesByLogin(login)
                _userState.postValue(State.Success(UserData(user, repos)))
            } catch (e: Exception) {
                _userState.postValue(State.Error(e))
            }
        }
    }

    fun shouldRequestAgain(login: String): Boolean {
        return _userState.value?.state?.run { this as? UserData }.let {
            it?.user?.login != login
        }
    }
}