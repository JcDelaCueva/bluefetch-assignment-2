package com.jcdelacueva.bluefetchassignment2.view.register

import com.jcdelacueva.bluefetchassignment2.base.BasePresenter
import com.jcdelacueva.bluefetchassignment2.base.BaseView
import com.jcdelacueva.bluefetchassignment2.data.model.RegistrationInfo

class RegisterContract {
    interface View :
        BaseView<Presenter> {

        fun onProceedToHomePage()

        fun resetErrors()

        fun onInputValidated(registrationInfo: RegistrationInfo)
    }

    interface Presenter : BasePresenter {

        fun register(registrationInfo: RegistrationInfo)

        fun validateUserLogIn(registrationInfo: RegistrationInfo)
    }
}