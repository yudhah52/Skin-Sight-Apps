package com.yhezra.skinsightapps.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.yhezra.skinsightapps.data.local.preference.UserPreference
import com.yhezra.skinsightapps.data.remote.repository.UserRepository
import com.yhezra.skinsightapps.data.remote.api.ApiConfig
import com.yhezra.skinsightapps.data.remote.repository.ArticleRepository

object Injection {

    fun provideUserRepository(dataStore: DataStore<Preferences>): UserRepository {
        val userApiService = ApiConfig.getUserApiService()
        val authPreferences = UserPreference.getInstance(dataStore)
        return UserRepository.getInstance(userApiService, authPreferences)
    }

    fun provideArticleRepository(
        context: Context,
        dataStore: DataStore<Preferences>
    ): ArticleRepository {
        val apiService = ApiConfig.getArticleApiService()
        return ArticleRepository.getInstance(apiService)
    }
}