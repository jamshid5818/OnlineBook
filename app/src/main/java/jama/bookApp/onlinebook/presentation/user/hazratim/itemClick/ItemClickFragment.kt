package jama.bookApp.onlinebook.presentation.user.hazratim.itemClick

import dagger.hilt.android.AndroidEntryPoint
import jama.bookApp.onlinebook.databinding.FragmentItemClickBinding
import jama.bookApp.onlinebook.presentation.user.BaseFragment

@AndroidEntryPoint
class ItemClickFragment : BaseFragment<FragmentItemClickBinding>(FragmentItemClickBinding::inflate){
    override fun onViewCreate() {
        binding.description.text = arguments?.getString("HAZRATINFO")
    }
}