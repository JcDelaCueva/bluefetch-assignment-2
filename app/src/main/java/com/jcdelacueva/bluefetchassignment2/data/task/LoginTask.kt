package com.jcdelacueva.bluefetchassignment2.data.task

import com.jcdelacueva.bluefetchassignment2.data.apiInterface
import com.jcdelacueva.bluefetchassignment2.data.model.LoginInfo

object LoginTask {
    suspend fun login(loginInfo: LoginInfo): String {
        val tokenRes = apiInterface.login(loginInfo)
        if (tokenRes.code != null) {
            val message = tokenRes.message ?: ""
            throw IllegalStateException("${tokenRes.code}: $message")
        }

        return tokenRes.token ?: throw IllegalStateException("Token Not Found")
    }
}
