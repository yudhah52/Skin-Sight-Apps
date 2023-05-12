package com.yhezra.skinsightapps.data.remote.model.history

import com.google.gson.annotations.SerializedName

data class HistoryResponse(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("data")
	val data: List<HistoryItem?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

