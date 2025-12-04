package com.example.bottomnavigationdemo.ui.pages.models

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/login")
    suspend fun login(@Body request: LoginRequest): ApiResponse<Estudiante>

    @POST("api/register")
    suspend fun register(@Body request: RegisterRequest): ApiResponse<Estudiante>
}