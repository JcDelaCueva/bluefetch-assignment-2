package com.jcdelacueva.bluefetchassignment2.view.splash

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jcdelacueva.bluefetchassignment2.R
import com.jcdelacueva.bluefetchassignment2.data.Token
import com.jcdelacueva.bluefetchassignment2.view.feed.FeedActivity
import com.jcdelacueva.bluefetchassignment2.view.login.LoginActivity
import com.jcdelacueva.bluefetchassignment2.view.main.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Token.loadTokenFromCache(this)
        if (Token.currentToken.isNullOrBlank()) {
            LoginActivity.start(this)
        } else {
            FeedActivity.start(this)
        }

        finishAffinity()
    }
}