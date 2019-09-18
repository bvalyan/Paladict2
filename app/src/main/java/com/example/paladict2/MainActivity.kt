package com.example.paladict2

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.paladict2.Constants.Companion.SHARED_PREF_NAME
import com.example.paladict2.networking.SessionManager
import com.example.paladict2.view.HomeScreenFragmentDirections
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var sharedPreferences: SharedPreferences? = null
    private lateinit var navigationController: NavController

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
        setupNavigation()
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

        return when(item.itemId){
            R.id.champion_menu_item -> {
                val championPage = HomeScreenFragmentDirections.actionHomeScreenFragmentToChampionPageFragment()
                findNavController(R.id.navigationHostFragment).navigate(championPage)
                true
            }
            else -> {
                val championPage = HomeScreenFragmentDirections.actionHomeScreenFragmentToChampionPageFragment()
                findNavController(R.id.navigationHostFragment).navigate(championPage)
                false
            }
        }
    }
}
