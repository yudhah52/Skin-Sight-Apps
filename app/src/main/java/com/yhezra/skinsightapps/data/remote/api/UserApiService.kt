package com.yhezra.skinsightapps.data.remote.api

import com.yhezra.skinsightapps.data.remote.model.RegisterResponse
import retrofit2.http.*

interface UserApiService {

    @FormUrlEncoded
    @POST("sign-up")
    suspend fun register(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("name") name: String,
        @Field("phone") phone: String
    ): RegisterResponse

//    @FormUrlEncoded
//    @POST("login")
//    suspend fun login(
//        @Field("email") email: String,
//        @Field("password") password: String
//    ): LoginResponse
//
//    @GET("stories")
//    suspend fun getAllStories(
//        @Header("Authorization") token: String,
//        @Query("page") page: Int? = null,
//        @Query("size") size: Int? = null,
//        @Query("location") location: Int = 0
//    ): AllStoriesResponse
//
//    @GET("stories")
//    suspend fun getAllStoriesWithLocation(
//        @Header("Authorization") token: String,
//        @Query("page") page: Int? = null,
//        @Query("size") size: Int? = 30,
//        @Query("location") location: Int = 1
//    ): AllStoriesResponse
//
//    @GET("stories/{id}")
//    suspend fun getDetailStory(
//        @Header("Authorization") token: String,
//        @Path("id") id: String
//    ): DetailStoryResponse
//
//    @Multipart
//    @POST("stories")
//    suspend fun addStory(
//        @Header("Authorization") token: String,
//        @Part file: MultipartBody.Part,
//        @Part("description") description: RequestBody,
//        @Part("lat") lat: RequestBody? = null,
//        @Part("lon") lon: RequestBody? = null
//    ): AddStoryResponse
}