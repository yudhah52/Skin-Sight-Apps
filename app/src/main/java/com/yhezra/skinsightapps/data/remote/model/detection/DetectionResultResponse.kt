package com.yhezra.skinsightapps.data.remote.model.detection

import com.google.gson.annotations.SerializedName

data class DetectionResultResponse(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("data")
	val data: DataDetectionResult? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)

