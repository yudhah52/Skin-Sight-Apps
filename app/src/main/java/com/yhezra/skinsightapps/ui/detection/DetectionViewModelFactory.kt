package com.yhezra.skinsightapps.ui.detection

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yhezra.skinsightapps.data.di.Injection
import com.yhezra.skinsightapps.data.remote.repository.DetectionRepository
import com.yhezra.skinsightapps.ui.home.article.ArticleViewModel
import com.yhezra.skinsightapps.ui.home.article.ArticleViewModelFactory

class DetectionViewModelFactory(
    private val detectionRepository: DetectionRepository
):ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DetectionViewModel::class.java) -> {
               DetectionViewModel(detectionRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: DetectionViewModelFactory? = null
        fun getInstance(
        ): DetectionViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: DetectionViewModelFactory(
                    Injection.provideDetectionRepository()
                )
            }.also { instance = it }
    }

}