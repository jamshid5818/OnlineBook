package jama.bookApp.onlinebook.presentation.admin.addBook

import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import jama.bookApp.onlinebook.R
import jama.bookApp.onlinebook.data.utils.getStorageImage
import jama.bookApp.onlinebook.data.utils.snackbar
import jama.bookApp.onlinebook.data.utils.toast
import jama.bookApp.onlinebook.databinding.FragmentGetNameAndAuthorBinding
import jama.bookApp.onlinebook.presentation.user.BaseFragment

@AndroidEntryPoint
class GetNameAndAuthorFragment : BaseFragment<FragmentGetNameAndAuthorBinding>(FragmentGetNameAndAuthorBinding::inflate) {
    var nameBook:String?=null
    var authorBook:String?=null
    var isIslamic:Boolean?=null
    var isAudio:Boolean?=null
    private var imageUri: Uri? = null
    override fun onViewCreate() {
        binding.imageAuthorUri.setOnClickListener {
            ImagePicker.with(this)
                .compress(1024)
                .galleryOnly()
                .createIntent {intent->
                    startForPostPictureResult.launch(intent)
                }
        }
        binding.nextBtn.setOnClickListener {
            isIslamic = arguments?.getBoolean("ISISLAMIC")
            isAudio = arguments?.getBoolean("ISAUDIO")
            if (validation()){
                val bundle1 = Bundle()
                bundle1.putString("NAMEBOOK", nameBook)
                bundle1.putString("AUTHORBOOK", authorBook)
                bundle1.putString("IMAGEURIAUTHOR", imageUri.toString())
                isIslamic?.let { it1 -> bundle1.putBoolean("ISISLAMIC", it1) }
                isAudio?.let { it1 -> bundle1.putBoolean("ISAUDIO", it1) }
                navController.navigate(R.id.action_getNameAndAuthorFragment_to_finishingFragment, bundle1)
            }
        }
    }
    private val startForPostPictureResult =registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result: ActivityResult ->
        when (result.resultCode) {
            Activity.RESULT_OK -> {
                imageUri = result.data!!.data
                binding.imageAuthorUri.setImageURI(imageUri)
            }
            getStorageImage.error -> {
                toast("${result.data}")
            }
            else -> {
                Log.d("GetNameAndAuthorFr", "Get Image Cancelled")
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
        }else if (imageUri==null){
            isValid = false
        }
        return isValid
    }
}