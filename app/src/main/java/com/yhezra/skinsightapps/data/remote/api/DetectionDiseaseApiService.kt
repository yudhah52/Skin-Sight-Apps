package com.yhezra.skinsightapps.data.remote.api

import com.yhezra.skinsightapps.data.remote.model.detection.DetectionResponse
import com.yhezra.skinsightapps.data.remote.model.history.HistoryDetectionResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface DetectionDiseaseApiService {

    @Multipart
    @POST("detect-disease/{uid}")
    suspend fun postDetectionDisease(
        @Path("uid") uid: String,
        @Part file: MultipartBody.Part,
    ): DetectionResponse

    @GET("history/{uid}")
    suspend fun getAllHistory(
        @Path("uid") uid: String
    ): HistoryDetectionResponse
}