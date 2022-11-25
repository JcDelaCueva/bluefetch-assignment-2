package com.jcdelacueva.bluefetchassignment2.data.task

import com.jcdelacueva.bluefetchassignment2.data.apiInterface
import com.jcdelacueva.bluefetchassignment2.data.model.LoginInfo
import com.jcdelacueva.bluefetchassignment2.data.model.RegistrationInfo

object RegisterTask {
    suspend fun register(registrationInfo: RegistrationInfo): String {
        val tokenRes = apiInterface.register(registrationInfo)
        if (tokenRes.code != null) {
            val message = tokenRes.message ?: ""
            throw IllegalStateException("${tokenRes.code}: $message")
        }

        return tokenRes.token ?: throw IllegalStateException("Token Not Found")
    }
}
