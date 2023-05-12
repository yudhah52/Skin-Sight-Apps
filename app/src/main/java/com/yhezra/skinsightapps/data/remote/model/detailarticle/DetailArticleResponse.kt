package com.yhezra.skinsightapps.data.remote.model.detailarticle

import com.google.gson.annotations.SerializedName

data class DetailArticleResponse(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("data")
	val data: DataDetailArticle? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)
