package com.example.happyhour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(){
    private lateinit var navController: NavController
    private lateinit var navView: BottomNavigationView
    private lateinit var backImage: ImageView


    private val navControllerListener = NavController.OnDestinationChangedListener { controller, destination, arguments ->
        if(destination.id == R.id.searchFragment) {
            backImage.animate().alpha(1f).duration = 100
        } else {
            backImage.animate().alpha(0f).duration = 100
        }

        if(destination.id == R.id.favesFragment || destination.id == R.id.searchFragment) {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            navView.visibility = android.view.View.VISIBLE
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            navView.visibility = android.view.View.GONE
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.navigation_view)
        backImage = findViewById(R.id.mainBgImage)


        navController = findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener(navControllerListener)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.searchFragment, R.id.favesFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }
    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp();
        return true;
    }
//
//    override fun onBackStackChanged() {
//        shouldDisplayHomeUp()
//    }
//    private fun shouldDisplayHomeUp() {
//        val canGoBack = supportFragmentManager.backStackEntryCount > 1
//        supportActionBar?.setDisplayHomeAsUpEnabled(canGoBack)
//    }
}