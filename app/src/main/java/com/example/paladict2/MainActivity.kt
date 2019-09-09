package com.example.paladict2

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.paladict2.Constants.Companion.PALADINS_SESSION_ID
import com.example.paladict2.model.Champion
import com.example.paladict2.networking.SessionManager
import com.example.paladict2.view.HomeScreenFragment
import com.example.paladict2.viewmodel.MainViewModel
import com.example.paladict2.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mainViewModel: MainViewModel? = null
    val SHARED_PREF_NAME = "paladict_prefs"
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initializeNavDrawer()
        sharedPreferences = this.getSharedPreferences(SHARED_PREF_NAME, 0)
        if (SessionManager.isSessionValid(sharedPreferences!!)) {
            setUpViewModel()
        } else {
            SessionManager.createAndSaveSession(sharedPreferences!!, this)
        }
    }

    private fun initializeNavDrawer() {
        // Initialize the action bar drawer toggle instance
        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        ){
            override fun onDrawerClosed(view: View){
                super.onDrawerClosed(view)
                //toast("Drawer closed")
            }

            override fun onDrawerOpened(drawerView: View){
                super.onDrawerOpened(drawerView)
                //toast("Drawer opened")
            }
        }
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerToggle.isDrawerSlideAnimationEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item_1 -> Log.d("","")
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun setUpMainScreen(obtainedChampions: List<Champion>?) {
        Log.d("MAINSCREEN", obtainedChampions?.size.toString())
        supportFragmentManager
            .beginTransaction()
            .add(R.id.activity_fragment, HomeScreenFragment.newInstance())
            .commit()
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
