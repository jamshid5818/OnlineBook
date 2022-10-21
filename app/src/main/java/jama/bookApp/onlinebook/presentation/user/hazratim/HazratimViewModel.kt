package jama.bookApp.onlinebook.presentation.user.hazratim

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
class HazratimViewModel @Inject constructor(
    val repository: GetAllBooksRepository
):ViewModel(){
    private val _getMuslimBooks = MutableLiveData<UiState<ArrayList<PdfBooksModel>>>()
    val getMuslimBooks: LiveData<UiState<ArrayList<PdfBooksModel>>>
        get() = _getMuslimBooks

    fun getMuslimBooks(){
        val audioBooksList = ArrayList<PdfBooksModel>()
        _getMuslimBooks.value = UiState.Loading(true)
        viewModelScope.launch {
            repository.getAllBooks {
                it.data?.forEach {pdf->
                    if (pdf.isMuslimBook == true){
                        audioBooksList.add(pdf)
                    }
                }
                _getMuslimBooks.value = UiState.Success(audioBooksList)
            }
        }
    }
}