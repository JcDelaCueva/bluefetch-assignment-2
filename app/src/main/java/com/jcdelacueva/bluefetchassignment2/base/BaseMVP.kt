package com.jcdelacueva.bluefetchassignment2.base

import android.content.Context

interface BasePresenter {

    fun start()

    fun stop()
}

interface BaseView<T> {

    fun setPresenter(presenter: T)

    fun showFullScreenProgress()

    fun hideFullScreenProgress()

    fun showProgress()

    fun hideProgress()

    fun showSuccess(message: String)

    fun showMessage(message: String)

    fun showError(e: Exception)

    fun onDisableEditing()

    fun onEnableEditing()

    fun hideKeyboard()

    fun getViewContext(): Context

    fun isViewAlive(): Boolean

    fun isOnline(): Boolean

    fun onSessionError()
}
