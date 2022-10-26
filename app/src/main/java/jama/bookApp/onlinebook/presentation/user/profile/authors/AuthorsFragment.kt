package jama.bookApp.onlinebook.presentation.user.profile.authors

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import dagger.hilt.android.AndroidEntryPoint
import jama.bookApp.onlinebook.data.utils.UiState
import jama.bookApp.onlinebook.databinding.FragmentAuthorsBinding
import jama.bookApp.onlinebook.presentation.user.BaseFragment
import jama.bookApp.onlinebook.presentation.user.profile.authors.adapter.Adapter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthorsFragment : BaseFragment<FragmentAuthorsBinding>(FragmentAuthorsBinding::inflate) {
    private val viewModel: AuthorsViewModel by viewModels()

    private val adapter by lazy {
        Adapter()
    }

    override fun onViewCreate() {
        set()
        observe()
    }

    fun set() {
        binding.listAuthors.adapter = adapter
        viewModel.getAuthorsList()
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenCreated {
                viewModel.getAuthors.observe(viewLifecycleOwner) { state ->
                    when (state) {
                        is UiState.Loading -> {

                        }
                        is UiState.Failure -> {
//                            snackbar(state.message.toString(), binding.fullLayout)
                        }
                        is UiState.Success -> {
                            adapter.setList(state.data ?: emptyList())
                        }
                    }
                }
            }
        }
    }
}
