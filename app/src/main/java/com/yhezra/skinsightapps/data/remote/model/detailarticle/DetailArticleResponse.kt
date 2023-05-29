package com.yhezra.skinsightapps.data.remote.model.detailarticle

import com.google.gson.annotations.SerializedName

data class DetailArticleResponse(

	@field:SerializedName("data")
	val data: DataDetailArticle,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)
