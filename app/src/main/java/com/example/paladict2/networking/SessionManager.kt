package com.example.paladict2.networking

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.paladict2.Constants
import com.example.paladict2.Constants.Companion.PALADINS_SESSION_ID
import com.example.paladict2.Constants.Companion.PALADINS_SESSION_TIME
import com.example.paladict2.MainActivity
import com.example.paladict2.viewmodel.SessionRepository

class SessionManager {

    companion object {

        fun isSessionValid(prefs: SharedPreferences): Boolean {
            val obtainedSessionTime = prefs.getLong(PALADINS_SESSION_TIME, 0)
            return !(System.currentTimeMillis() > obtainedSessionTime + 900000 || obtainedSessionTime == 0.toLong())
        }


        fun createAndSaveSession(
            prefs: SharedPreferences,
            currentActivity: AppCompatActivity
        ) {
            val sessionRepository = SessionRepository()
            val session = sessionRepository.getMutableLiveData()
            session.observe(currentActivity, Observer { obtainedSession ->
                run {
                    val editor = prefs.edit()
                    editor.putString(PALADINS_SESSION_ID, obtainedSession.sessionID)
                    editor.putLong(PALADINS_SESSION_TIME, System.currentTimeMillis())
                    editor.apply()
                    val mainActivity = currentActivity as MainActivity
                    mainActivity.loadViewPagerMenu()
                }
            })
        }

        fun retrieveSessionID(context: Context): String? {
            val sharedPrefs = context.getSharedPreferences(Constants.SHARED_PREF_NAME, 0)
            return sharedPrefs.getString(PALADINS_SESSION_ID, "")
        }
    }
}