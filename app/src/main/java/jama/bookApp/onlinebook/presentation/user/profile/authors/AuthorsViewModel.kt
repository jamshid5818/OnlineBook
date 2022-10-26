package jama.bookApp.onlinebook.presentation.user.profile.authors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jama.bookApp.onlinebook.data.repository.user.authors.AuthorsRepository
import jama.bookApp.onlinebook.data.utils.UiState
import jama.bookApp.onlinebook.presentation.user.profile.authors.adapter.AuthorsData
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorsViewModel @Inject constructor(private val authorsRepository: AuthorsRepository) : ViewModel() {
    private val _getAuthors = MutableLiveData<UiState<List<AuthorsData>>>()
    val getAuthors: LiveData<UiState<List<AuthorsData>>>
        get() = _getAuthors

    fun getAuthorsList(){
        val authorsList = ArrayList<AuthorsData>()
        _getAuthors.value = UiState.Loading(true)
        viewModelScope.launch {
            authorsRepository.getAuthors {
                authorsList.addAll(it.data ?: emptyList())
                _getAuthors.value = UiState.Success(authorsList)
            }
        }
    }
}