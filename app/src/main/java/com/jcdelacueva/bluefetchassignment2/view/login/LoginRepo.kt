package com.jcdelacueva.bluefetchassignment2.view.login

import com.jcdelacueva.bluefetchassignment2.data.Token
import com.jcdelacueva.bluefetchassignment2.data.task.LoginTask
import com.jcdelacueva.bluefetchassignment2.data.model.LoginInfo
import kotlinx.coroutines.*

class LoginRepo() {
    fun login(loginInfo: LoginInfo, callback: (token: String, error: Exception?)->Unit) {
        val job = Job()
        val scope = CoroutineScope(Dispatchers.Main + job)
        scope.launch {
            try {
                val token = LoginTask.login(loginInfo)
                Token.currentToken = token
                callback(token, null)
            } catch (e: Exception) {
                callback("", e)
            }
        }
    }
}
