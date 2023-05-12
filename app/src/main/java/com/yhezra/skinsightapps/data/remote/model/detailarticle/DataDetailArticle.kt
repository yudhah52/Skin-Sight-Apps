package com.yhezra.skinsightapps.data.remote.model.detailarticle

import com.google.gson.annotations.SerializedName

data class DataDetailArticle(

    @field:SerializedName("image_url")
    val imageUrl: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null
)
