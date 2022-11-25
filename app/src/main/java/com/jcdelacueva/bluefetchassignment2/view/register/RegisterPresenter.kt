package com.jcdelacueva.bluefetchassignment2.view.register

import com.jcdelacueva.bluefetchassignment2.data.model.RegistrationInfo

class RegisterPresenter(private val view: RegisterContract.View, private val repo: RegisterRepo) : RegisterContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun register(registrationInfo: RegistrationInfo) {
        view.onDisableEditing()
        view.showProgress()

        repo.register(registrationInfo) { error ->

            if (!view.isViewAlive()) {
                return@register
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

    override fun validateUserLogIn(registrationInfo: RegistrationInfo) {
        view.hideKeyboard()

        when {
            !view.isOnline() -> view.showError(Exception("No Internet Connection"))
            !validateRegistrationData(registrationInfo) -> view.showError(Exception("Fill up all the fields"))
            else -> view.onInputValidated(registrationInfo)
        }
    }

    private fun validateRegistrationData(registrationInfo: RegistrationInfo): Boolean {
        return registrationInfo.firstName.isNotBlank() &&
            registrationInfo.lastName.isNotBlank() &&
            registrationInfo.userName.isNotBlank() &&
            registrationInfo.password.isNotBlank()
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }
}