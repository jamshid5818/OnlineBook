package jama.bookApp.onlinebook.data.repository.user.auth

import jama.bookApp.onlinebook.data.model.UserInfo
import jama.bookApp.onlinebook.data.utils.UiState


interface AuthRepository {
    fun registerUser(userInfo: UserInfo, emailFireKey:String, result: (UiState<String>) -> Unit)
    fun loginUser(email: String, password: String, result: (UiState<String>) -> Unit)
    fun forgotPassword(email: String, result: (UiState<String>) -> Unit)
    fun logout(result: () -> Unit)
}