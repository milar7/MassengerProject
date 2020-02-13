package com.example.massengerproject.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.massengerproject.R
import com.example.massengerproject.data.db.entities.User
import com.example.massengerproject.databinding.ActivityLoginBinding
import com.example.massengerproject.ui.home.HomeActivity
import com.example.massengerproject.util.hide
import com.example.massengerproject.util.show
import com.example.massengerproject.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListener,KodeinAware {


    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val networkOnMainThreadException = NetWorkConnentionInterceptor(this)
//        val api = MyApi(networkOnMainThreadException)
//        val db = AppDatabase(this)
//        val repository = UserRepository(api, db)
//        val factory = AuthViewModelFactory(repository)



        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_login
        )
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        viewModel.authListener = this

        viewModel.getLoggedinUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }

            }

        })


    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
       // root_layout.snackbar("${user.name}is logged in")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
       // toast(message)
         root_layout_l.snackbar(message)

    }


}
