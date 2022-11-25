package com.jcdelacueva.bluefetchassignment2.view.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.jcdelacueva.bluefetchassignment2.data.model.RegistrationInfo
import com.jcdelacueva.bluefetchassignment2.databinding.ActivityRegisterBinding
import com.jcdelacueva.bluefetchassignment2.isNetworkAvailable
import com.jcdelacueva.bluefetchassignment2.view.feed.FeedActivity
import com.jcdelacueva.bluefetchassignment2.view.main.MainActivity
import retrofit2.HttpException

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var presenter: RegisterContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        RegisterPresenter(this, RegisterRepo())

        binding.btnRegister.setOnClickListener {
            val firstName = binding.tiFirstName.editText?.text.toString()
            val lastName = binding.tiLastName.editText?.text.toString()
            val userName = binding.tiUserName.editText?.text.toString()
            val password = binding.tiPassword.editText?.text.toString()

            presenter.validateUserLogIn(
                RegistrationInfo(
                    firstName,
                    lastName,
                    userName,
                    password
                )
            )
        }

        binding.btnLogIn.setOnClickListener {
            finish()
        }
    }

    override fun onProceedToHomePage() {
        FeedActivity.start(this)
        finishAffinity()
    }

    override fun resetErrors() {
        binding.apply {
            tiFirstName.error = null
            tiLastName.error = null
            tiUserName.error = null
            tiPassword.error = null
        }
    }

    override fun onInputValidated(registrationInfo: RegistrationInfo) {
        presenter.register(registrationInfo)
    }

    override fun setPresenter(presenter: RegisterContract.Presenter) {
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
                401 -> showMessage("Provide all information")
                402 -> showMessage("Invalid username and password")
                500 -> showMessage("Issue with Database")
            }
        } else {
            showMessage(e.message ?: "")
        }
    }

    override fun onDisableEditing() {
        binding.apply {
            tiFirstName.isEnabled = false
            tiLastName.isEnabled = false
            tiUserName.isEnabled = false
            tiPassword.isEnabled = false
            btnLogIn.isEnabled = false
            btnRegister.isEnabled = false
        }
    }

    override fun onEnableEditing() {
        binding.apply {
            tiFirstName.isEnabled = true
            tiLastName.isEnabled = true
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
            context.startActivity(Intent(context, RegisterActivity::class.java))
        }
    }
}