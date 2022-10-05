package jama.bookApp.onlinebook.data.utils

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.toast(msg: String?){
    Toast.makeText(requireContext(),msg, Toast.LENGTH_LONG).show()
}
fun Fragment.snackbar(message:String,view: View){
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}
fun View.disabled(){
    isEnabled = false
}

fun View.enabled(){
    isEnabled = true
}