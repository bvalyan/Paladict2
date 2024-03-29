package com.example.paladict2.networking

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.paladict2.Constants
import com.example.paladict2.Constants.Companion.PALADINS_SESSION_ID
import com.example.paladict2.Constants.Companion.PALADINS_SESSION_TIME
import com.example.paladict2.view.SessionCallback
import com.example.paladict2.viewmodel.repositories.SessionRepository

class SessionManager {

    companion object {

        fun isSessionValid(context: Context): Boolean {
            val prefs = context.getSharedPreferences(Constants.SHARED_PREF_NAME, 0)
            val obtainedSessionTime = prefs.getLong(PALADINS_SESSION_TIME, 0)
            val obtainedSession = prefs.getString(PALADINS_SESSION_ID, "")
            return !(System.currentTimeMillis() > obtainedSessionTime + 900000 || obtainedSessionTime == 0.toLong() || obtainedSession.isNullOrBlank())
        }


        fun createAndSaveSession(
            context: Context,
            lifeCycleOwner: LifecycleOwner,
            sessionCallback: SessionCallback
        ) {
            val prefs = context.getSharedPreferences(Constants.SHARED_PREF_NAME, 0)
            val sessionRepository = SessionRepository()
            val session = sessionRepository.getMutableLiveData()
            session.observe(lifeCycleOwner, { obtainedSession ->
                run {
                    val editor = prefs.edit()
                    editor.putString(PALADINS_SESSION_ID, obtainedSession.sessionID)
                    editor.putLong(PALADINS_SESSION_TIME, System.currentTimeMillis())
                    editor.apply()
                }
            })
        }

        fun retrieveSessionID(context: Context?): String? {
            val sharedPrefs = context?.getSharedPreferences(Constants.SHARED_PREF_NAME, 0)
            return sharedPrefs?.getString(PALADINS_SESSION_ID, "")
        }
    }
}