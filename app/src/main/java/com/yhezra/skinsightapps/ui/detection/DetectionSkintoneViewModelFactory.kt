package com.yhezra.skinsightapps.ui.detection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yhezra.skinsightapps.data.di.Injection
import com.yhezra.skinsightapps.data.remote.repository.DetectionSkintoneRepository

class DetectionSkintoneViewModelFactory(
    private val DetectionSkintoneRepository: DetectionSkintoneRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DetectionSkintoneViewModel::class.java) -> {
                DetectionSkintoneViewModel(DetectionSkintoneRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: DetectionSkintoneViewModelFactory? = null
        fun getInstance(
        ): DetectionSkintoneViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: DetectionSkintoneViewModelFactory(
                    Injection.provideDetectionSkintoneRepository()
                )
            }.also { instance = it }
    }
}