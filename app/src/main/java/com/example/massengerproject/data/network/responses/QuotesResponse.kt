package com.example.massengerproject.data.network.responses

import com.example.massengerproject.data.db.Quote

data class QuotesResponse(
    val isSuccessful:Boolean,
    val quotes : List<Quote>
)
