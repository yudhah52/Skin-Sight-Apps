package com.yhezra.skinsightapps.ui.home.article.detailarticle

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.text.HtmlCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.bumptech.glide.Glide
import com.yhezra.skinsightapps.R
import com.yhezra.skinsightapps.data.local.Result
import com.yhezra.skinsightapps.data.remote.model.detailarticle.DataDetailArticle
import com.yhezra.skinsightapps.databinding.ActivityDetailArticleBinding
import com.yhezra.skinsightapps.ui.home.ArticleViewModelFactory
import com.yhezra.skinsightapps.ui.home.article.ArticleViewModel

class DetailArticleActivity : AppCompatActivity(), View.OnClickListener {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")

    private lateinit var binding: ActivityDetailArticleBinding
    private val articleViewModel: ArticleViewModel by viewModels{
        ArticleViewModelFactory.getInstance(this,dataStore)
    }

    private var id: String? = null
    private var detailArticle: DataDetailArticle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()

        getData()
    }

    private fun getData() {
        id = intent.getStringExtra(ID_ARTICLE)

        articleViewModel.getDetailArticle(id = id!!).observe(this) { result ->
            when(result){
                is Result.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    detailArticle = result.data
                    setDataArticle(detailArticle!!)
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        this,
                        "Failed to load article",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    private fun setToolbar() {
        binding.toolbar.btnBackToolbar.setOnClickListener(this)
        binding.toolbar.tvToolbarTitle.text = "Article Detail"
    }

    private fun setDataArticle(detailArticle: DataDetailArticle) {
        binding.apply {
            Glide.with(root)
                .load(detailArticle.imageUrl)
                .into(ivArticle)
            tvTitleArticle.text = detailArticle.title

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tvDescArticle.text =
                    Html.fromHtml(detailArticle.content, Html.FROM_HTML_MODE_COMPACT)
            } else {
                tvDescArticle.text =
                    HtmlCompat.fromHtml(detailArticle.content, HtmlCompat.FROM_HTML_MODE_LEGACY)
            }
        }
    }


    companion object {
        const val ID_ARTICLE = "default_id"
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_back_toolbar -> {
                finish()
            }
        }
    }
}