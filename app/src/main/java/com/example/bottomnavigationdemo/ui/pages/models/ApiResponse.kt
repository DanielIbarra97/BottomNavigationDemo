package com.example.bottomnavigationdemo.ui.pages.models

data class ApiResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T?
)