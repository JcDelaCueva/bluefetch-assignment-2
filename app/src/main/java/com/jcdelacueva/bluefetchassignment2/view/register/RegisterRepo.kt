package com.jcdelacueva.bluefetchassignment2.view.register

import com.jcdelacueva.bluefetchassignment2.data.Token
import com.jcdelacueva.bluefetchassignment2.data.model.RegistrationInfo
import com.jcdelacueva.bluefetchassignment2.data.task.RegisterTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RegisterRepo {
    fun register(registerInfo: RegistrationInfo, callback: (error: Exception?)->Unit) {
        val job = Job()
        val scope = CoroutineScope(Dispatchers.Main + job)
        scope.launch {
            try {
                val token = RegisterTask.register(registerInfo)
                Token.currentToken = token
                callback(null)
            } catch (e: Exception) {
                callback(e)
            }
        }
    }
}