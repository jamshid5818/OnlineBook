package jama.bookApp.onlinebook.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import jama.bookApp.onlinebook.data.database.database.Database

@HiltAndroidApp
class CoreApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        Database.init(this).createDatabase().open()
    }

    override fun onTerminate() {
        Database.getDatabase().close()
        super.onTerminate()
    }
}