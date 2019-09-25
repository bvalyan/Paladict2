package com.example.paladict2

import com.example.paladict2.model.Platform

class KotlinUtils {

    companion object {

        public fun retrievePortalID(platform: Platform): String {
            when (platform) {
                Platform.PC -> return Constants.PALADINS_PC_PORTAL_ID
                Platform.Xbox -> return Constants.PALADINS_XBOX_PORTAL_ID
                Platform.Switch -> return Constants.PALADINS_SWITCH_PORTAL_ID
                Platform.PS4 -> return Constants.PALADINS_PS4_PORTAL_ID
            }
        }
    }
}