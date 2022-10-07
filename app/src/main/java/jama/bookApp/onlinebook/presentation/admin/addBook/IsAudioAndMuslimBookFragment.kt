package jama.bookApp.onlinebook.presentation.admin.addBook

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import jama.bookApp.onlinebook.R
import jama.bookApp.onlinebook.databinding.FragmentIsAudioAndMuslimBookBinding
import jama.bookApp.onlinebook.presentation.user.BaseFragment
@AndroidEntryPoint
class IsAudioAndMuslimBookFragment : BaseFragment<FragmentIsAudioAndMuslimBookBinding>(FragmentIsAudioAndMuslimBookBinding::inflate) {
    override fun onViewCreate() {
        var isIslamic = false
        var isAudio = false
        binding.isAudio.setOnClickListener {
            isAudio = binding.isAudio.isChecked
        }
        binding.isIslomic.setOnClickListener {
            isIslamic = binding.isIslomic.isChecked
        }
        binding.nextBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("ISAUDIO",isAudio)
            bundle.putBoolean("ISISLAMIC",isIslamic)
            navController.navigate(R.id.action_isAudioAndMuslimBookFragment_to_getNameAndAuthorFragment)
        }
    }
}