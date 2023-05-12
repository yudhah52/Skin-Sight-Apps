package com.yhezra.skinsightapps.data.remote.model.article

import com.google.gson.annotations.SerializedName

data class ArticleResponse(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("data")
	val data: List<ArticleItem?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)
