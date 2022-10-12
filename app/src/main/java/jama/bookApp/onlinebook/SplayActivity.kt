package jama.bookApp.onlinebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import dagger.hilt.android.AndroidEntryPoint
import jama.bookApp.onlinebook.data.utils.SharedPref
import jama.bookApp.onlinebook.databinding.ActivitySplayBinding
import jama.bookApp.onlinebook.presentation.admin.AdminActivity
import jama.bookApp.onlinebook.presentation.user.UserActivity

@AndroidEntryPoint
class SplayActivity : AppCompatActivity() {
    lateinit var binding:ActivitySplayBinding
    val sharedPref by lazy {
        SharedPref(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val anim = AnimationUtils.loadAnimation(this,R.anim.fade_in)
        anim.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                if (sharedPref.getEmail()=="bekodilov.99@mail.ru"){
                    startActivity(Intent(this@SplayActivity, AdminActivity::class.java))
                    finish()
                }else{
                    startActivity(Intent(this@SplayActivity,UserActivity::class.java))
                    finish()
                }
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })
        binding.image.startAnimation(anim)

    }
}