package com.first.showcasetrial

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.first.showcasetrial.Fragments.HomeFreagment
import com.first.showcasetrial.Fragments.NotificationsFragment
import com.first.showcasetrial.Fragments.ProfileFragment
import com.first.showcasetrial.Fragments.SearchFragment
import com.first.showcasetrial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {
                    moveToFragment((HomeFreagment()))
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_search -> {
                moveToFragment((SearchFragment()))
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_post -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_notification -> {
                moveToFragment((NotificationsFragment()))
                return@OnNavigationItemSelectedListener true

            }
            R.id.nav_profile -> {
                moveToFragment((ProfileFragment()))
                return@OnNavigationItemSelectedListener true

            }
        }

        false
    }


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        moveToFragment((HomeFreagment()))

    }

        private fun moveToFragment(fragment: Fragment)
        {
            val fragmentTrans = supportFragmentManager.beginTransaction()
            fragmentTrans.replace(R.id.fragment_container, fragment)
            fragmentTrans.commit()
        }
}