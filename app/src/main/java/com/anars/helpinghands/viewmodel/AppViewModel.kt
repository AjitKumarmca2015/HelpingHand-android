package com.anars.helpinghands.viewmodel

import androidx.lifecycle.ViewModel
import com.anars.helpinghands.utils.Prefs

class AppViewModel():ViewModel() {

    fun getInitialDestination(): String {
        val isLoggedIn = Prefs.getBoolean("isLoggedIn", false)
        val isIntroCompleted = Prefs.getBoolean("isIntroCompleted", false)

        return when {
            isLoggedIn && isIntroCompleted -> "home"
            isLoggedIn -> "intro"
            else -> "login"
        }
    }

    // Mark intro as completed
    fun setIntroCompleted() {
        Prefs.putBoolean("isIntroCompleted", true)
    }

    // Mark user as logged in
    fun setLoggedIn() {
        Prefs.putBoolean("isLoggedIn", true)
    }
}