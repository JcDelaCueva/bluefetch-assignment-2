package com.jcdelacueva.bluefetchassignment2.view.login

import com.jcdelacueva.bluefetchassignment2.base.BasePresenter
import com.jcdelacueva.bluefetchassignment2.base.BaseView

interface LoginContract {

    interface View :
        BaseView<Presenter> {

        fun onProceedToHomePage()

        fun resetErrors()

        fun onCredentialsValidated(username: String, password: String)
    }

    interface Presenter : BasePresenter {

        fun login(username: String, password: String)

        fun validateUserLogIn(username: String, password: String)
    }
}
