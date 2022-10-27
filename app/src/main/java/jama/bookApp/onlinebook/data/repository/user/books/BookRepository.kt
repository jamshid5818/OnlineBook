package jama.bookApp.onlinebook.data.repository.user.books

import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.utils.UiState
import jama.bookApp.onlinebook.presentation.user.profile.authors.adapter.AuthorsData

interface BookRepository {
    fun getFreeBooks(result: (UiState<List<PdfBooksModel>>) -> Unit)
}