package jama.bookApp.onlinebook.data.repository.admin

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.utils.UiState
import jama.bookApp.onlinebook.data.utils.getFireRealData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetItemClickRepositoryRepositoryImp(var myRef: FirebaseDatabase):GetItemClickRepository {
    override fun getAllBooks(randomKey: String, result: (UiState<PdfBooksModel>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            myRef.getReference(getFireRealData.myRef)
                .addValueEventListener(object :ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.children.forEach {
                            val snap:PdfBooksModel = it.getValue(PdfBooksModel::class.java)!!
                            if (snap.randomKey==randomKey){
                                result.invoke(UiState.Success(snap))
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        result.invoke(UiState.Failure(error.message))
                    }

                })
        }
    }

}