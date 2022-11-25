package com.jcdelacueva.bluefetchassignment2.data

import android.content.Context
import android.content.SharedPreferences

object Token {
    private const val SHARED_PREF = "sharedPref"
    private const val TOKEN = "token"
    private lateinit var prefs: SharedPreferences

    var currentToken: String? = null
        set(value) {
            prefs.edit().putString(TOKEN, value).apply()
            field = value
        }

    fun loadTokenFromCache(context: Context) {
        prefs = context.getSharedPreferences(SHARED_PREF, 0)
        currentToken = prefs.getString(TOKEN, null)
    }
}
