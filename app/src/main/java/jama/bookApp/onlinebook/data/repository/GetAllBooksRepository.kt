package jama.bookApp.onlinebook.data.repository

import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.utils.UiState

interface GetAllBooksRepository {
    fun getAllBooks(result: (UiState<ArrayList<PdfBooksModel>>) -> Unit)
}