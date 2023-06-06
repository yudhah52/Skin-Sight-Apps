package com.yhezra.skinsightapps.ui.home.article

import androidx.lifecycle.*
import com.yhezra.skinsightapps.data.remote.repository.ArticleRepository

class ArticleViewModel(private val articleRepository: ArticleRepository) : ViewModel() {
    fun getListArticle() = articleRepository.getListArticle().asLiveData()

    fun getDetailArticle(id:String) = articleRepository.getDetailArticle(id).asLiveData()

    companion object {
        private const val TAG = "ArticleViewModel"
    }
}