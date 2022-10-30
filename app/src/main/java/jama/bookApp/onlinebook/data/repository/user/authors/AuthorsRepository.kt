package jama.bookApp.onlinebook.data.repository.user.authors

import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.utils.UiState
import jama.bookApp.onlinebook.presentation.user.profile.authors.adapter.AuthorsData

interface AuthorsRepository {
    fun getAuthors(result: (UiState<List<PdfBooksModel>>) -> Unit)
}