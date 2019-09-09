package com.example.paladict2

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.paladict2.Constants.Companion.PALADINS_SESSION_ID
import com.example.paladict2.Constants.Companion.PALADINS_SESSION_TIME
import com.example.paladict2.model.Session
import com.example.paladict2.viewmodel.MainViewModel
import com.example.paladict2.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    var mainViewModel: MainViewModel? = null
    val SHARED_PREF_NAME = "paladict_prefs"
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = this.getSharedPreferences(SHARED_PREF_NAME, 0)
        var sessionTime = sharedPreferences?.getLong(PALADINS_SESSION_TIME, 0)
        var sessionID = sharedPreferences?.getString(PALADINS_SESSION_ID, "")
        mainViewModel = ViewModelProviders.of(this, MainViewModelFactory(sessionID!!)).get(MainViewModel::class.java)
        if (Utils.isAPISessionExpired(sessionTime as Long, sessionID)) {
            createAPISession()
        } else fetchChampions()


    }

    private fun fetchChampions() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun createAPISession() {
        mainViewModel!!.session.observe(this, Observer { obtainedSession ->
            saveSession(obtainedSession)
            fetchChampions()
        })
    }

    private fun saveSession(obtainedSession: Session?) {
        val editor = sharedPreferences!!.edit()
        editor.putString(PALADINS_SESSION_ID, obtainedSession!!.sessionID)
        editor.putLong(PALADINS_SESSION_TIME, System.currentTimeMillis())
        editor.apply()
    }
}
