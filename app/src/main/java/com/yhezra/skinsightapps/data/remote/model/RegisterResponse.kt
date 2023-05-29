package com.yhezra.skinsightapps.data.remote.model

import com.google.gson.annotations.SerializedName
import com.yhezra.skinsightapps.data.remote.model.detailarticle.DataDetailArticle

data class RegisterResponse(

    @field:SerializedName("data")
    val data: DataDetailArticle,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)