package com.example.paladict2

import android.content.SharedPreferences
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.paladict2.Constants.Companion.PALADINS_SESSION_ID
import com.example.paladict2.model.Champion
import com.example.paladict2.networking.SessionManager
import com.example.paladict2.view.MainMenuPagerAdapter
import com.example.paladict2.viewmodel.MainViewModel
import com.example.paladict2.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mainViewModel: MainViewModel? = null
    val SHARED_PREF_NAME = "paladict_prefs"
    private var sharedPreferences: SharedPreferences? = null
    private lateinit var mainMenuViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainMenuViewPager = main_menu_view_pager
        setUpFAB()
        sharedPreferences = this.getSharedPreferences(SHARED_PREF_NAME, 0)
        if (SessionManager.isSessionValid(sharedPreferences!!)) {
            setUpViewModel()
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

    private fun setUpMainScreen(obtainedChampions: List<Champion>?) {
        Log.d("MAINSCREEN", obtainedChampions?.size.toString())
        loadViewPagerMenu()
    }

    private fun loadViewPagerMenu() {
        val mainPagerAdapter = MainMenuPagerAdapter(supportFragmentManager)
        mainMenuViewPager.adapter = mainPagerAdapter
        main_tabs.setupWithViewPager(mainMenuViewPager)
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
