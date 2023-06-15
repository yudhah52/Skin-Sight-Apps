package com.yhezra.skinsightapps.data.remote.repository

import android.util.Log
import com.yhezra.skinsightapps.data.remote.model.detection.DetectionResponse
import com.yhezra.skinsightapps.data.local.Result
import com.yhezra.skinsightapps.data.remote.api.DetectionSkintoneApiService
import com.yhezra.skinsightapps.data.remote.utils.reduceFileImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class DetectionSkintoneRepository(
    private val DetectionSkintoneApiService: DetectionSkintoneApiService
) {
    fun postDetectionSkintone(
        uid: String, imageFile: File
    ): Flow<Result<DetectionResponse>> = flow {
        emit(Result.Loading)
        try{
            val reducedFile = reduceFileImage(imageFile)
            val requestImageFile = reducedFile.asRequestBody("image/jpeg".toMediaType())
            val imageMultipart: MultipartBody.Part =
                MultipartBody.Part.createFormData("file", imageFile.name, requestImageFile)
            val response = DetectionSkintoneApiService.postDetectionSkintone(
                uid, imageMultipart
            )
            emit(Result.Success(response))
        }catch (e:Exception){
            Log.d("DetectToneRepo", "postDetectionSkintone: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: DetectionSkintoneRepository? = null
        fun getInstance(
            apiService: DetectionSkintoneApiService
        ): DetectionSkintoneRepository = instance ?: synchronized(this) {
            instance ?: DetectionSkintoneRepository(apiService)
        }.also { instance = it }
    }
}