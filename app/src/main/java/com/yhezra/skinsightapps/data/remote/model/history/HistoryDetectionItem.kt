package com.yhezra.skinsightapps.data.remote.model.history

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HistoryDetectionItem(

    @field:SerializedName("datetime")
    val datetime: String,

    @field:SerializedName("predicted_class")
    val predictedClass: String,

    @field:SerializedName("detection_img")
    val detectionImg: String,

    @field:SerializedName("type")
    val type: String
) : Parcelable
