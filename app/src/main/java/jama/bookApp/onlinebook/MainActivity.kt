package jama.bookApp.onlinebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import jama.bookApp.onlinebook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.main_nav_fragment)
        binding.bottomNavMenu.background = null

        binding.bottomNavMenu.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.miXazratim -> {
                    navController.navigate(R.id.hazratimFragment)
                }
                R.id.miKitob -> {
                    navController.navigate(R.id.kitobFragment)
                }
                R.id.miAudio -> {
                    navController.navigate(R.id.audioFragment)
                }
                R.id.miDokon -> {
                    navController.navigate(R.id.dokonFragment)
                }
                R.id.miSahifam->{
                    navController.navigate(R.id.sahifamFragment)
                }
            }
            true
        }
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.hazratimFragment,
                R.id.kitobFragment,
                R.id.audioFragment,
                R.id.dokonFragment,
                R.id.sahifamFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.main_nav_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}