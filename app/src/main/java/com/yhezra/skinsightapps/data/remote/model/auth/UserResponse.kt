package com.yhezra.skinsightapps.data.remote.model.auth

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("data")
	val data: DataUser,

	@field:SerializedName("status")
	val status: String
)

