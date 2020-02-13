package com.example.massengerproject.ui.home.profile

import androidx.lifecycle.ViewModel
import com.example.massengerproject.data.repositories.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {

val user = repository.getUser()
}
