package com.example.augfutre.Repository

import com.example.augfutre.Utils.Resource

interface AuthRepository {
    suspend fun login(loginRequest: AuthRequest): Resource<Unit>
    suspend fun register(registerRequest: AuthRequest): Resource<Unit>
}