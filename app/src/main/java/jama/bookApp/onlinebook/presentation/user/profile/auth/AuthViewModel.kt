package jama.bookApp.onlinebook.presentation.user.profile.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jama.bookApp.onlinebook.data.model.UserInfo
import jama.bookApp.onlinebook.data.repository.user.AuthRepository
import jama.bookApp.onlinebook.data.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val repository: AuthRepository
):ViewModel(){
    private val _register = MutableLiveData<UiState<String>>()
    val register: LiveData<UiState<String>>
        get() = _register
    private val _login = MutableLiveData<UiState<String>>()
    val login: LiveData<UiState<String>>
        get() = _login
    private val _forgotPassword = MutableLiveData<UiState<String>>()
    val forgotPassword: LiveData<UiState<String>>
        get() = _forgotPassword
    fun register(
        userInfo: UserInfo,
        emailFireKey:String
    ) {
        _register.value = UiState.Loading(true)
        viewModelScope.launch(Dispatchers.Main){
            repository.registerUser(
                userInfo = userInfo,
                emailFireKey
            ) {
                _register.value = it
            }
        }
    }
    fun login(
        email: String,
        password: String
    ) {
        _login.value = UiState.Loading(true)
        viewModelScope.launch(Dispatchers.Main){
            repository.loginUser(
                email,
                password
            ){
                _login.value = it
            }
        }
    }
    fun forgotPassword(email: String) {
        _forgotPassword.value = UiState.Loading(true)
        viewModelScope.launch(Dispatchers.Main){
            repository.forgotPassword(email){
                _forgotPassword.value = it
            }
        }
    }
    fun logout(result: () -> Unit){
        viewModelScope.launch(Dispatchers.Main){
            repository.logout(result)
        }
    }
    fun firebasePathgmail(email:String):String{
        var email2 =""
        email.forEachIndexed { index, letter ->
            if (letter.isLetter() || letter.isDigit()) {
                email2+=email[index]
            }
        }
        return email2
    }

}