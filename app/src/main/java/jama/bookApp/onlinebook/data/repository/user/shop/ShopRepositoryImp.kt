package jama.bookApp.onlinebook.data.repository.user.shop

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.protobuf.Value
import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.data.utils.UiState
import jama.bookApp.onlinebook.data.utils.getFirebaseRealData
import javax.inject.Inject

class ShopRepositoryImp @Inject constructor(private val myRef:FirebaseDatabase) :ShopRepository {
    override fun getBooks(result: (UiState<ArrayList<PdfBooksModel>>) -> Unit) {
        myRef.getReference(getFirebaseRealData.getBooks)
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = ArrayList<PdfBooksModel>()
                    snapshot.children.forEach {
                        val data:PdfBooksModel = it.getValue(PdfBooksModel::class.java)!!
                        if (data.isAudioBook==false){
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

    override fun getAudioBooks(result: (UiState<ArrayList<PdfBooksModel>>) -> Unit) {
        myRef.getReference(getFirebaseRealData.getBooks)
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = ArrayList<PdfBooksModel>()
                    snapshot.children.forEach {
                        val data:PdfBooksModel = it.getValue(PdfBooksModel::class.java)!!
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