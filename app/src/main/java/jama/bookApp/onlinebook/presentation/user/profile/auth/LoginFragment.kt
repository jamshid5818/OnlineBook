package jama.bookApp.onlinebook.presentation.user.profile.auth

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import jama.bookApp.onlinebook.R
import jama.bookApp.onlinebook.data.utils.*
import jama.bookApp.onlinebook.databinding.FragmentLoginBinding
import jama.bookApp.onlinebook.presentation.admin.AdminActivity
import jama.bookApp.onlinebook.presentation.user.BaseFragment

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    val viewModel: AuthViewModel by viewModels()
    private val shared by lazy {
        SharedPref(requireContext())
    }
    override fun onViewCreate() {
        observer()
        binding.loginBtn.setOnClickListener {
            if (binding.emailEt.text.toString() == "bekodilov.99@mail.ru" && binding.passEt.text.toString()=="bek01091999"){
                shared.setEmail("bekodilov.99@mail.ru")
                startActivity(Intent(requireContext(), AdminActivity::class.java))
            }else{
                if (validation()) {
                    viewModel.login(
                        email = binding.emailEt.text.toString(),
                        password = binding.passEt.text.toString()
                    )
                }
            }
        }
        binding.forgotPassLabel.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        binding.registerLabel.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
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
        if (binding.passEt.text.isNullOrEmpty()){
            isValid = false
            toast(getString(R.string.enter_password))
        }else{
            if (binding.passEt.text.toString().length < 8){
                isValid = false
                toast(getString(R.string.invalid_password))
            }
        }
        return isValid
    }

    private fun observer() {
        viewModel.login.observe(viewLifecycleOwner) { state ->
            when(state){
                is UiState.Loading -> {
                    binding.loginBtn.text = ""
                    binding.loginProgress.show()
                }
                is UiState.Failure -> {
                    binding.loginBtn.text = "Login"
                    binding.loginProgress.gone()
                    toast(state.message)
                }
                is UiState.Success -> {
                    binding.loginBtn.text = "Login"
                    binding.loginProgress.gone()
                    if (shared.getEmail().toString()=="bekodilov.99@mail.ru"){
                        startActivity(Intent(requireActivity(), AdminActivity::class.java))
                    }else{
                        shared.setEmail(binding.emailEt.text.toString())
                        findNavController().navigate(R.id.action_loginFragment_to_sahifamFragment)
                    }
                    toast(state.data)
                }
            }
        }
    }
}