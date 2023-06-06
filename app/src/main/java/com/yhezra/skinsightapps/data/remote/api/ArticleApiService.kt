package com.yhezra.skinsightapps.data.remote.api

import com.yhezra.skinsightapps.data.remote.model.article.ArticleResponse
import com.yhezra.skinsightapps.data.remote.model.detailarticle.DetailArticleResponse
import retrofit2.http.*

interface ArticleApiService {

    @GET("article")
    suspend fun getAllArticle():ArticleResponse

    @GET("article/{id}")
    suspend fun getDetailArticle(
        @Path("id") id:String
    ):DetailArticleResponse
//
//    @Headers("Authorization: token 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")
//    @GET("users")
//    fun getListUser(
//        @Query("since") since: Int,
//        @Query("per_page") perPage: Int
//    ): Call<List<UserItem>>
//
//    @Headers("Authorization: token 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")
//    @GET("search/users")
//    fun getSearchUser(
//        @Query("q") username: String,
//        @Query("page") page: Int,
//        @Query("per_page") perPage: Int
//    ): Call<SearchUsersResponse>
//
//    @Headers("Authorization: token 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")
//    @GET("users/{username}")
//    fun getDetailUser(
//        @Path("username") username: String
//    ): Call<DetailUser>
//
//    @Headers("Authorization: token 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")
//    @GET("users/{username}/following")
//    fun getListFollowingUser(
//        @Path("username") username: String,
//        @Query("page") page: Int,
//        @Query("per_page") perPage: Int
//    ): Call<List<UserItem>>
//
//    @Headers("Authorization: token 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")
//    @GET("users/{username}/followers")
//    fun getListFollowersUser(
//        @Path("username") username: String,
//        @Query("page") page: Int,
//        @Query("per_page") perPage: Int
//    ): Call<List<UserItem>>
}