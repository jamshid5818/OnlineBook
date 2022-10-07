package jama.bookApp.onlinebook.presentation.admin.addBook

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import jama.bookApp.onlinebook.R
import jama.bookApp.onlinebook.data.utils.snackbar
import jama.bookApp.onlinebook.databinding.FragmentGetNameAndAuthorBinding
import jama.bookApp.onlinebook.presentation.user.BaseFragment

@AndroidEntryPoint
class GetNameAndAuthorFragment : BaseFragment<FragmentGetNameAndAuthorBinding>(FragmentGetNameAndAuthorBinding::inflate) {
    var nameBook:String?=null
    var authorBook:String?=null
    var isIslamic:Boolean?=null
    var isAudio:Boolean?=null
    override fun onViewCreate() {
        binding.nextBtn.setOnClickListener {
            isIslamic = arguments?.getBoolean("ISISLAMIC")
            isAudio = arguments?.getBoolean("ISAUDIO")
            if (validation()){
                val bundle1 = Bundle()
                bundle1.putString("NAMEBOOK", nameBook)
                bundle1.putString("AUTHORBOOK", authorBook)
                isIslamic?.let { it1 -> bundle1.putBoolean("ISISLAMIC", it1) }
                isAudio?.let { it1 -> bundle1.putBoolean("ISAUDIO", it1) }
                navController.navigate(R.id.action_getNameAndAuthorFragment_to_finishingFragment, bundle1)
            }
        }
    }

    private fun validation(): Boolean {
        var isValid = true
        if (binding.nameBook.text.isNotEmpty() && binding.nameAuthor.text.isNotEmpty()){
            nameBook = binding.nameBook.text.toString()
            authorBook = binding.nameAuthor.text.toString()
        }else if (binding.nameBook.text.isEmpty()){
            snackbar("Kitobning nomi bo'sh bo'lishi mumkin emas", binding.fullLayout)
            isValid = false
        }else if (binding.nameAuthor.text.isEmpty()){
            snackbar("Muallifning nomi bo'sh bo'lishi mumkin emas", binding.fullLayout)
            isValid = false
        }
        return isValid
    }
}