package com.jcdelacueva.bluefetchassignment2.view.login

import com.jcdelacueva.bluefetchassignment2.data.model.LoginInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginPresenter(private val view: LoginContract.View, private val repo: LoginRepo) : LoginContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun login(username: String, password: String) {
        view.onDisableEditing()
        view.showProgress()

        repo.login(LoginInfo(username, password)) { token, error ->
            if (!view.isViewAlive()) {
                return@login
            }

            view.hideProgress()

            if (error == null) {
                view.onProceedToHomePage()
            } else {
                error.printStackTrace()
                view.onEnableEditing()
                view.showError(error)
            }
        }
    }

    override fun validateUserLogIn(username: String, password: String) {
        view.hideKeyboard()

        when {
            !view.isOnline() -> view.showError(Exception("No Internet Connection"))
            username.isBlank() && password.isBlank()-> view.showError(Exception("Fill up username and password"))
            else -> view.onCredentialsValidated(username, password)
        }
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }
}
