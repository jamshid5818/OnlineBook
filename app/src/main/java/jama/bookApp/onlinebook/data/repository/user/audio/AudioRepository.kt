package jama.bookApp.onlinebook.data.repository.user.audio

import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.utils.UiState

interface AudioRepository {
    fun getFreeBooks(result: (UiState<List<PdfBooksModel>>) -> Unit)
}