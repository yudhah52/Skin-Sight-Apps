package com.yhezra.skinsightapps.data.remote.repository

import android.util.Log
import com.yhezra.skinsightapps.data.remote.api.ArticleApiService
import com.yhezra.skinsightapps.data.remote.model.article.ArticleItem
import com.yhezra.skinsightapps.data.local.Result
import com.yhezra.skinsightapps.data.remote.model.detailarticle.DataDetailArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArticleRepository private constructor(
    private val articleApiService: ArticleApiService
) {

    fun getListArticle(): Flow<Result<List<ArticleItem>>> = flow {
        emit(Result.Loading)
        try {
            val response = articleApiService.getAllArticle()
            emit(Result.Success(response.data))
        } catch (e: Exception) {
            Log.d("ArticleRepository", "getListArticle: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getDetailArticle(id: String): Flow<Result<DataDetailArticle>> = flow {
        emit(Result.Loading)
        try {
            val response = articleApiService.getDetailArticle(id)
            emit(Result.Success(response.data))
        } catch (e: Exception) {
            Log.d("ArticleRepository", "getDetailArticle: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: ArticleRepository? = null
        fun getInstance(
            apiService: ArticleApiService
        ): ArticleRepository = instance ?: synchronized(this) {
            instance ?: ArticleRepository(apiService)
        }.also { instance = it }
    }
}