package com.example.sejtautomata_kotlin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        (application as SejtautomataApplication).setNavController(navController)
        /*(application as SejtautomataApplication).bottomNav = findViewById(R.id.bottom_nav)
        (application as SejtautomataApplication).bottomNav!!.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.one_dimension -> {
                    navController.navigate(R.id.homeFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.two_dimension -> {
                    navController.navigate(R.id.homeFragment2)
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener false
            }
        }*/
    }
}