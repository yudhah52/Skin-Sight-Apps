package com.yhezra.skinsightapps.data.remote.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.yhezra.skinsightapps.data.local.Result
import com.yhezra.skinsightapps.data.local.preference.UserPreference
import com.yhezra.skinsightapps.data.remote.api.UserApiService
import com.yhezra.skinsightapps.data.remote.model.auth.DataUser
import kotlinx.coroutines.flow.emitAll

class UserRepository private constructor(
    private val userApiService: UserApiService,
    private val userPreference: UserPreference
) {

    fun isLogin(): Flow<String?> = flow { emitAll(userPreference.getToken()) }

    fun login(email: String, password: String): Flow<Result<String>> = flow {
        emit(Result.Loading)
        try {
            Log.d("LOGIN", "login: $userApiService")
            val response = userApiService.login(email, password)
            val token = response.data.uid
            userPreference.saveToken(token)
            emit(Result.Success(response.message))
        } catch (e: Exception) {
            Log.d("UserRepository", "login: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun register(
        email: String,
        password: String,
        name: String,
        phone: String
    ): Flow<Result<String>> = flow {
        emit(Result.Loading)

        try {
            val response = userApiService.register(email, password, name, phone)
            val token = response.data.uid
            userPreference.saveToken(token)
            emit(Result.Success(response.message))
        } catch (e: Exception) {
            Log.d("UserRepository", "register: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun reset(email: String): Flow<Result<String>> = flow {
        emit(Result.Loading)
        try {
            val response = userApiService.reset(email)
//            val token = response.data.uid
//            userPreference.saveToken(token)
            emit(Result.Success(response.message))
        } catch (e: Exception) {
            Log.d("UserRepository", "reset: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }


    fun logout(): Flow<Result<String>> = flow {
        emit(Result.Loading)
        userPreference.logout()
        emit(Result.Success("success"))
    }

    fun getDataUser(
        token: String
    ) : Flow<Result<DataUser>> = flow{
        emit(Result.Loading)
        try{
            val response = userApiService.getDataUser(token)
            emit(Result.Success(response.data!!))
        }catch (e:Exception){
            Log.d("UserRepository", "getDataUser: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }

    }

    fun editEmailPassword(
        token: String,
        currentEmail: String,
        newEmail: String,
        currentPassword: String,
        newPassword: String
    ): Flow<Result<String>> = flow {
        emit(Result.Loading)
        try {
            val response = userApiService.editEmailPassword(
                uid = token,
                email = newEmail,
                password = newPassword,
                currentEmail = currentEmail,
                currentPassword = currentPassword
            )
            emit(Result.Success(response.message!!))

        } catch (e: Exception) {
            Log.d("UserRepository", "editEmailPassword: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

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