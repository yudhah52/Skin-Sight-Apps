package com.yhezra.skinsightapps.ui.detection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yhezra.skinsightapps.data.remote.repository.DetectionDiseaseRepository
import java.io.File

class DetectionDiseaseViewModel(private val detectionDiseaseRepository: DetectionDiseaseRepository):ViewModel() {

    fun postDetectionDisease(
        uid: String, imageFile: File
    ) = detectionDiseaseRepository.postDetectionDisease(uid,imageFile).asLiveData()

    fun getAllHistory(
        uid: String
    ) = detectionDiseaseRepository.getAllHistory(uid).asLiveData()
}