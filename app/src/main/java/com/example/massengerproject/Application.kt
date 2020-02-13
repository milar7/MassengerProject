package com.example.massengerproject


import android.app.Application
import com.example.massengerproject.data.db.AppDatabase
import com.example.massengerproject.data.network.MyApi
import com.example.massengerproject.data.network.NetWorkConnentionInterceptor
import com.example.massengerproject.data.preferances.PreferenceProvider
import com.example.massengerproject.data.repositories.QuoteRepository
import com.example.massengerproject.data.repositories.UserRepository
import com.example.massengerproject.ui.auth.AuthViewModelFactory
import com.example.massengerproject.ui.home.profile.ProfileViewModelFactory
import com.example.massengerproject.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class Application : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@Application))
        bind() from singleton { NetWorkConnentionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from singleton { QuoteRepository(instance(), instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { QuotesViewModelFactory(instance()) }

//test
        //first spring

    }
}