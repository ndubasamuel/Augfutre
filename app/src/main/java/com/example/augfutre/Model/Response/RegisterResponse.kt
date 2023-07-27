package com.example.augfutre.Model.Response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("id")
    val id : String,
    @SerializedName("token")
    val token : String
)