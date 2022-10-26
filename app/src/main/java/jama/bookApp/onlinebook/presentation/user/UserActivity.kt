package jama.bookApp.onlinebook.presentation.user

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.*
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

        val navController = findNavController(R.id.main_nav_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.hazratimFragment,
                R.id.sahifamFragment,
                R.id.audioFragment,
                R.id.kitobFragment,
                R.id.dokonFragment,
                R.id.profileFragment,
                R.id.registerFragment
            )
        )

        binding.bottomNavMenu.setupWithNavController(navController)
        setupActionBarWithNavController(navController,appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.main_nav_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}