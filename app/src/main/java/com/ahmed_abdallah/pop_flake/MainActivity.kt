package com.ahmed_abdallah.pop_flake

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ahmed_abdallah.pop_flake.databinding.ActivityMainBinding
import com.ahmed_abdallah.pop_flake.viewModel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel.getMode()
        handleMode()
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun handleMode() {
        mainViewModel.mode.observe(this) { mode ->
            when (mode) {
                0 -> {
                    getCurrentMode()
                }
                1 -> {
            setDefaultNightMode(MODE_NIGHT_NO)

                }
                else -> {
            setDefaultNightMode(MODE_NIGHT_YES)

                }
            }
        }
    }

    private fun getCurrentMode() {
        val nightModeFlags: Int = resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK
        when (nightModeFlags) {
            Configuration.UI_MODE_NIGHT_YES -> mainViewModel.setMode(2)
            Configuration.UI_MODE_NIGHT_NO -> mainViewModel.setMode(1)
            Configuration.UI_MODE_NIGHT_UNDEFINED -> mainViewModel.setMode(1)
        }
    }
}