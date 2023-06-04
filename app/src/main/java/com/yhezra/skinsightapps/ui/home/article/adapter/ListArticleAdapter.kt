package com.yhezra.skinsightapps.ui.home.article.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yhezra.skinsightapps.data.remote.model.article.ArticleItem
import com.yhezra.skinsightapps.databinding.ItemArticleBinding

class ListArticleAdapter(private val listArticle: List<ArticleItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ArticleViewHolder -> holder.bind(listArticle[position])
        }
    }

    override fun getItemCount(): Int = listArticle.size


    inner class ArticleViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleItem) {
            val title = article.title
            val imageUrl = article.imageUrl
            val desc = article.description
            binding.tvTitleArticle.text = title
            binding.tvDescArticle.text = desc
            Glide.with(binding.root)
                .load(imageUrl)
                .into(binding.ivItemArticle)
            binding.root.setOnClickListener {
                onItemClickCallback.onItemArticleClicked(article)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemArticleClicked(item: ArticleItem)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}