package com.example.augfutre.Model.Response

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("token")
    val token : String
) {
}