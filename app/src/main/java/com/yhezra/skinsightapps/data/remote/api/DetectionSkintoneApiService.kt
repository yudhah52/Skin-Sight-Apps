package com.yhezra.skinsightapps.data.remote.api

import com.yhezra.skinsightapps.data.remote.model.detection.DetectionResponse
import com.yhezra.skinsightapps.data.remote.repository.DetectionSkintoneRepository
import okhttp3.MultipartBody
import retrofit2.http.*

interface DetectionSkintoneApiService {

    @Multipart
    @POST("detect-tone/{uid}")
    suspend fun postDetectionSkintone(
        @Path("uid") uid: String,
        @Part file: MultipartBody.Part,
    ): DetectionResponse
}