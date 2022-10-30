package jama.bookApp.onlinebook.presentation.user.profile.auth

import android.graphics.Color
import android.util.Log
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import jama.bookApp.onlinebook.R
import jama.bookApp.onlinebook.data.model.UserInfo
import jama.bookApp.onlinebook.data.utils.*
import jama.bookApp.onlinebook.databinding.FragmentRegisterBinding
import jama.bookApp.onlinebook.presentation.user.BaseFragment

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    val viewModel: AuthViewModel by viewModels()
    var gender: String? = null
    private val shared by lazy {
        SharedPref(requireContext())
    }
    override fun onViewCreate() {
        observer()
        val adapter = ArrayAdapter.createFromResource(requireContext(),
            R.array.age,android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerAge.adapter = adapter

        binding.registerBtn.setOnClickListener {
            if (validation()){
                viewModel.register(
                    UserInfo(
                    email = binding.editEmailText.text.toString(),
                    name = binding.editNameText.text.toString(),
                    gender = gender!!,
                    password = binding.editpasswordText.text.toString(),
                    age = binding.spinnerAge.selectedItem.toString().toInt(),
                    balance = 0
                ),
                    viewModel.firebasePathgmail(binding.editEmailText.text.toString()))
            }
        }
        binding.female.setOnClickListener {
            gender = "Female"
            binding.female.setBackgroundColor(R.drawable.login_txt_back)
            binding.male.setBackgroundColor(Color.WHITE)
        }
        binding.loginLabel.setOnClickListener {
            navController.navigate(R.id.action_registerFragment_to_loginFragment)
        }
        binding.male.setOnClickListener {
            gender = "Male"
            binding.male.setBackgroundColor(R.drawable.login_txt_back)
            binding.female.setBackgroundColor(Color.WHITE)
        }
    }

    private fun validation(): Boolean {
        var isValid = true

        if (binding.editNameText.text.isNullOrEmpty()){
            isValid = false
            toast(getString(R.string.enter_first_name))
        }

        if (binding.spinnerAge.selectedItem.toString().isEmpty()){
            isValid = false
            toast(getString(R.string.enter_age))
        }

        if (gender==null){
            isValid = false
            toast(getString(R.string.enter_gender))
        }

        if (binding.editEmailText.text.isNullOrEmpty()){
            isValid = false
            toast(getString(R.string.enter_email))
        }else{
            if (!binding.editEmailText.text.toString().isValidEmail()){
                isValid = false
                toast(getString(R.string.invalid_email))
            }
        }
        if (binding.editpasswordText.text.isNullOrEmpty()){
            isValid = false
            toast(getString(R.string.enter_password))
        }else{
            if (binding.editpasswordText.text.toString().length <= 8){
                isValid = false
                toast(getString(R.string.invalid_password))
            }
        }

        return isValid
    }

    private fun observer() {
        viewModel.register.observe(viewLifecycleOwner) { state ->
            when(state){
                is UiState.Loading -> {
                    binding.registerBtn.text = ""
                    binding.registerProgress.show()
                }
                is UiState.Failure -> {
                    binding.registerBtn.text = "Register"
                    binding.registerProgress.gone()
                    Log.d("ERRRRRORRRRR", "observer: ${state.message}")
                    snackbar(state.message!!, binding.root)
                }
                is UiState.Success -> {
                    binding.registerBtn.text = "Register"
                    binding.registerProgress.gone()
                    snackbar("Registration Succes", binding.root)
                    shared.setEmail(binding.editEmailText.text.toString())
                    shared.setName(binding.editNameText.text.toString())
                    findNavController().navigate(R.id.action_registerFragment_to_sahifamFragment)
                }
            }
        }
    }
}