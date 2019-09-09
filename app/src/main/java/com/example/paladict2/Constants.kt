package com.example.paladict2

class Constants {

    companion object {
        @kotlin.jvm.JvmField

        //Access Constants
        val REDIRECT_URI = "https://optimalotaku.com/paladict/"
        val RESPONSE_TYPE = "?response_type=code"
        const val PALADINS_API_URI = "http://api.paladins.com/paladinsapi.svc/"
        const val PALADINS_DEV_ID = "2568"
        const val PALADINS_AUTH_KEY = "660ACC8BED2044B986BE8329F8856B6B"
        val ADMOB_KEY = "ca-app-pub-6213612804571704~5555108114"
        val ADMOB_TEST_BANNER = "ca-app-pub-3940256099942544/6300978111"
        val ADMOB_LIVE_BANNER = "ca-app-pub-6213612804571704/7460459994"

        val PALADINS_SESSION_ID = "session_id"
        val PALADINS_SESSION_TIME = "session_time"

        val PALADINS_XBOX_SESSION_ID = "xbox_session_id"
        val PALADINS_XBOX_SESSION_TIME = "xbox_session_time"

        val PALADINS_PS4_SESSION_ID = "ps4_session_id"
        val PALADINS_PS4_SESSION_TIME = "ps4_session_time"

        //Paladins Queues

        val PALADINS_ONSLAUGHT_QUEUE_ID = "452"
        val PALADINS_SIEGE_QUEUE_ID = "465"
        val PALADINS_COMPETITIVE_QUEUE_ID = "428"
        val PALADINS_TEAM_DM_QUEUE_ID = "469"
        val PALADINS_CASUAL_QUEUE_ID = "424"
    }
}
