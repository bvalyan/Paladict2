package com.example.paladict2

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.paladict2.Constants.Companion.PALADINS_SESSION_ID
import com.example.paladict2.model.Champion
import com.example.paladict2.networking.SessionManager
import com.example.paladict2.viewmodel.MainViewModel
import com.example.paladict2.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    var mainViewModel: MainViewModel? = null
    val SHARED_PREF_NAME = "paladict_prefs"
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = this.getSharedPreferences(SHARED_PREF_NAME, 0)
        if (SessionManager.isSessionValid(sharedPreferences!!)) {
            setUpViewModel()
        } else {
            SessionManager.createAndSaveSession(sharedPreferences!!, this, true)
        }
    }

    private fun setUpMainScreen(obtainedChampions: List<Champion>?) {
        Log.d("MAINSCREEN", obtainedChampions?.size.toString())
    }

    fun setUpViewModel() {
        val sessionID = sharedPreferences!!.getString(PALADINS_SESSION_ID, "") as String
        mainViewModel = ViewModelProviders.of(this, MainViewModelFactory(sessionID))
            .get(MainViewModel::class.java)
        mainViewModel!!.champions.observe(this, Observer { obtainedChampions ->
            setUpMainScreen(obtainedChampions)
        })
    }
}
