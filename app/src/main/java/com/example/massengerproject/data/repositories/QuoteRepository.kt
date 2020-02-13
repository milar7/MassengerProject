package com.example.massengerproject.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.massengerproject.data.db.AppDatabase
import com.example.massengerproject.data.db.Quote
import com.example.massengerproject.data.network.MyApi
import com.example.massengerproject.data.network.SafeApiRequest
import com.example.massengerproject.data.preferances.PreferenceProvider
import com.example.massengerproject.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private val MINIMUM_INTERVAL = 6
class QuoteRepository(
    private val api: MyApi,
    private val db: AppDatabase,
    private val prrfs:PreferenceProvider
) : SafeApiRequest() {

    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    suspend fun getQuotes(): LiveData<List<Quote>> {
      return withContext(Dispatchers.IO){
          //TODO check if have internet then fetch data
          fetchQuotes()
          db.quoteDao().getQoutes()
      }
    }

    private suspend fun fetchQuotes() {
        val lastsaveat=prrfs.getLastSavedAt()

      //  if (lastsaveat==null || isFetchNeeded(LocalDateTime.parse(lastsaveat))) {
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        //}
    }

//    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean {
//
//        return ChronoUnit.HOURS.between(savedAt,LocalDateTime.now() )> MINIMUM_INTERVAL
//
//    }

    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
            //legacy datetime class
           // prrfs.savelastSavedAt(LocalDateTime.now().toString())
            db.quoteDao().saveAllQuotes(quotes)
        }
    }
}