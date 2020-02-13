package com.example.massengerproject.data.repositories


import com.example.massengerproject.data.db.AppDatabase
import com.example.massengerproject.data.db.entities.User
import com.example.massengerproject.data.network.MyApi
import com.example.massengerproject.data.network.SafeApiRequest
import com.example.massengerproject.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun userSignup(
        name: String,
        email: String,
        password: String
    ):AuthResponse{
        return apiRequest {
            api.userSignup(name, email, password)
        }
    }

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest {
            api.userLogin(email, password)
        }

    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getuser()
}