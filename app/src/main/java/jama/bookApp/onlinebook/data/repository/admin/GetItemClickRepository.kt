package jama.bookApp.onlinebook.data.repository.admin

import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.utils.UiState

interface GetItemClickRepository {
    fun getAllBooks(randomKey:String,result: (UiState<PdfBooksModel>) -> Unit)
}