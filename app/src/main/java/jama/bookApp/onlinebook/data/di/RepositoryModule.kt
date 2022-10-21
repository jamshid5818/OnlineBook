package jama.bookApp.onlinebook.data.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jama.bookApp.onlinebook.data.repository.GetAllBooksRepository
import jama.bookApp.onlinebook.data.repository.GetAllBooksRepositoryImp
import jama.bookApp.onlinebook.data.repository.admin.*
import jama.bookApp.onlinebook.data.repository.user.AuthRepository
import jama.bookApp.onlinebook.data.repository.user.AuthRepositoryImp
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
    @Provides
    @Singleton
    fun provideGetAllBooks(
        databaseReference: FirebaseDatabase
    ): GetAllBooksRepository {
        return GetAllBooksRepositoryImp(databaseReference)
    }
    @Provides
    @Singleton
    fun provides(
        databaseReference: FirebaseDatabase
    ):GetItemClickRepository{
        return GetItemClickRepositoryRepositoryImp(databaseReference)
    }
    @Provides
    @Singleton
    fun provideAutghRepository(
        auth: FirebaseAuth,
        myRef: FirebaseDatabase,
    ): AuthRepository {
        return AuthRepositoryImp(auth,myRef)
    }
}