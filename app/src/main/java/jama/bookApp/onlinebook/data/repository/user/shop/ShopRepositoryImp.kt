package jama.bookApp.onlinebook.data.repository.user.shop

import com.google.firebase.database.FirebaseDatabase
import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.utils.UiState
import javax.inject.Inject

class ShopRepositoryImp @Inject constructor(private val myRef:FirebaseDatabase) :ShopRepository {
    override fun getBooks(result: (UiState<ArrayList<PdfBooksModel>>) -> Unit) {
        
    }

    override fun getAudioBooks(result: (UiState<ArrayList<PdfBooksModel>>) -> Unit) {

    }

}