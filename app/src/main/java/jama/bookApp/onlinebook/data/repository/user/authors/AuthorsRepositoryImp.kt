package jama.bookApp.onlinebook.data.repository.user.authors

import android.util.Log
import androidx.core.net.toUri
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import jama.bookApp.onlinebook.data.utils.UiState
import jama.bookApp.onlinebook.data.utils.getFirebaseRealData
import jama.bookApp.onlinebook.presentation.user.profile.authors.adapter.AuthorsData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthorsRepositoryImp @Inject constructor(
    var storageReference: FirebaseStorage,
    private val myRef: FirebaseDatabase,
) : AuthorsRepository {

    override fun getAuthors(result: (UiState<List<AuthorsData>>) -> Unit) {
        val list = ArrayList<AuthorsData>()
        CoroutineScope(Dispatchers.IO).launch {
            myRef.getReference(getFirebaseRealData.getBooks)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.children.forEach {
                            val data: AuthorsData = it.getValue(AuthorsData::class.java)!!
                            Log.d("FFFFFFFFFFF", data.toString())
                            list.add(AuthorsData(data.name ?: "", data.image ?: "",data.id?:""))
                        }
                        result.invoke(UiState.Success(list))
                    }

                    override fun onCancelled(error: DatabaseError) {
                        result.invoke(UiState.Failure(error.message))
                    }
                })
        }
    }

    override fun addAuthor(authorsData: AuthorsData, result: (UiState<String>) -> Unit) {
        val key = myRef.getReference("Authors").push().key ?: ""
        val referenceBookImage = storageReference.getReference("Authors Image").child("${key}.jpg")
        referenceBookImage.putFile(authorsData.image.toUri()).addOnSuccessListener {
            referenceBookImage.downloadUrl.addOnSuccessListener {
                myRef.getReference("Authors").child(authorsData.name)
                    .setValue(AuthorsData(name = authorsData.name, image = it.toString(),key))
                    .addOnSuccessListener {
                        result.invoke(UiState.Success("Success"))
                    }.addOnFailureListener { t ->
                        result.invoke(UiState.Failure(t.message))
                    }
            }
        }
    }
}