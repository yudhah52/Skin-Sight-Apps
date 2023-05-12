package com.yhezra.skinsightapps.data.remote.model.history

import com.google.gson.annotations.SerializedName
import com.yhezra.skinsightapps.data.remote.model.detection.ClassificationItem

data class HistoryItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("classification")
	val classification: List<ClassificationItem?>? = null
)
