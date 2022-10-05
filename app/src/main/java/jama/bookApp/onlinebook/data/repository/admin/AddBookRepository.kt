package jama.bookApp.onlinebook.data.repository.admin

import android.net.Uri
import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.utils.UiState

interface AddBookRepository {
    fun addBook(fileUri: Uri, pdfBooksModel: PdfBooksModel, result: (UiState<String>) -> Unit)

}