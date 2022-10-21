package com.example.gsi

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Pair
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class SplashScreenActivity : AppCompatActivity() {
    lateinit var BytextView: TextView
    lateinit var nameTextView: TextView
    lateinit var logoImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //pantalla completa
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_splash_screen)

        //ANIMACIONES
        val animacion1 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba)
        val animacion2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo)


        BytextView = findViewById(R.id.ByTextView)
        nameTextView = findViewById(R.id.jeafTextView)
        logoImageView = findViewById(R.id.logoImageView)

        BytextView.animation = animacion2
        nameTextView.animation = animacion2
        logoImageView.animation = animacion1


        Handler().postDelayed({
            val intent = Intent(this@SplashScreenActivity, WelcomeActivity::class.java)
            val pairs: Array<Pair<*, *>?> = arrayOfNulls(2)
            pairs[0] = Pair<View, String>(logoImageView, "logoImageTrans")
            pairs[1] = Pair<View, String>(nameTextView, "textTrans")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val options =
                    ActivityOptions.makeSceneTransitionAnimation(this@SplashScreenActivity)
                startActivity(intent, options.toBundle())
            } else {
                startActivity(intent)
                finish()
            }
            startActivity(intent)
            finish()
        }, 3500)
    }
}