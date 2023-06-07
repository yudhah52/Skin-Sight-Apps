package com.yhezra.skinsightapps.data.remote.api

import com.yhezra.skinsightapps.data.remote.model.detection.DetectionDiseaseResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface DetectionApiService {

    @Multipart
    @POST("/detect-disease/{uid}")
    suspend fun postDetectionDisease(
        @Path("uid") uid: String,
        @Part file: MultipartBody.Part,
    ): DetectionDiseaseResponse

}