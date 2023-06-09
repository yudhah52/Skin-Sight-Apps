package com.yhezra.skinsightapps.ui.detection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yhezra.skinsightapps.data.di.Injection
import com.yhezra.skinsightapps.data.remote.repository.DetectionDiseaseRepository

class DetectionDiseaseViewModelFactory(
    private val detectionDiseaseRepository: DetectionDiseaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DetectionDiseaseViewModel::class.java) -> {
                DetectionDiseaseViewModel(detectionDiseaseRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: DetectionDiseaseViewModelFactory? = null
        fun getInstance(
        ): DetectionDiseaseViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: DetectionDiseaseViewModelFactory(
                    Injection.provideDetectionDiseaseRepository()
                )
            }.also { instance = it }
    }
}