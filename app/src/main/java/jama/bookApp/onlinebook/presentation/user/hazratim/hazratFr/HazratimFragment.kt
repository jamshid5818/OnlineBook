package jama.bookApp.onlinebook.presentation.user.hazratim.hazratFr

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import dagger.hilt.android.AndroidEntryPoint
import jama.bookApp.onlinebook.R
import jama.bookApp.onlinebook.data.utils.UiState
import jama.bookApp.onlinebook.databinding.FragmentHazratimBinding
import jama.bookApp.onlinebook.presentation.user.BaseFragment
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HazratimFragment : BaseFragment<FragmentHazratimBinding>(FragmentHazratimBinding::inflate) {
    val viewModel: HazratimViewModel by viewModels()
    override fun onViewCreate() {
        observer()
        viewModel.getMuslimBooks()
        viewModel.getMuslimAudioBooks()
        binding.hazratimImage.setOnClickListener {
            navController.navigate(R.id.action_hazratimFragment_to_clickedHazratFragment)
        }

    }

    private fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenCreated {
                viewModel.getMuslimBooks.observe(viewLifecycleOwner){state->
                    when(state){
                        is UiState.Loading->{

                        }
                        is UiState.Failure->{
//                            snackbar(state.message.toString(), binding.fullLayout)
                        }
                        is UiState.Success->{
//                            adapter =
//                                HomeAdapter(
//                                    state.data,
//                                    requireContext())
//                            binding.list.adapter = adapter
                        }
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenCreated {
                viewModel.getMuslimAudioBooks.observe(viewLifecycleOwner){state->
                    when(state){
                        is UiState.Loading->{

                        }
                        is UiState.Failure->{
//                            snackbar(state.message.toString(), binding.fullLayout)
                        }
                        is UiState.Success->{
//                            adapter =
//                                HomeAdapter(
//                                    state.data,
//                                    requireContext())
//                            binding.list.adapter = adapter
                        }
                    }
                }
            }
        }
    }
}