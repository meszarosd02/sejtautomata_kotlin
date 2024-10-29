package com.example.sejtautomata_kotlin

import android.app.Application
import androidx.navigation.NavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.ref.WeakReference

class SejtautomataApplication: Application() {
    private var navControllerRef: WeakReference<NavController>? = null
    var bottomNav: BottomNavigationView? = null

    fun setNavController(navController: NavController){
        navControllerRef = WeakReference(navController)
    }
}