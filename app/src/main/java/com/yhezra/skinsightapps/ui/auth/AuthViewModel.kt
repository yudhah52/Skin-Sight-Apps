package com.yhezra.skinsightapps.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yhezra.skinsightapps.data.remote.UserRepository

class AuthViewModel(private val userRepository: UserRepository) : ViewModel() {
//    fun isLogin() = userRepository.isLogin().asLiveData()

//    fun login(email: String, password: String) = userRepository.login(email, password).asLiveData()

    fun register(email: String, password: String, name: String, phone: String) =
        userRepository.register(email, password, name, phone).asLiveData()
}