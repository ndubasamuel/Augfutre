package com.example.augfutre.Model.ModelUseCase

import com.example.augfutre.Data.AuthResult
import com.example.augfutre.Repository.AuthRepository
import com.example.augfutre.Repository.AuthRequest

class SignUpScreen (
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): AuthResult {
        val emailError = if (email.isBlank()) "Username cannot be blank" else null
        val passwordError = if (password.isBlank()) "Password cannot be blank" else null

        if (emailError != null){
            return AuthResult(
                emailError = emailError
            )
        }

        if (passwordError!=null){
            return AuthResult(
                passwordError = passwordError
            )
        }

        val registerRequest = AuthRequest(
            email = email.trim(),
            password = password.trim()
        )

        return AuthResult(
            result = repository.register(registerRequest)
        )
    }
}