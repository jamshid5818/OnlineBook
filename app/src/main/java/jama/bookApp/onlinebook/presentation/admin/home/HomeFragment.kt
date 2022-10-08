package jama.bookApp.onlinebook.presentation.admin.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import dagger.hilt.android.AndroidEntryPoint
import jama.bookApp.onlinebook.R
import jama.bookApp.onlinebook.data.utils.UiState
import jama.bookApp.onlinebook.data.utils.snackbar
import jama.bookApp.onlinebook.databinding.FragmentHomeBinding
import jama.bookApp.onlinebook.presentation.user.BaseFragment
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    val viewModel:HomeViewModel by viewModels()
    lateinit var adapter: HomeAdapter
    var itemKey :String?=null
    override fun onViewCreate() {
        observer()
        viewModel.getAllBooks()
        binding.list.setOnItemClickListener { adapterView, view, i, l ->
            val bundle = Bundle()
            itemKey = adapter.bookList[i].randomKey
            bundle.putString("RANDOMKEY", itemKey)
            navController.navigate(R.id.action_homeFragment_to_itemClickedFragment, bundle)
        }
    }

    private fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenCreated {
                viewModel.getAllBooks.observe(viewLifecycleOwner){state->
                    when(state){
                        is UiState.Loading->{

                        }
                        is UiState.Failure->{

                            snackbar(state.message.toString(), binding.fullLayout)
                        }
                        is UiState.Success->{
                            adapter =
                                HomeAdapter(
                                    state.data,
                                    requireContext())
                            binding.list.adapter = adapter
                        }
                    }
                }
            }
        }
    }
}