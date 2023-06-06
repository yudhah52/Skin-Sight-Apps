package com.yhezra.skinsightapps.ui.home

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yhezra.skinsightapps.data.di.Injection
import com.yhezra.skinsightapps.data.remote.repository.ArticleRepository
import com.yhezra.skinsightapps.ui.home.article.ArticleViewModel

class ArticleViewModelFactory(
    private val articleRepository: ArticleRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ArticleViewModel::class.java) -> {
                ArticleViewModel(articleRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ArticleViewModelFactory? = null
        fun getInstance(context: Context, dataStore: DataStore<Preferences>): ArticleViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ArticleViewModelFactory(
                    Injection.provideArticleRepository(
                        context,
                        dataStore
                    )
                )
            }.also { instance = it }
    }
}