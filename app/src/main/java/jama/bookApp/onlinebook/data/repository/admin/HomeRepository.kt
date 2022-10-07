package jama.bookApp.onlinebook.data.repository.admin

import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.utils.UiState

interface HomeRepository {
    fun getAllBooks(result: (UiState<ArrayList<PdfBooksModel>>) -> Unit)
}