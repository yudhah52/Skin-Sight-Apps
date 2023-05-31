package com.yhezra.skinsightapps.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.yhezra.skinsightapps.data.local.preference.UserPreference
import com.yhezra.skinsightapps.data.remote.repository.UserRepository
import com.yhezra.skinsightapps.data.remote.api.ApiConfig

object Injection {

    fun provideUserRepository(dataStore: DataStore<Preferences>): UserRepository {
        val userApiService = ApiConfig.getUserApiService()
        val authPreferences = UserPreference.getInstance(dataStore)
        return UserRepository.getInstance(userApiService, authPreferences)
    }

//    fun provideStoryRepository(
//        context: Context,
//        dataStore: DataStore<Preferences>
//    ): StoryRepository {
//        val apiService = ApiConfig.getUserApiService()
//        val userPreference = UserPreference.getInstance(dataStore)
//        val database = StoryDatabase.getInstance(context)
//        return StoryRepository.getInstance(apiService, userPreference, database)
//    }
}