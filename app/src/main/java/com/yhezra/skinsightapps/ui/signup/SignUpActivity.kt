package com.yhezra.skinsightapps.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yhezra.skinsightapps.databinding.ActivitySignUpBinding
import com.yhezra.skinsightapps.ui.MainMenuActivity
import com.yhezra.skinsightapps.ui.login.LoginActivity

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loginNow.setOnClickListener {
            val moveToSignInActivity = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(moveToSignInActivity)
        }
        binding.signupButton.setOnClickListener {
            val moveToSignInActivity = Intent(this@SignUpActivity, MainMenuActivity::class.java)
            startActivity(moveToSignInActivity)
        }

    }
}