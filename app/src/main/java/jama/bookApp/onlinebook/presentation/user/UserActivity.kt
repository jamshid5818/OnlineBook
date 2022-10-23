package jama.bookApp.onlinebook.presentation.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import jama.bookApp.onlinebook.R
import jama.bookApp.onlinebook.data.utils.SharedPref
import jama.bookApp.onlinebook.databinding.ActivityUserBinding
import jama.bookApp.onlinebook.presentation.admin.AdminActivity

@AndroidEntryPoint
class UserActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserBinding
    private val sharedPref by lazy{
        SharedPref(this)
    }
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        val navController = findNavController(R.id.main_nav_fragment)
        binding.bottomNavMenu.background = null
//        binding.bottomNavMenu.setOnItemSelectedListener { item->
//            when(item.itemId){
//                R.id.miXazratim -> {
//                    navController.navigate(R.id.hazratimFragment)
//                }
//                R.id.miKitob -> {
//                    navController.navigate(R.id.kitobFragment)
//                }
//                R.id.miAudio -> {
//                    navController.navigate(R.id.audioFragment)
//                }
//                R.id.miDokon -> {
//                    navController.navigate(R.id.dokonFragment)
//                }
//                R.id.miSahifam ->{
//                    if (sharedPref.getEmail().toString().isNotEmpty()) {
//                        navController.navigate(R.id.sahifamFragment)
//                    }else{
//                        navController.navigate(R.id.registerFragment)
//                    }
//                }
//            }
//            true
//        }
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mainFragment,
                R.id.booksFragment,
                R.id.audioFragment,
                R.id.shopsFragment,
                R.id.profileFragment,
                R.id.registerFragment
            )
        )
        binding.bottomNavMenu.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.main_nav_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}