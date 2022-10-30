package jama.bookApp.onlinebook.presentation.user.profile.profile

import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.AndroidEntryPoint
import jama.bookApp.onlinebook.R
import jama.bookApp.onlinebook.data.utils.SharedPref
import jama.bookApp.onlinebook.databinding.FragmentSahifamBinding
import jama.bookApp.onlinebook.presentation.user.BaseFragment

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentSahifamBinding>(FragmentSahifamBinding::inflate) {
    private val shared by lazy {
        SharedPref(requireContext())
    }
    override fun onViewCreate() {
        binding.changeTheme.setOnCheckedChangeListener { b, listen ->
            if (listen){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        if (shared.getEmail()!=""){
            binding.email.text = shared.getEmail()
            binding.name.text = shared.getName()
        }else{
            binding.email.text = "Ro'yxatdan o'tish"
            binding.name.text = ""
        }
        binding.authors.setOnClickListener {
            navController.navigate(R.id.action_sahifamFragment_to_authorsFragment)
        }
        binding.dataProfil.setOnClickListener {
            if (shared.getEmail()!=""){
//                navController.navigate(R.id.action_sahifamFragment_to_addAuthorFragment)
            }else{
                navController.navigate(R.id.action_sahifamFragment_to_registerFragment)
            }
        }
    }
}