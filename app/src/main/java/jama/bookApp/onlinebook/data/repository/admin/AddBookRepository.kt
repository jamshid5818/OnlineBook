package jama.bookApp.onlinebook.data.repository.admin

import android.content.Context
import android.net.Uri
import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.utils.UiState

interface AddBookRepository {
    fun addBook(context: Context,fileUri: Uri, pdfBooksModel: PdfBooksModel, imageAuthorUri:Uri, result: (UiState<String>) -> Unit)

}