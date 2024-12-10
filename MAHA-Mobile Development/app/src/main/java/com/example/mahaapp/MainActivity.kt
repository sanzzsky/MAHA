package com.example.mahaapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ui.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Menghubungkan menu navigasi
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        @Suppress("DEPRECATION")
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homepage -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.activity2 -> {
                    loadFragment(HealthFragment())
                    true
                }
                R.id.activity3 -> {
                    loadFragment(RoutinesFragment())
                    true
                }
                R.id.profilpage -> {
                    loadFragment(ProfilFragment())
                    true
                }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            bottomNavigationView.selectedItemId = R.id.homepage
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }
}
