package jama.bookApp.onlinebook.presentation.user.kitob

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jama.bookApp.onlinebook.data.repository.admin.AddBookRepository
import javax.inject.Inject

@HiltViewModel
class KitobViewModel @Inject constructor(
    val addBookRepository: AddBookRepository
):ViewModel(){

}