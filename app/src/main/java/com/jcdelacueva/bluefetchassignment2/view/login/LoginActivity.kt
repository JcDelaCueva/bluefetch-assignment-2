package com.jcdelacueva.bluefetchassignment2.view.login

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import com.jcdelacueva.bluefetchassignment2.databinding.ActivityLoginBinding
import com.jcdelacueva.bluefetchassignment2.isNetworkAvailable
import com.jcdelacueva.bluefetchassignment2.view.feed.FeedActivity
import com.jcdelacueva.bluefetchassignment2.view.main.MainActivity
import com.jcdelacueva.bluefetchassignment2.view.register.RegisterActivity
import retrofit2.HttpException

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        LoginPresenter(this, LoginRepo())

        binding.btnLogIn.setOnClickListener {
            val userName = binding.tiUserName.editText?.text.toString()
            val password = binding.tiPassword.editText?.text.toString()
            presenter.validateUserLogIn(userName, password)
        }

        binding.btnRegister.setOnClickListener {
            RegisterActivity.start(this@LoginActivity)
        }
    }

    override fun onProceedToHomePage() {
        FeedActivity.start(this)
        finishAffinity()
    }

    override fun resetErrors() {
        binding.tiUserName.error = null
        binding.tiPassword.error = null
    }

    override fun onCredentialsValidated(username: String, password: String) {
        presenter.login(username, password)
    }

    override fun setPresenter(presenter: LoginContract.Presenter) {
        this.presenter = presenter
    }

    override fun showFullScreenProgress() {

    }

    override fun hideFullScreenProgress() {

    }

    override fun showProgress() {
        binding.cardProgress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.cardProgress.visibility = View.GONE
    }

    override fun showSuccess(message: String) {

    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showError(e: Exception) {
        if (e is HttpException) {
            when (e.code()) {
                401 -> showMessage("Provide username and password")
                403 -> showMessage("Invalid username and password")
            }
        } else {
            showMessage(e.message ?: "")
        }
    }

    override fun onDisableEditing() {
        binding.apply {
            tiUserName.isEnabled = false
            tiPassword.isEnabled = false
            btnLogIn.isEnabled = false
            btnRegister.isEnabled = false
        }
    }

    override fun onEnableEditing() {
        binding.apply {
            tiUserName.isEnabled = true
            tiPassword.isEnabled = true
            btnLogIn.isEnabled = true
            btnRegister.isEnabled = true
        }
    }

    override fun hideKeyboard() {
        currentFocus?.let {
            val imm: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    override fun getViewContext(): Context {
        return this
    }

    override fun isViewAlive(): Boolean {
        return !(isFinishing || isDestroyed)
    }

    override fun isOnline(): Boolean {
        return isNetworkAvailable(this)
    }

    override fun onSessionError() {

    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }
}