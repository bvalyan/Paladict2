package com.example.paladict2

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.paladict2.Constants.Companion.PALADINS_SESSION_ID
import com.example.paladict2.Constants.Companion.PALADINS_SESSION_TIME
import com.example.paladict2.Constants.Companion.PLATFORM
import com.example.paladict2.Constants.Companion.PLAYER_ID
import com.example.paladict2.Constants.Companion.PLAYER_NAME
import com.example.paladict2.networking.SessionManager
import com.example.paladict2.utils.LoginManager
import com.example.paladict2.view.HomeScreenFragmentDirections
import com.example.paladict2.view.SessionCallback
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    SessionCallback {

    //TODO: Implement Firebase

    override fun postSessionExecution() {
        //
    }

    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpFAB()
        setupNavigation()
    }


    private fun setUpFAB() {
        fab_close_btn.setOnClickListener { paladins_fab.isExpanded = !paladins_fab.isExpanded }
        paladins_fab.setOnClickListener {
            paladins_fab.isExpanded = !paladins_fab.isExpanded
        }
    }

    override fun onSupportNavigateUp() =
        NavigationUI.navigateUp(findNavController(R.id.navigationHostFragment), drawer_layout)

    private fun setupNavigation() {
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navigationController = findNavController(R.id.navigationHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navigationController, drawer_layout)
        NavigationUI.setupWithNavController(navigationView, navigationController)
        navigationView.menu.findItem(R.id.logout_item).isVisible = LoginManager.isLoggedIn(this)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.isChecked = true
        drawer_layout.closeDrawers()

        return when (item.itemId) {
            R.id.champion_menu_item -> {
                val championPage =
                    HomeScreenFragmentDirections.actionHomeScreenFragmentToChampionPageFragment()
                findNavController(R.id.navigationHostFragment).navigate(championPage)
                true
            }
            R.id.items_menu_item -> {

                true
            }
            R.id.logout_item -> {
                val sharedPreferences: SharedPreferences? =
                    getSharedPreferences(Constants.SHARED_PREF_NAME, 0)
                sharedPreferences!!.edit()
                    .putString(PALADINS_SESSION_ID, "")
                    .putLong(PALADINS_SESSION_TIME, 0)
                    .putString(PLAYER_NAME, "")
                    .putString(PLAYER_ID, "")
                    .putString(PLATFORM, "")
                    .apply()
                postLogin(false)
                restartActivity()
                true
            }
            else -> {
                val championPage =
                    HomeScreenFragmentDirections.actionHomeScreenFragmentToChampionPageFragment()
                findNavController(R.id.navigationHostFragment).navigate(championPage)
                false
            }
        }
    }

    private fun restartActivity() {
        finish()
        overridePendingTransition(0, 0)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

    override fun onResume() {
        super.onResume()
        if (!SessionManager.isSessionValid(this)) {
            SessionManager.createAndSaveSession(this, this, this)
        }
    }

    override fun postLogin(isLoggedIn: Boolean) {
        runOnUiThread {
            navigationView.menu.findItem(R.id.logout_item).isVisible = isLoggedIn
        }
    }
}
