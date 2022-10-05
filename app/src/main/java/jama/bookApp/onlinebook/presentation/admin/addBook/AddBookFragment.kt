package jama.bookApp.onlinebook.presentation.admin.addBook

import android.app.Activity
import android.net.Uri
import android.util.Log
import android.widget.ProgressBar
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import jama.bookApp.onlinebook.R
import jama.bookApp.onlinebook.data.utils.*
import jama.bookApp.onlinebook.databinding.FragmentAddBookBinding
import jama.bookApp.onlinebook.presentation.user.BaseFragment
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddBookFragment :BaseFragment<FragmentAddBookBinding>(FragmentAddBookBinding::inflate){
    private val viewmodel:AddBookViewModel by viewModels()
    lateinit var progress :ProgressBar
    private var imageUri:Uri?=null
    override fun onViewCreate() {
        observer()
        binding.imageBook.setOnClickListener {
            ImagePicker.with(this)
                .compress(1024)
                .galleryOnly()
                .createIntent {intent->
                    startForPostPictureResult.launch(intent)
                }
        }
        binding.send.setOnClickListener {
            if (validation())
        }
        progress = requireActivity().findViewById(R.id.progress_admin)
    }

    private val startForPostPictureResult =registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result: ActivityResult ->
        when (result.resultCode) {
            Activity.RESULT_OK -> {
                imageUri = result.data!!.data
                binding.imageBook.setImageURI(imageUri)
            }
            getStorageImage.error -> {
                toast("${result.data}")
            }
            else -> {
                Log.d("PostFragment", "Get Image Cancelled")
            }
        }
    }
    private fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenCreated {
                viewmodel.addBook.observe(viewLifecycleOwner){state->
                    when(state){
                        is UiState.Loading->{
                            progress.enabled()
                        }
                        is UiState.Failure->{
                            progress.disabled()
                            snackbar(state.message.toString(), binding.fullLayout)
                        }
                        is UiState.Success->{
                            progress.disabled()
                            snackbar("Succesfully", binding.fullLayout)
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}