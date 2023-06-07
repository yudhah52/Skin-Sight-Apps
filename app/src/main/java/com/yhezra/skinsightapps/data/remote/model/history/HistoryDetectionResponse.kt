package com.yhezra.skinsightapps.data.remote.model.history

import com.google.gson.annotations.SerializedName

data class HistoryDetectionResponse(

	@field:SerializedName("data")
	val data: List<HistoryDetectionItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)
