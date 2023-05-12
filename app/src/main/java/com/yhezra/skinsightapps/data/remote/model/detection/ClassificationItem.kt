package com.yhezra.skinsightapps.data.remote.model.detection

import com.google.gson.annotations.SerializedName

data class ClassificationItem(
    @field:SerializedName("percentage")
    val percentage: String? = null,

    @field:SerializedName("detection_result")
    val detectionResult: String? = null
)
