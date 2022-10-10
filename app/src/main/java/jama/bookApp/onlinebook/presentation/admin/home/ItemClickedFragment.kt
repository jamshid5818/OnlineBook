package jama.bookApp.onlinebook.presentation.admin.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import jama.bookApp.onlinebook.data.utils.UiState
import jama.bookApp.onlinebook.data.utils.snackbar
import jama.bookApp.onlinebook.databinding.FragmentItemClickedBinding
import jama.bookApp.onlinebook.presentation.user.BaseFragment
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ItemClickedFragment : BaseFragment<FragmentItemClickedBinding>(FragmentItemClickedBinding::inflate) {
    var randomKey:String?=null
    val viewModel:ItemClickedViewModel by viewModels()
    override fun onViewCreate() {
        observer()
        randomKey = arguments?.getString("RANDOMKEY")
        randomKey?.let { viewModel.getItemList(it) }
    }

    private fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenCreated {
                viewModel.getBook.observe(viewLifecycleOwner){state->
                    when(state){
                        is UiState.Loading->{

                        }
                        is UiState.Failure->{
                            snackbar(state.message.toString(), binding.fullLayout)
                        }
                        is UiState.Success->{
                            val data = state.data!!
                            Glide.with(requireContext())
                                .load(data.imageBookUri)
                                .into(binding.imageBook)
                            binding.authorName.text = data.authorBook
                            binding.nameBook.text = data.nameBook
                            binding.countDownloads.text = data.amountSold.toString()
                            binding.description.text = data.muqaddima
                        }
                    }
                }
            }
        }
    }
}