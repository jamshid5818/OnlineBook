package jama.bookApp.onlinebook.data.repository.user.shop

import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.utils.UiState

interface ShopRepository {
    fun getBooks(result: (UiState<ArrayList<PdfBooksModel>>) -> Unit)
    fun getAudioBooks(result: (UiState<ArrayList<PdfBooksModel>>) -> Unit)
}