package jama.bookApp.onlinebook.data.repository.user.audio

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.utils.UiState
import jama.bookApp.onlinebook.data.utils.getFirebaseRealData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AudioRepositoryImp @Inject constructor(private val myRef:FirebaseDatabase):AudioRepository {
    override fun getAudioBooks(result: (UiState<List<PdfBooksModel>>) -> Unit) {
        val list = ArrayList<PdfBooksModel>()
        CoroutineScope(Dispatchers.IO).launch {
            myRef.getReference(getFirebaseRealData.getBooks)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.children.forEach {
                            val data: PdfBooksModel = it.getValue(PdfBooksModel::class.java)!!
                            if (data.isAudioBook==true){
                                list.add(data)
                            }
                        }
                        result.invoke(UiState.Success(list))
                    }

                    override fun onCancelled(error: DatabaseError) {
                        result.invoke(UiState.Failure(error.message))
                    }

                })
        }
    }
}