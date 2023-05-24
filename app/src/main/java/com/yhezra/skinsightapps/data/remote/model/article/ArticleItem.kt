package com.yhezra.skinsightapps.data.remote.model.article

import com.google.gson.annotations.SerializedName

data class ArticleItem(

    @field:SerializedName("imageUrl")
    val imageUrl: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("content")
    val content: String
)
