package com.example.paladict2.utils

import android.content.Context
import com.example.paladict2.Constants
import com.example.paladict2.Constants.Companion.EMPTY_STRING
import com.example.paladict2.Constants.Companion.PLATFORM
import com.example.paladict2.Constants.Companion.PLAYER_ID
import com.example.paladict2.Constants.Companion.PLAYER_NAME
import com.example.paladict2.model.Player

class LoginManager {

    companion object {
        fun isLoggedIn(context: Context?): Boolean {
            val prefs = context?.getSharedPreferences(Constants.SHARED_PREF_NAME, 0)
            return !prefs?.getString(
                PLAYER_NAME,
                EMPTY_STRING
            ).isNullOrBlank() && !prefs?.getString(
                PLAYER_ID,
                EMPTY_STRING
            ).isNullOrBlank() && !prefs?.getString(PLATFORM, "").isNullOrBlank()
        }

        fun retrievedLoggedInPlayer(context: Context?): Player {
            val prefs = context?.getSharedPreferences(Constants.SHARED_PREF_NAME, 0)

            val loggedInPlayer = Player()

            loggedInPlayer.playerID = prefs?.getString(PLAYER_ID, EMPTY_STRING)
            loggedInPlayer.name = prefs?.getString(PLAYER_NAME, EMPTY_STRING)
            loggedInPlayer.platform = prefs?.getString(PLATFORM, EMPTY_STRING)

            return loggedInPlayer
        }
    }
}
