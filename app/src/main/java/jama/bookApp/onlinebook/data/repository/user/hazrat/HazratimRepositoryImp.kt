package jama.bookApp.onlinebook.data.repository.user.hazrat

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

class HazratimRepositoryImp  @Inject constructor(
    private val myRef: FirebaseDatabase,
) : HazratimRepository {

    override fun getHazratBooks(result: (UiState<ArrayList<PdfBooksModel>>) -> Unit) {
        val list = ArrayList<PdfBooksModel>()
        CoroutineScope(Dispatchers.IO).launch {
            myRef.getReference(getFirebaseRealData.getBooks)
                .addValueEventListener(object :ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.value == null) {
                            result.invoke(UiState.Failure("NULL"))
                        } else {
                            snapshot.children.forEach {
                                if (it.value == null) {
                                    result.invoke(UiState.Failure("NULL"))
                                } else {
                                    val data: PdfBooksModel =
                                        it.getValue(PdfBooksModel::class.java)!!
                                    if (data.isAudioBook == false && data.isMuslimBook == true) {
                                        list.add(data)
                                    }
                                    result.invoke(UiState.Success(list))
                                }
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        result.invoke(UiState.Failure(error.message))
                    }

                })
        }
    }

    override fun getHazratAudioBooks(result: (UiState<ArrayList<PdfBooksModel>>) -> Unit) {
        val list = ArrayList<PdfBooksModel>()
        CoroutineScope(Dispatchers.IO).launch {
            myRef.getReference(getFirebaseRealData.getBooks)
                .addValueEventListener(object :ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.value == null) {
                            result.invoke(UiState.Failure("NULL"))
                        } else {
                            snapshot.children.forEach {
                                if (it.value == null) {
                                    result.invoke(UiState.Failure("Null"))
                                } else {
                                    val data: PdfBooksModel =
                                        it.getValue(PdfBooksModel::class.java)!!
                                    if (data.isAudioBook == true && data.isMuslimBook == true) {
                                        list.add(data)
                                    }
                                    result.invoke(UiState.Success(list))
                                }
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