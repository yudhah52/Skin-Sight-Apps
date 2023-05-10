package com.yhezra.skinsightapps.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yhezra.skinsightapps.databinding.ActivityLoginBinding
import com.yhezra.skinsightapps.ui.MainMenuActivity
import com.yhezra.skinsightapps.ui.signup.SignUpActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signUpNow.setOnClickListener {
            val moveToSignInActivity = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(moveToSignInActivity)
        }
        binding.loginButton.setOnClickListener {
            val moveToSignInActivity = Intent(this@LoginActivity, MainMenuActivity::class.java)
            startActivity(moveToSignInActivity)
        }
    }
}