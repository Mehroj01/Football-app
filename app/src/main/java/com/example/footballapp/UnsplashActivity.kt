package com.example.footballapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.footballapp.databinding.ActivityUnsplashBinding
import android.view.View

import android.widget.TextView

import android.view.animation.Animation
import android.view.animation.AnimationUtils


class UnsplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUnsplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUnsplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        runAnimation()
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intent = Intent(this, MainActivity::class.java)
                finish()
                startActivity(intent)
            },
            3000
        )
    }

    private fun runAnimation() {
        val a: Animation = AnimationUtils.loadAnimation(this, R.anim.tv_anim)
        a.reset()

        binding.tv.clearAnimation()
        binding.tv.startAnimation(a)
    }
}