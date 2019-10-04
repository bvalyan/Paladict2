package com.example.paladict2.view

interface SessionCallback {

    fun postLogin(isLoggedIn: Boolean)

    fun postSessionExecution()
}