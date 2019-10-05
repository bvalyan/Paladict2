package com.example.paladict2.utils

import android.graphics.Color
import android.graphics.Typeface
import com.example.paladict2.Constants
import com.example.paladict2.model.Platform
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend

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

        fun formatPaladinsRoleName(role: String?): String {
            return role!!.removePrefix("Paladins").trim()
        }

        fun pieChartSetup(chartToFormat: PieChart) {
            chartToFormat.isDrawHoleEnabled = true
            chartToFormat.holeRadius = 85f
            chartToFormat.setCenterTextColor(Color.WHITE)
            chartToFormat.setCenterTextSize(18f)
            chartToFormat.setCenterTextTypeface(Typeface.create("sans-serif-thin", Typeface.NORMAL))
            chartToFormat.animateX(2000)
            chartToFormat.animateY(2000)
            chartToFormat.setTouchEnabled(false)
            chartToFormat.setHoleColor(Color.TRANSPARENT)
            chartToFormat.legend.textColor = Color.WHITE
            chartToFormat.legend.xEntrySpace = 20f
            chartToFormat.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            chartToFormat.legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
            chartToFormat.setDrawEntryLabels(false)
            chartToFormat.description.isEnabled = false
            chartToFormat.legend.form = Legend.LegendForm.CIRCLE
            chartToFormat.description.textColor = Color.WHITE
            chartToFormat.legend.isWordWrapEnabled = true
            chartToFormat.transparentCircleRadius = 50f
        }
    }
}