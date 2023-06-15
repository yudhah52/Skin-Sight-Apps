package com.yhezra.skinsightapps.data.remote.repository

import android.util.Log
import com.yhezra.skinsightapps.data.remote.api.DetectionDiseaseApiService
import com.yhezra.skinsightapps.data.remote.model.detection.DetectionResponse
import com.yhezra.skinsightapps.data.local.Result
import com.yhezra.skinsightapps.data.remote.model.history.HistoryDetectionItem
import com.yhezra.skinsightapps.data.remote.utils.reduceFileImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class DetectionDiseaseRepository(
    private val detectionDiseaseApiService: DetectionDiseaseApiService
) {

    fun getAllHistory(uid: String): Flow<Result<List<HistoryDetectionItem>>> = flow {
        emit(Result.Loading)
        try{
            val response = detectionDiseaseApiService.getAllHistory(uid)
            emit(Result.Success(response.data))
        }catch (e:Exception){
            Log.d("DetectionDiseaseRepo", "getAllHistory: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun postDetectionDisease(
        uid: String, imageFile: File
    ): Flow<Result<DetectionResponse>> = flow {
        emit(Result.Loading)
        try{
            val reducedFile = reduceFileImage(imageFile)
            val requestImageFile = reducedFile.asRequestBody("image/jpeg".toMediaType())
            val imageMultipart: MultipartBody.Part =
                MultipartBody.Part.createFormData("file", imageFile.name, requestImageFile)
            val response = detectionDiseaseApiService.postDetectionDisease(
                uid, imageMultipart
            )
            emit(Result.Success(response))
        }catch (e:Exception){
            Log.d("DetectionDiseaseRepo", "postDetectionDisease: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: DetectionDiseaseRepository? = null
        fun getInstance(
            apiService: DetectionDiseaseApiService
        ): DetectionDiseaseRepository = instance ?: synchronized(this) {
            instance ?: DetectionDiseaseRepository(apiService)
        }.also { instance = it }
    }
}