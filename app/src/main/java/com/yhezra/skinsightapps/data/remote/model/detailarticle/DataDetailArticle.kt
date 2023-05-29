package com.yhezra.skinsightapps.data.remote.model.detailarticle

import com.google.gson.annotations.SerializedName

data class DataDetailArticle(

    @field:SerializedName("imageUrl")
    val imageUrl: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("content")
    val content: String
)
