package jama.bookApp.onlinebook.presentation.user.kitob

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jama.bookApp.onlinebook.data.repository.admin.AddBookRepository
import jama.bookApp.onlinebook.data.repository.user.books.BookRepository
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    val repository: BookRepository
):ViewModel(){

}