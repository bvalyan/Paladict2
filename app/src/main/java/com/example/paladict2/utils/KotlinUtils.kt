package com.example.paladict2.utils

import com.example.paladict2.Constants
import com.example.paladict2.model.Platform

class KotlinUtils {

    companion object {

        fun retrievePortalID(platform: Platform): String {
            when (platform) {
                Platform.PC -> return Constants.PALADINS_PC_PORTAL_ID
                Platform.Xbox -> return Constants.PALADINS_XBOX_PORTAL_ID
                Platform.Switch -> return Constants.PALADINS_SWITCH_PORTAL_ID
                Platform.PS4 -> return Constants.PALADINS_PS4_PORTAL_ID
            }
        }

        fun portalToPlatform(portalID: String?): CharSequence? {
            when (portalID) {
                "1" -> return "Hi-Rez"
                "5" -> return "Steam"
                "9" -> return "PS4"
                "10" -> return "Xbox"
                "22" -> return "Switch"
            }
            return "UNKNOWN"
        }
    }
}