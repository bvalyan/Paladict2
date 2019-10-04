package com.example.paladict2

class Constants {

    companion object {

        @kotlin.jvm.JvmField

        //Access Constants
        val PALADINS_SELECTED_CHAMPION = "selectedChampion"
        val REDIRECT_URI = "https://optimalotaku.com/paladict/"
        val RESPONSE_TYPE = "?response_type=code"
        const val PALADINS_API_URI = "http://api.paladins.com/paladinsapi.svc/"
        const val PALADINS_DEV_ID = "2568"
        const val PALADINS_AUTH_KEY = "660ACC8BED2044B986BE8329F8856B6B"
        const val SHARED_PREF_NAME = "paladict_prefs"
        val ADMOB_KEY = "ca-app-pub-6213612804571704~5555108114"
        val ADMOB_TEST_BANNER = "ca-app-pub-3940256099942544/6300978111"
        val ADMOB_LIVE_BANNER = "ca-app-pub-6213612804571704/7460459994"

        val PALADINS_SESSION_ID = "session_id"
        val PALADINS_SESSION_TIME = "session_time"

        val PLAYER_NAME = "player_name"
        val PLAYER_ID =  "player_id"
        val PLATFORM = "player_platform"

        //Paladins Queues

        val PALADINS_ONSLAUGHT_QUEUE_ID = "452"
        val PALADINS_SIEGE_QUEUE_ID = "465"
        val PALADINS_COMPETITIVE_QUEUE_ID = "428"
        val PALADINS_TEAM_DM_QUEUE_ID = "469"
        val PALADINS_CASUAL_QUEUE_ID = "424"

        //Paladins Portals

        const val PALADINS_PS4_PORTAL_ID = "9"
        const val PALADINS_SWITCH_PORTAL_ID = "22"
        const val PALADINS_PC_PORTAL_ID = "5"
        const val PALADINS_XBOX_PORTAL_ID = "10"
    }
}
