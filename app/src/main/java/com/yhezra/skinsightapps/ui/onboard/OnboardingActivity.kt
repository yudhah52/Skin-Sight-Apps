package com.yhezra.skinsightapps.ui.onboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yhezra.skinsightapps.databinding.ActivityOnboardingBinding
import com.yhezra.skinsightapps.ui.signup.SignUpActivity

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.onboardButton.setOnClickListener {
            val moveToSignInActivity = Intent(this@OnboardingActivity, SignUpActivity::class.java)
            startActivity(moveToSignInActivity)
        }
    }
}