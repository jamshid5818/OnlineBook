package jama.bookApp.onlinebook.presentation.user.profile.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.AndroidEntryPoint
import jama.bookApp.onlinebook.R
import jama.bookApp.onlinebook.presentation.admin.AdminActivity
import jama.bookApp.onlinebook.databinding.FragmentSahifamBinding
import jama.bookApp.onlinebook.presentation.user.BaseFragment

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentSahifamBinding>(FragmentSahifamBinding::inflate) {
    override fun onViewCreate() {
        binding.changeTheme.setOnCheckedChangeListener { b, listen ->
            if (listen){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        binding.authors.setOnClickListener {
            navController.navigate(R.id.action_sahifamFragment_to_authorsFragment)
        }

        binding.email.setOnClickListener {
            navController.navigate(R.id.action_sahifamFragment_to_addAuthorFragment)
        }
    }
}