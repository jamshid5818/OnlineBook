package jama.bookApp.onlinebook.presentation.user.audio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.repository.GetAllBooksRepository
import jama.bookApp.onlinebook.data.repository.user.audio.AudioRepository
import jama.bookApp.onlinebook.data.utils.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AudioViewModel @Inject constructor(
    val repository: AudioRepository
): ViewModel(){
    private val _getAudioBooks = MutableLiveData<UiState<ArrayList<PdfBooksModel>>>()
    val getAudioBooks: LiveData<UiState<ArrayList<PdfBooksModel>>>
        get() = _getAudioBooks

    fun getAudioBooks(){
        val audioBooksList = ArrayList<PdfBooksModel>()
        _getAudioBooks.value = UiState.Loading(true)
        viewModelScope.launch {
            repository.getAudioBooks {
                it.data?.let { it1 -> audioBooksList.addAll(it1) }
                _getAudioBooks.value = UiState.Success(audioBooksList)
            }
        }
    }
}