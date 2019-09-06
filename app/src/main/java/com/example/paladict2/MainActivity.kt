package com.example.paladict2

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.paladict2.model.Session
import com.example.paladict2.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    var mainViewModel: MainViewModel? = null
    val SHARED_PREF_NAME = "paladict_prefs"
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        createAPISession()
    }

    private fun createAPISession() {
        mainViewModel!!.session.observe(this, Observer {
            obtainedSession -> saveSession(obtainedSession)
        })
    }

    private fun saveSession(obtainedSession: Session?) {
        sharedPreferences = this.getSharedPreferences(SHARED_PREF_NAME, 0)
        val editor = sharedPreferences!!.edit()
        editor.putString(Constants.PALADINS_PC_SESSION_ID, obtainedSession!!.sessionID)
        editor.apply()
    }
}
