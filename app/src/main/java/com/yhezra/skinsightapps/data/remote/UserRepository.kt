package com.yhezra.skinsightapps.data.remote

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.yhezra.skinsightapps.data.local.Result
import com.yhezra.skinsightapps.data.local.preference.UserPreference
import com.yhezra.skinsightapps.data.remote.api.UserApiService

class UserRepository private constructor(
    private val userApiService: UserApiService,
    private val userPreference: UserPreference
) {

    fun register(email: String, password: String, name: String, phone: String): Flow<Result<String>> = flow {
        emit(Result.Loading)
        try {
            val response = userApiService.register(email, password, name, phone)
            emit(Result.Success(response.message))
        } catch (e: Exception) {
            Log.d("UserRepository", "register: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

//    fun logout(): Flow<Result<String>> = flow {
//        emit(Result.Loading)
//        userPreference.logout()
//        emit(Result.Success("success"))
//    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: UserApiService, userPreference: UserPreference
        ): UserRepository = instance ?: synchronized(this) {
            instance ?: UserRepository(apiService, userPreference)
        }.also { instance = it }
    }
}