package com.example.shifttest.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shifttest.data.retrofit.RetrofitProvider
import com.example.shifttest.data.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UsersViewModel : ViewModel() {
    private val _usersList = MutableStateFlow<List<User>>(emptyList())
    val usersList = _usersList.asStateFlow()
    private val api = RetrofitProvider.connectApi(RetrofitProvider.provideRetrofit())

    init {requestUsers()
    }

    fun refresh() {
        requestUsers()
    }

    fun findUser(userName: String) = _usersList.value.first { it.name.toString() == userName }

    private fun requestUsers() =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _usersList.value = api.getUsers().users
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }