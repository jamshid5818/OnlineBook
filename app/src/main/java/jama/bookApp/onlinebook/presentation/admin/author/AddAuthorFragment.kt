package jama.bookApp.onlinebook.presentation.admin.author

import android.app.Activity
import android.net.Uri
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import jama.bookApp.onlinebook.data.utils.getStorageImage
import jama.bookApp.onlinebook.data.utils.toast
import jama.bookApp.onlinebook.databinding.FragmentAddAuthorBinding
import jama.bookApp.onlinebook.presentation.user.BaseFragment
import jama.bookApp.onlinebook.presentation.user.profile.authors.adapter.AuthorsData

@AndroidEntryPoint
class AddAuthorFragment : BaseFragment<FragmentAddAuthorBinding>(FragmentAddAuthorBinding::inflate){
    private var imageUri: Uri?=null
    private val viewModel: AddAuthorViewModel by viewModels()

    override fun onViewCreate() {
        binding.imageAuthor.setOnClickListener {
            ImagePicker.with(this)
                .compress(1024)
                .galleryOnly()
                .createIntent {intent->
                    startForPostPictureResult.launch(intent)
                }
        }

        binding.add.setOnClickListener {
            viewModel.addAuthors(AuthorsData(name = binding.author.text.toString(), image = imageUri.toString(),""))
        }

        viewModel.addAuthors.observe(viewLifecycleOwner){
            toast(it.data.toString())
        }
    }

    private val startForPostPictureResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result: ActivityResult ->
        when (result.resultCode) {
            Activity.RESULT_OK -> {
                imageUri = result.data?.data!!
                binding.imageAuthor.setImageURI(imageUri)
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