package br.com.chernoobyl.testegithub.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.chernoobyl.testegithub.domain.entities.User
import br.com.chernoobyl.testegithub.domain.usecases.GetAllUsers
import br.com.chernoobyl.testegithub.presentation.shared.State
import kotlinx.coroutines.launch

class UserListViewModel(
    private val GetAllUsers: GetAllUsers
) : ViewModel() {

    private val _userListState = MutableLiveData<State<List<User>>>()
    val userListState get() = _userListState

    private val shouldRequestAgain get() = _userListState.value == null || _userListState.value?.hasError() == true

    fun getAllUsers() {
        if (shouldRequestAgain) {
            viewModelScope.launch {
                try {
                    _userListState.postValue(State.Loading)
                    val userList = GetAllUsers()
                    _userListState.postValue(State.Success(userList))
                } catch (e: Exception) {
                    _userListState.postValue(State.Error(e))
                }
            }
        }
    }
}