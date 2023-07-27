package com.example.augfutre.Data

import com.example.augfutre.Utils.Resource

data class AuthResult(
    val passwordError: String? = null,
    val emailError: String? = null,
    val result: Resource<Unit>? = null
)