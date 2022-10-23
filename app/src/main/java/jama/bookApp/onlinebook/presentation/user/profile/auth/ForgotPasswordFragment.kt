package jama.bookApp.onlinebook.presentation.user.profile.auth

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import jama.bookApp.onlinebook.R
import jama.bookApp.onlinebook.data.utils.*
import jama.bookApp.onlinebook.databinding.FragmentForgotPasswordBinding
import jama.bookApp.onlinebook.presentation.user.BaseFragment

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>(FragmentForgotPasswordBinding::inflate) {
    val viewModel: AuthViewModel by viewModels()
    override fun onViewCreate() {
        observer()
        binding.forgotPassBtn.setOnClickListener {
            if (validation()){
                viewModel.forgotPassword(binding.emailEt.text.toString())
            }
        }
    }

    private fun observer() {
        viewModel.forgotPassword.observe(viewLifecycleOwner) { state ->
            when(state){
                is UiState.Loading -> {
                    binding.forgotPassBtn.text = ""
                    binding.forgotPassProgress.show()
                }
                is UiState.Failure -> {
                    binding.forgotPassBtn.text = "Send"
                    binding.forgotPassProgress.gone()
                    toast(state.message)
                }
                is UiState.Success -> {
                    binding.forgotPassBtn.text = "Send"
                    binding.forgotPassProgress.gone()
                    toast(state.data)
                }
            }
        }
    }

    private fun validation(): Boolean {
        var isValid = true

        if (binding.emailEt.text.isNullOrEmpty()){
            isValid = false
            toast(getString(R.string.enter_email))
        }else{
            if (!binding.emailEt.text.toString().isValidEmail()){
                isValid = false
                toast(getString(R.string.invalid_email))
            }
        }
        return isValid
    }
}