package com.yhezra.skinsightapps.ui.detection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yhezra.skinsightapps.data.remote.repository.DetectionRepository
import java.io.File

class DetectionViewModel(private val detectionRepository: DetectionRepository):ViewModel() {

    fun postDetectionDisease(
        uid: String, imageFile: File
    ) = detectionRepository.postDetectionDisease(uid,imageFile).asLiveData()

    fun getAllHistory(
        uid: String
    ) = detectionRepository.getAllHistory(uid).asLiveData()
}