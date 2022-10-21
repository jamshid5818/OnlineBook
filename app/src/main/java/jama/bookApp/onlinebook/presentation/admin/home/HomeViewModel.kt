package jama.bookApp.onlinebook.presentation.admin.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.repository.GetAllBooksRepository
import jama.bookApp.onlinebook.data.utils.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllBooksRepository: GetAllBooksRepository
):ViewModel(){
    private val _getAllBooks = MutableLiveData<UiState<ArrayList<PdfBooksModel>>>()
    val getAllBooks:LiveData<UiState<ArrayList<PdfBooksModel>>>
        get() = _getAllBooks

    fun getAllBooks(){
        _getAllBooks.value = UiState.Loading(true)
        viewModelScope.launch {
            getAllBooksRepository.getAllBooks {
                _getAllBooks.value = it
            }
        }
    }
}