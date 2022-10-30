package jama.bookApp.onlinebook.presentation.admin.addBook

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import jama.bookApp.onlinebook.R
import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.utils.*
import jama.bookApp.onlinebook.databinding.FragmentFinishingBinding
import jama.bookApp.onlinebook.presentation.user.BaseFragment
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class FinishingFragment : BaseFragment<FragmentFinishingBinding>(FragmentFinishingBinding::inflate) {
    private var imageUri:Uri?=null
    private var fileUri:Uri?=null
    private var nameBook:String?=null
    private var authorBook:String?=null
    private var isIslamic:Boolean?=null
    private var isAudio:Boolean?=null
    private var imageAuthorUri:String?=null
    private val viewModel:AddBookViewModel by viewModels()
    override fun onViewCreate() {
        nameBook = arguments?.getString("NAMEBOOK")
        authorBook = arguments?.getString("AUTHORBOOK")
        isIslamic = arguments?.getBoolean("ISISLAMIC")
        isAudio = arguments?.getBoolean("ISAUDIO")
        imageAuthorUri = arguments?.getString("IMAGEURIAUTHOR")

        observer()
        binding.imageBookAdmin.setOnClickListener {
            ImagePicker.with(this)
                .compress(1024)
                .galleryOnly()
                .createIntent {intent->
                    startForPostPictureResult.launch(intent)
                }
        }
        binding.fileBook.setOnClickListener {
            if (validation()){
                selectFiles()
            }
        }
    }

    private fun selectFiles() {
        val galleryIntent = Intent()
        galleryIntent.action = Intent.ACTION_GET_CONTENT
        // We will be redirected to choose pdf
        galleryIntent.type = "application/pdf"
        startActivityForResult(galleryIntent, 1)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK) {
            fileUri = data!!.data
            val randomKey = UUID.randomUUID()
            viewModel.addBook(
                requireContext(),
                PdfBooksModel(
                    nameBook,
                    imageUri.toString(),
                    fileUri.toString(),
                    binding.costBook.text.toString(),
                    authorBook,
                    binding.description.text.toString(),
                    randomKey.toString(),
                    0,
                    isIslamic!!,
                    isAudio!!
                ),
                fileUri!!,
                imageAuthorUri!!.toUri()
            )
        }
    }

    private fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenCreated {
                viewModel.addBook.observe(viewLifecycleOwner){ state->
                    when(state){
                        is UiState.Loading->{

                        }
                        is UiState.Failure->{
                            snackbar(state.message.toString(), binding.fullLayout)
                        }
                        is UiState.Success->{
                            snackbar("Succesfully", binding.fullLayout)
                            navController.navigate(R.id.homeFragment)
                        }
                    }
                }
            }
        }
    }

    private fun validation(): Boolean {
        var isValid = true
        if (imageUri ==null){
            isValid = false
            snackbar("Kitob uchun rasm tanlang", binding.fullLayout)
        }else if (binding.costBook.text.toString().isEmpty()){
            isValid = false
            snackbar("Kitob uchun narx kiriting", binding.fullLayout)
        }
        else if (binding.description.text.toString().isEmpty()){
            isValid = false
            snackbar("Kitob haqida ma'lumotlar yozing.", binding.fullLayout)
        }
        return isValid
    }

    private val startForPostPictureResult =registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result: ActivityResult ->
        when (result.resultCode) {
            Activity.RESULT_OK -> {
                imageUri = result.data?.data!!
                binding.imageBookAdmin.setImageURI(imageUri)
            }
            getStorageImage.error -> {
                toast("${result.data}")
            }
            else -> {
                Log.d("PostFragment", "Get Image Cancelled")
            }
        }
    }
}