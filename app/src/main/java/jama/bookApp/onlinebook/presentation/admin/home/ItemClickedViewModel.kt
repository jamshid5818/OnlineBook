package jama.bookApp.onlinebook.presentation.admin.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.repository.admin.GetItemClickRepository
import jama.bookApp.onlinebook.data.utils.UiState
import javax.inject.Inject

@HiltViewModel
class ItemClickedViewModel @Inject constructor(
    private val repository: GetItemClickRepository
):ViewModel(){
    private val _getBook = MutableLiveData<UiState<PdfBooksModel>>()
    val getBook:LiveData<UiState<PdfBooksModel>>
        get() = _getBook
    fun getItemList(randomKey:String){
        _getBook.value = UiState.Loading(true)
        repository.getAllBooks(randomKey){
            _getBook.value = it
        }
    }
}