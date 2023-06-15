package com.yhezra.skinsightapps.ui.detection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yhezra.skinsightapps.data.remote.repository.DetectionSkintoneRepository
import java.io.File

class DetectionSkintoneViewModel(private val DetectionSkintoneRepository: DetectionSkintoneRepository):ViewModel() {

    fun postDetectionSkintone(
        uid: String, imageFile: File
    ) = DetectionSkintoneRepository.postDetectionSkintone(uid,imageFile).asLiveData()

}