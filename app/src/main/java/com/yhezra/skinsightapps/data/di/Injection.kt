package com.yhezra.skinsightapps.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.yhezra.skinsightapps.data.local.preference.UserPreference
import com.yhezra.skinsightapps.data.remote.repository.UserRepository
import com.yhezra.skinsightapps.data.remote.api.ApiConfig
import com.yhezra.skinsightapps.data.remote.repository.ArticleRepository
import com.yhezra.skinsightapps.data.remote.repository.DetectionRepository

object Injection {

    fun provideUserRepository(dataStore: DataStore<Preferences>): UserRepository {
        val userApiService = ApiConfig.getUserApiService()
        val authPreferences = UserPreference.getInstance(dataStore)
        return UserRepository.getInstance(userApiService, authPreferences)
    }

    fun provideArticleRepository(): ArticleRepository {
        val apiService = ApiConfig.getArticleApiService()
        return ArticleRepository.getInstance(apiService)
    }

    fun provideDetectionDiseaseRepository(): DetectionRepository {
        val apiService = ApiConfig.getDetectionDiseaseApiService()
        return DetectionRepository.getInstance(apiService)
    }

    fun provideDetectionSkintoneRepository(): DetectionRepository {
        val apiService = ApiConfig.getDetectionSkintoneApiService()
        return DetectionRepository.getInstance(apiService)
    }
}