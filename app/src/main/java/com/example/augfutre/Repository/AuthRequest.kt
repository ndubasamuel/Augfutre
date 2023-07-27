package com.example.augfutre.Repository

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String
)