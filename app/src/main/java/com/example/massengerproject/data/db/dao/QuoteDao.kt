package com.example.massengerproject.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.massengerproject.data.db.Quote

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun saveAllQuotes(quotes:List<Quote>)

    @Query( "SELECT * FROM Quote " )
    fun getQoutes() : LiveData<List<Quote>>
}