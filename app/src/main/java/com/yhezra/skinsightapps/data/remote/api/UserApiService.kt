package com.yhezra.skinsightapps.data.remote.api

import com.yhezra.skinsightapps.data.remote.model.auth.AuthResponse
import com.yhezra.skinsightapps.data.remote.model.auth.UserResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*
import java.io.File

interface UserApiService {

    @FormUrlEncoded
    @POST("sign-up")
    suspend fun register(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("name") name: String,
        @Field("phone") phone: String
    ): AuthResponse

    @FormUrlEncoded
    @POST("sign-in")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): AuthResponse

    @GET("user/{uid}")
    fun getDataUser(
        @Path("uid") uid:String
    ): Call<UserResponse>

    @Multipart
    @POST("user/{uid}/profile-picture")
    fun editProfilePicture(
        @Path("uid") uid:String,
        @Part file: MultipartBody.Part,
    ): Call<UserResponse>
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