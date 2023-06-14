package com.yhezra.skinsightapps.data.remote.model.detection

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetectionResponse(

    @field:SerializedName("detection_img")
    val detectionImg: String,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("class")
    val jsonMemberClass: String,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("description")
    val description: String?,

    @field:SerializedName("recommendation")
    val recommendation: String? = null
) : Parcelable
