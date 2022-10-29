package jama.bookApp.onlinebook.presentation.user.kitob

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.repository.user.books.BookRepository
import jama.bookApp.onlinebook.data.utils.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    val repository: BookRepository
):ViewModel(){
    private val _getFreeBooks = MutableLiveData<UiState<ArrayList<PdfBooksModel>>>()
    val getFreeBooks: LiveData<UiState<ArrayList<PdfBooksModel>>>
        get() = _getFreeBooks
    fun getMuslimBooks(){
        val muslimBooksList = ArrayList<PdfBooksModel>()
        _getFreeBooks.value = UiState.Loading(true)
        viewModelScope.launch {
            repository.getFreeBooks {
                it.data?.let { it1 -> muslimBooksList.addAll(it1) }
                _getFreeBooks.value = UiState.Success(muslimBooksList)
            }
        }
    }
}