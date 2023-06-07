package com.yhezra.skinsightapps.data.remote.repository

import android.util.Log
import com.yhezra.skinsightapps.data.remote.api.DetectionApiService
import com.yhezra.skinsightapps.data.remote.model.detection.DetectionDiseaseResponse
import com.yhezra.skinsightapps.data.local.Result
import com.yhezra.skinsightapps.data.remote.model.history.HistoryDetectionItem
import com.yhezra.skinsightapps.data.remote.utils.reduceFileImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class DetectionRepository(
    private val detectionApiService: DetectionApiService
) {

    fun getAllHistory(uid: String): Flow<Result<List<HistoryDetectionItem>>> = flow {
        emit(Result.Loading)
        try{
            val response = detectionApiService.getAllHistory(uid)
            emit(Result.Success(response.data))
        }catch (e:Exception){
            Log.d("DetectionRepository", "getAllHistory: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun postDetectionDisease(
        uid: String, imageFile: File
    ): Flow<Result<DetectionDiseaseResponse>> = flow {
        emit(Result.Loading)
        try{
            val reducedFile = reduceFileImage(imageFile)
            val requestImageFile = reducedFile.asRequestBody("image/jpeg".toMediaType())
            val imageMultipart: MultipartBody.Part =
                MultipartBody.Part.createFormData("file", imageFile.name, requestImageFile)
            val response = detectionApiService.postDetectionDisease(
                uid, imageMultipart
            )
            emit(Result.Success(response))
        }catch (e:Exception){
            Log.d("DetectionRepository", "postDetectionDisease: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: DetectionRepository? = null
        fun getInstance(
            apiService: DetectionApiService
        ): DetectionRepository = instance ?: synchronized(this) {
            instance ?: DetectionRepository(apiService)
        }.also { instance = it }
    }
}