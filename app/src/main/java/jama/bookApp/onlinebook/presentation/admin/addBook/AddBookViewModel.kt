package jama.bookApp.onlinebook.presentation.admin.addBook

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.repository.admin.AddBookRepository
import jama.bookApp.onlinebook.data.utils.UiState
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    val repository: AddBookRepository
):ViewModel(){
    private val _addBook = MutableLiveData<UiState<String>>()
    val addBook :LiveData<UiState<String>>
        get() =_addBook
    fun addBook(
        pdfBooksModel: PdfBooksModel,
        fileUri:Uri
    ){
        _addBook.value = UiState.Loading(true)
        viewModelScope.launch(Dispatchers.Main){
            repository.addBook(
                fileUri = fileUri,
                pdfBooksModel = pdfBooksModel
            ){
                _addBook.value = it
            }
        }
    }
}