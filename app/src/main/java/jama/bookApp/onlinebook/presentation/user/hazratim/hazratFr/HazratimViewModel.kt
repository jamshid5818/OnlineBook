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
        _getMuslimBooks.value = UiState.Loading(true)
        viewModelScope.launch {
            repository.getHazratBooks {
                _getMuslimBooks.value = it
            }
        }
    }
    private val _getMuslimAudioBooks = MutableLiveData<UiState<ArrayList<PdfBooksModel>>>()
    val getMuslimAudioBooks: LiveData<UiState<ArrayList<PdfBooksModel>>>
        get() = _getMuslimAudioBooks

    fun getMuslimAudioBooks(){
        _getMuslimAudioBooks.value = UiState.Loading(true)
        viewModelScope.launch {
            repository.getHazratAudioBooks {
                _getMuslimAudioBooks.value = it
            }
        }
    }
}