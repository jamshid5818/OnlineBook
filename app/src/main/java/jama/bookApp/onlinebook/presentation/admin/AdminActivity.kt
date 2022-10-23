package jama.bookApp.onlinebook.presentation.admin

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
import jama.bookApp.onlinebook.databinding.ActivityAdminBinding

@AndroidEntryPoint
class AdminActivity : AppCompatActivity() {
    lateinit var binding:ActivityAdminBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE)
        val navController = findNavController(R.id.main_admin_fragment)
        binding.bottomNavMenu.background = null
        binding.bottomNavMenu.menu.getItem(1).isEnabled = false
//        binding.bottomNavMenu.setOnItemSelectedListener { item->
//            when(item.itemId){
//                R.id.adHome -> {
//                    navController.navigate(R.id.homeFragment)
//                }
//                R.id.adProfil -> {
//                    navController.navigate(R.id.profilFragment)
//                }
//            }
//            true
//        }
        binding.bottomNavMenu.setupWithNavController(navController)
        binding.addBook.setOnClickListener {
            navController.navigate(R.id.isAudioAndMuslimBookFragment)
        }
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.profileFragment,
                R.id.isAudioAndMuslimBookFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.main_admin_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}