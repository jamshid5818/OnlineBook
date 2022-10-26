package jama.bookApp.onlinebook.presentation.admin.author

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jama.bookApp.onlinebook.data.repository.user.authors.AuthorsRepositoryImp
import jama.bookApp.onlinebook.data.utils.UiState
import jama.bookApp.onlinebook.presentation.user.profile.authors.adapter.AuthorsData
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddAuthorViewModel @Inject constructor(private val authorRepository: AuthorsRepositoryImp): ViewModel() {
    private val _addAuthors = MutableLiveData<UiState<String>>()
    val addAuthors:LiveData<UiState<String>> = _addAuthors

    fun addAuthors(authorsData: AuthorsData){
        viewModelScope.launch {
            _addAuthors.value = UiState.Loading(true)
            viewModelScope.launch {
                authorRepository.addAuthor(authorsData = authorsData){t->
                    _addAuthors.value = t
                }
            }
        }
    }
}