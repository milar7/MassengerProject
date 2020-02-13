package com.example.massengerproject.data.network.responses

import com.example.massengerproject.data.db.entities.User


data class AuthResponse(
    val isSuccessful : Boolean?,
    val message: String?,
    val user: User?
)

