package com.example.massengerproject.ui.auth

import androidx.lifecycle.LiveData
import com.example.massengerproject.data.db.entities.User

interface AuthListener {
    fun onStarted()

    fun onSuccess(user: User)

    fun onFailure(message:String)
}