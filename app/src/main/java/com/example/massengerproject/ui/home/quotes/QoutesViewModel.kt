package com.example.massengerproject.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.example.massengerproject.data.repositories.QuoteRepository
import com.example.massengerproject.data.repositories.UserRepository
import com.example.massengerproject.util.lazyDeferred

class QoutesViewModel(
    repository: QuoteRepository
) : ViewModel() {


    val qoutes by lazyDeferred {
        repository.getQuotes()
    }


}
