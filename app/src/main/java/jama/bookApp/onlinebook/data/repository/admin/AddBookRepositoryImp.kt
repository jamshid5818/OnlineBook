package jama.bookApp.onlinebook.data.repository.admin

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.utils.UiState
import jama.bookApp.onlinebook.data.utils.getFirebaseRealData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddBookRepositoryImp @Inject constructor(
    var storageReference: FirebaseStorage,
    var databaseReference: FirebaseDatabase
) : AddBookRepository {
    override fun addBook(context: Context,fileUri:Uri,pdfBooksModel: PdfBooksModel, imageAuthorUri:Uri, result: (UiState<String>) -> Unit) {
        val referenceBookPdf = storageReference.getReference("pdfBooks").child("${pdfBooksModel.randomKey}.pdf")
        val referenceBookImage = storageReference.getReference("BookImages").child("${pdfBooksModel.randomKey}.jpg")
        val referenceAuthorImage = storageReference.getReference("BookImages").child("${pdfBooksModel.randomKey}.jpg")
        val progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Uploading Pdf file......")
        progressDialog.setCancelable(false)
        progressDialog.show()
        CoroutineScope(Dispatchers.IO).launch {
            referenceBookPdf.putFile(fileUri)
                .addOnSuccessListener {
                    referenceBookPdf.downloadUrl
                        .addOnSuccessListener {uriPdf->
                            pdfBooksModel.imageBookUri?.let { it1 ->
                                referenceBookImage.putFile(it1.toUri())
                                    .addOnSuccessListener {
                                        referenceBookImage.downloadUrl
                                            .addOnSuccessListener {uriImage->
                                               referenceAuthorImage.putFile(imageAuthorUri)
                                                   .addOnSuccessListener {
                                                       referenceAuthorImage.downloadUrl
                                                           .addOnSuccessListener { authorImageUri->
                                                               databaseReference.getReference(getFirebaseRealData.getBooks).child("${pdfBooksModel.randomKey}--pdf").setValue(
                                                                   pdfBooksModel.randomKey?.let {randomKey->
                                                                       pdfBooksModel.isMuslimBook?.let { isMuslim ->
                                                                           pdfBooksModel.isAudioBook?.let { isAudio ->
                                                                               PdfBooksModel(
                                                                                   pdfBooksModel.nameBook,
                                                                                   uriImage.toString(),
                                                                                   uriPdf.toString(),
                                                                                   authorImageUri.toString(),
                                                                                   pdfBooksModel.costBook,
                                                                                   pdfBooksModel.authorBook,
                                                                                   pdfBooksModel.muqaddima,
                                                                                   randomKey,
                                                                                   pdfBooksModel.amountSold,
                                                                                   isMuslim,
                                                                                   isAudio
                                                                               )
                                                                           }
                                                                       }
                                                                   })
                                                                   .addOnSuccessListener {
                                                                       progressDialog.dismiss()
                                                                       result.invoke(UiState.Success("Succesfully"))
                                                                   }
                                                                   .addOnFailureListener {
                                                                       progressDialog.dismiss()
                                                                       Log.d("EEEEEEE", it.message.toString())
                                                                       result.invoke(UiState.Failure(it.message))
                                                                   }
                                                           }
                                                           .addOnFailureListener {
                                                               result.invoke(UiState.Failure(it.message))
                                                           }
                                                           }
                                                   .addOnFailureListener{
                                                       result.invoke(UiState.Failure(it.message))
                                                   }
                                                   }
                                        //
                                    }
                                    .addOnFailureListener{
                                        result.invoke(UiState.Failure(it.message))
                                    }
                            }
                        }
                        .addOnFailureListener {
                            progressDialog.dismiss()
                            Log.d("EEEEEEE",it.message.toString())
                            result.invoke(UiState.Failure("Failure"))
                        }
                }
                .addOnFailureListener{
                    progressDialog.dismiss()
                    Log.d("EEEEEEE", it.message.toString())
                    result.invoke(UiState.Failure("Failure"))
                }
                .addOnProgressListener {
                    val progress = (100.00*it.bytesTransferred/it.totalByteCount).toInt()
                    progressDialog.setMessage("Uploaded:$progress%")
                }
        }
    }
}