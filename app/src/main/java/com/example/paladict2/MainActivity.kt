package com.example.paladict2

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.paladict2.Constants.Companion.SHARED_PREF_NAME
import com.example.paladict2.networking.SessionManager
import com.example.paladict2.view.HomeScreenFragment
import com.example.paladict2.view.addFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpFAB()
        sharedPreferences = this.getSharedPreferences(SHARED_PREF_NAME, 0)
        if (SessionManager.isSessionValid(sharedPreferences!!)) {
            loadViewPagerMenu()
        } else {
            SessionManager.createAndSaveSession(sharedPreferences!!, this)
        }
    }

    private fun setUpFAB() {
        fab_close_btn.setOnClickListener { paladins_fab.isExpanded = !paladins_fab.isExpanded }
        paladins_fab.setOnClickListener {
            paladins_fab.isExpanded = !paladins_fab.isExpanded
        }
    }

    internal fun loadViewPagerMenu() {
        addFragment(HomeScreenFragment.newInstance("Home"), R.id.fragment_container)
    }
}
