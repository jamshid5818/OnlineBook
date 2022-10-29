package jama.bookApp.onlinebook.data.utils
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedPref(context: Context) {

    private var preferences: SharedPreferences =
        context.getSharedPreferences(SharedPrefConstants.LOCAL_SHARED_PREF, MODE_PRIVATE)

    private lateinit var editor: SharedPreferences.Editor

    ////////////////////////////////////////////////////////

    fun setEmail(name:String){
        editor = preferences.edit()
        editor.putString("PersonEmail", name)
        editor.apply()
    }
    fun getEmail() = preferences.getString("PersonEmail","")
    fun setName(name: String){
        editor = preferences.edit()
        editor.putString("NAME", name)
        editor.apply()
    }
    fun getName() = preferences.getString("NAME","")
}