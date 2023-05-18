package com.yhezra.skinsightapps.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.yhezra.skinsightapps.databinding.ActivityLoginBinding
import com.yhezra.skinsightapps.ui.MainMenuActivity
import com.yhezra.skinsightapps.ui.signup.SignUpActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnTvSignup.setOnClickListener {
            val moveToSignUpActivity = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(
                moveToSignUpActivity,
                ActivityOptionsCompat.makeSceneTransitionAnimation(this@LoginActivity).toBundle()
            )
        }

        confInput()
        playAnimation()
    }

    private fun confInput() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            when {
                email.isEmpty() -> {
                    binding.etEmailLayout.error = "Masukkan email"
                }
                password.isEmpty() -> {
                    binding.etPasswordLayout.error = "Masukkan password"
                }
                else -> {
                    val moveToMainMenuActivity =
                        Intent(this@LoginActivity, MainMenuActivity::class.java)
                    startActivity(
                        moveToMainMenuActivity,
                        ActivityOptionsCompat.makeSceneTransitionAnimation(this@LoginActivity)
                            .toBundle()
                    )
                    finish()
//                    signupViewModel.saveUser(UserModel(name, email, password, false))
//                    AlertDialog.Builder(this).apply {
//                        setTitle("Berhasil!")
//                        setMessage("Akun sudah berhasil dibuat sudah jadi nih. Yuk, login dan belajar coding.")
//                        setPositiveButton("Lanjut") { _, _ ->
//                            finish()
//                        }
//                        create()
//                        show()
//                    }
                }
            }
        }
    }

    private fun playAnimation() {
//        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
//            duration = 6000
//            repeatCount = ObjectAnimator.INFINITE
//            repeatMode = ObjectAnimator.REVERSE
//        }.start()

        val logo =
            ObjectAnimator.ofFloat(binding.imgLogoHorizontal, View.ALPHA, 1f).setDuration(500)
        val title = ObjectAnimator.ofFloat(binding.tvTitle, View.ALPHA, 1f).setDuration(500)
        val email = ObjectAnimator.ofFloat(binding.tvEmail, View.ALPHA, 1f).setDuration(500)
        val etEmail = ObjectAnimator.ofFloat(binding.etEmailLayout, View.ALPHA, 1f).setDuration(500)
        val password = ObjectAnimator.ofFloat(binding.tvPassword, View.ALPHA, 1f).setDuration(500)
        val etPassword =
            ObjectAnimator.ofFloat(binding.etPasswordLayout, View.ALPHA, 1f).setDuration(500)
        val signupNow = ObjectAnimator.ofFloat(binding.btnTvSignup, View.ALPHA, 1f).setDuration(500)
        val btnLogin = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(500)

//        val tgtname = AnimatorSet().apply {
//            playTogether(nameTextView, nameEditTextLayout)
//        }
//        val tgtemail = AnimatorSet().apply {
//            playTogether(emailTextView, emailEditTextLayout)
//        }
//        val tgtpass = AnimatorSet().apply {
//            playTogether(passwordTextView, passwordEditTextLayout)
//        }

        AnimatorSet().apply {
            playTogether(
                logo,
                title,
                email,
                etEmail,
                password,
                etPassword,
                signupNow,
                btnLogin
            )
            startDelay = 500
        }.start()
    }
}