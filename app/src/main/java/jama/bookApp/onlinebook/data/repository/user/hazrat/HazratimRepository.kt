package jama.bookApp.onlinebook.data.repository.user.hazrat

import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.utils.UiState

interface HazratimRepository {
    fun getHazratBooks(result: (UiState<ArrayList<PdfBooksModel>>) -> Unit)
    fun getHazratAudioBooks(result: (UiState<ArrayList<PdfBooksModel>>) -> Unit)
}