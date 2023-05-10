package com.yhezra.skinsightapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yhezra.skinsightapps.databinding.ActivityMainBinding
import com.yhezra.skinsightapps.ui.MainMenuActivity
import com.yhezra.skinsightapps.ui.signup.SignUpActivity
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    private lateinit var binding :ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timer().schedule(timerTask {
            val moveToMainMenuActivity = Intent(this@MainActivity, SignUpActivity::class.java)
            startActivity(moveToMainMenuActivity)
            finish()
        }, 3000L)
    }
}