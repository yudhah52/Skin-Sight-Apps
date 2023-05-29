package com.yhezra.skinsightapps.ui.home.article.detailarticle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.yhezra.skinsightapps.R
import com.yhezra.skinsightapps.data.remote.model.detailarticle.DataDetailArticle
import com.yhezra.skinsightapps.databinding.ActivityDetailArticleBinding
import com.yhezra.skinsightapps.ui.home.article.ArticleViewModel

class DetailArticleActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailArticleBinding
    private val articleViewModel: ArticleViewModel by viewModels()

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

        articleViewModel.getDetailArticle(id = id!!)

        articleViewModel.detailArticle.observe(this) { detailArticle ->
            setDataArticle(detailArticle)
        }

        articleViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun setToolbar() {
        binding.toolbar.btnBackToolbar.setOnClickListener(this)
    }

    private fun setDataArticle(detailArticle: DataDetailArticle) {
        binding.apply {
            tvTitleArticle.text = detailArticle.title
            tvDescArticle.text = detailArticle.content
            Glide.with(root)
                .load(detailArticle.imageUrl)
                .into(ivArticle)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


    companion object {
        const val ID_ARTICLE = "default_id"
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_back_toolbar -> {
                finish()
            }
        }
    }
}