package jama.bookApp.onlinebook.presentation.user.hazratim.hazratFr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.repository.user.hazrat.HazratimRepository
import jama.bookApp.onlinebook.data.utils.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HazratimViewModel @Inject constructor(
    val repository: HazratimRepository
):ViewModel(){
    private val _getMuslimBooks = MutableLiveData<UiState<ArrayList<PdfBooksModel>>>()
    val getMuslimBooks: LiveData<UiState<ArrayList<PdfBooksModel>>>
        get() = _getMuslimBooks

    fun getMuslimBooks(){
        val muslimBooksList = ArrayList<PdfBooksModel>()
        _getMuslimBooks.value = UiState.Loading(true)
        viewModelScope.launch {
            repository.getHazratBooks {
                it.data?.let { it1 -> muslimBooksList.addAll(it1) }
                _getMuslimBooks.value = UiState.Success(muslimBooksList)
            }
        }
    }
    private val _getMuslimAudioBooks = MutableLiveData<UiState<ArrayList<PdfBooksModel>>>()
    val getMuslimAudioBooks: LiveData<UiState<ArrayList<PdfBooksModel>>>
        get() = _getMuslimAudioBooks

    fun getMuslimAudioBooks(){
        val muslimAudioBooksList = ArrayList<PdfBooksModel>()
        _getMuslimAudioBooks.value = UiState.Loading(true)
        viewModelScope.launch {
            repository.getHazratAudioBooks {
                it.data?.let { it1 -> muslimAudioBooksList.addAll(it1) }
                _getMuslimAudioBooks.value = UiState.Success(muslimAudioBooksList)
            }
        }
    }
}