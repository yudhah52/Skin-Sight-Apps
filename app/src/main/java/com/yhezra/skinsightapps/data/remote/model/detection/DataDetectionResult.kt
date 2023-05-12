package com.yhezra.skinsightapps.data.remote.model.detection

import com.google.gson.annotations.SerializedName

data class DataDetectionResult(

    @field:SerializedName("date")
    val date: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("classification")
    val classification: List<ClassificationItem?>? = null,

    @field:SerializedName("message")
    val message: String? = null
)
