package jama.bookApp.onlinebook.presentation.user.hazratim.hazratFr

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jama.bookApp.onlinebook.R
import jama.bookApp.onlinebook.data.utils.UiState
import jama.bookApp.onlinebook.data.utils.snackbar
import jama.bookApp.onlinebook.databinding.FragmentHazratimBinding
import jama.bookApp.onlinebook.presentation.user.BaseFragment
import kotlinx.coroutines.launch
import kotlin.math.log

@AndroidEntryPoint
class HazratimFragment : BaseFragment<FragmentHazratimBinding>(FragmentHazratimBinding::inflate) {
    val viewModel: HazratimViewModel by viewModels()
    private val adapter1 by lazy {
        Adapter1(requireContext())
    }
    private val adapter2 by lazy {
        Adapter2(requireContext())
    }
    override fun onViewCreate() {
        observer()
        viewModel.getMuslimBooks()
        viewModel.getMuslimAudioBooks()
        binding.hazratimImage.setOnClickListener {
            navController.navigate(R.id.action_hazratimFragment_to_clickedHazratFragment)
        }
        binding.apply {
            list1.layoutManager = LinearLayoutManager(requireContext())
            list2.layoutManager = LinearLayoutManager(requireContext())
            list1.adapter = adapter1
            list2.adapter = adapter2
        }
    }

    private fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenCreated {
                viewModel.getMuslimBooks.observe(viewLifecycleOwner){state->
                    when(state){
                        is UiState.Loading->{
                            Log.d("LOADING", "LOADED")
                        }
                        is UiState.Failure->{
//                        binding.list1.gone()
//                        binding.list2.gone()
//                        binding.noBook.show()
//                        binding.noAudio.show()
                            Log.d("LOADING", "FAILED")
                        }
                        is UiState.Success->{
                            Log.d("LOADING", "SUCCESFULLY")
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

                        }
                    }
                }
            }
        }
    }
}