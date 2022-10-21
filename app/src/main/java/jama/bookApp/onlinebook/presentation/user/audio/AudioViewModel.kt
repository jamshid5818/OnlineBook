package jama.bookApp.onlinebook.presentation.user.audio

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
class AudioViewModel @Inject constructor(
    val repository: GetAllBooksRepository
): ViewModel(){
    private val _getAudioBooks = MutableLiveData<UiState<ArrayList<PdfBooksModel>>>()
    val getAudioBooks: LiveData<UiState<ArrayList<PdfBooksModel>>>
        get() = _getAudioBooks

    fun getAudioBooks(){
        val audioBooksList = ArrayList<PdfBooksModel>()
        _getAudioBooks.value = UiState.Loading(true)
        viewModelScope.launch {
            repository.getAllBooks {
                it.data?.forEach {pdf->
                    if (pdf.isAudioBook == true){
                        audioBooksList.add(pdf)
                    }
                }
                _getAudioBooks.value = UiState.Success(audioBooksList)
            }
        }
    }
}