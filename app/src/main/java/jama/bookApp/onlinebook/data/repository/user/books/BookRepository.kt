package jama.bookApp.onlinebook.data.repository.user.books

import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.utils.UiState

interface BookRepository {
    fun getFreeBooks(result: (UiState<List<PdfBooksModel>>) -> Unit)
}