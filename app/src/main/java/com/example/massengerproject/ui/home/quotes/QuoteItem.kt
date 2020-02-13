package com.example.massengerproject.ui.home.quotes

import com.example.massengerproject.R
import com.example.massengerproject.data.db.Quote
import com.example.massengerproject.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(
    private val quote:Quote

):BindableItem<ItemQuoteBinding>() {

    override fun getLayout()= R.layout.item_quote
    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)
    }
}