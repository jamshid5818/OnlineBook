package jama.bookApp.onlinebook.data.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jama.bookApp.onlinebook.data.repository.admin.AddBookRepository
import jama.bookApp.onlinebook.data.repository.admin.AddBookRepositoryImp
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAddBookRepository(
        storageReference: FirebaseStorage,
        databaseReference: FirebaseDatabase
    ):AddBookRepository{
        return AddBookRepositoryImp(storageReference, databaseReference)
    }
}