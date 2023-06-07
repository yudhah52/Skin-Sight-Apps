package com.yhezra.skinsightapps.data.remote.api

import com.yhezra.skinsightapps.data.remote.model.article.ArticleResponse
import com.yhezra.skinsightapps.data.remote.model.detailarticle.DetailArticleResponse
import retrofit2.http.*

interface ArticleApiService {

    @GET("article")
    suspend fun getAllArticle(): ArticleResponse

    @GET("article/{id}")
    suspend fun getDetailArticle(
        @Path("id") id: String
    ): DetailArticleResponse
}