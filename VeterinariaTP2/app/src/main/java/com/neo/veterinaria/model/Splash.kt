package com.neo.veterinaria.model

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import com.neo.veterinaria.R
import com.neo.veterinaria.view.MainActivity

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val foto: ImageView = findViewById(R.id.splash_image)
        foto.setImageResource(R.mipmap.img)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        },2000)
    }
}