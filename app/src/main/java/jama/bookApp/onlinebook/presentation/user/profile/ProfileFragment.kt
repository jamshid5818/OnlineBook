package jama.bookApp.onlinebook.presentation.user.profile

import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import jama.bookApp.onlinebook.presentation.admin.AdminActivity
import jama.bookApp.onlinebook.databinding.FragmentSahifamBinding
import jama.bookApp.onlinebook.presentation.user.BaseFragment

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentSahifamBinding>(FragmentSahifamBinding::inflate) {
    override fun onViewCreate() {
        binding.txt.setOnClickListener {
            startActivity(Intent(requireContext(), AdminActivity::class.java))
        }
    }
}