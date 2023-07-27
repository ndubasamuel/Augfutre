package com.example.augfutre.Data

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("first name")
    var firstName: String? = null,
    @SerializedName("last name")
    var lastName: String? = null,
    @SerializedName("avatar")
    var avatar: String? = null
)