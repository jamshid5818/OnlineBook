package jama.bookApp.onlinebook.data.repository.admin

import android.net.Uri
import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.utils.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddBookRepositoryImp(
    var storageReference: FirebaseStorage,
    var databaseReference: FirebaseDatabase
) : AddBookRepository {
    override fun addBook(fileUri:Uri,pdfBooksModel: PdfBooksModel, result: (UiState<String>) -> Unit) {
        val reference = storageReference.getReference("pdfBooks").child("${pdfBooksModel.randomKey}.pdf")
        CoroutineScope(Dispatchers.IO).launch {
            reference.putFile(fileUri)
                .addOnSuccessListener {
                    reference.downloadUrl
                        .addOnSuccessListener {
                            databaseReference.getReference("AllBooks").child("${pdfBooksModel.randomKey}.pdf").setValue(pdfBooksModel)
                                .addOnSuccessListener {
                                    result.invoke(UiState.Success("Succesfully"))
                                }
                                .addOnFailureListener {
                                    Log.d("EEEEEEE", it.message.toString())
                                    result.invoke(UiState.Failure("Failure"))
                                }
                        }
                        .addOnFailureListener {
                            Log.d("EEEEEEE",it.message.toString())
                            result.invoke(UiState.Failure("Failure"))
                        }
                }
                .addOnFailureListener{
                    Log.d("EEEEEEE", it.message.toString())
                    result.invoke(UiState.Failure("Failure"))
                }
        }
    }
}