package jama.bookApp.onlinebook.presentation.user.dokon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.repository.user.shop.ShopRepository
import jama.bookApp.onlinebook.data.utils.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DokonViewModel @Inject constructor(
    val repository: ShopRepository
):ViewModel(){
    private val _getAudioBooks = MutableLiveData<UiState<ArrayList<PdfBooksModel>>>()
    val getAudioBooks: LiveData<UiState<ArrayList<PdfBooksModel>>>
        get() = _getAudioBooks

    private val _getBooks = MutableLiveData<UiState<ArrayList<PdfBooksModel>>>()
    val getBooks: LiveData<UiState<ArrayList<PdfBooksModel>>>
        get() = _getBooks

    fun getAudioBooks(){
        val audioBooksList = ArrayList<PdfBooksModel>()
        _getAudioBooks.value = UiState.Loading(true)
        viewModelScope.launch {
            repository.getAudioBooks {
                if (it.data==null){
                    _getAudioBooks.value = UiState.Failure("Tez orada")
                }else{
                    audioBooksList.addAll(it.data)
                }
            }
        }
    }
    fun getBooks(){
        val booksList = ArrayList<PdfBooksModel>()
        _getBooks.value = UiState.Loading(true)
        viewModelScope.launch {
            repository.getAudioBooks {
                if (it.data==null){
                    _getBooks.value = UiState.Failure("Tez orada")
                }else{
                    booksList.addAll(it.data)
                }
            }
        }
    }
}