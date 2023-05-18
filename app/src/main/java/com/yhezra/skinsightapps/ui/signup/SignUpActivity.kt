package com.yhezra.skinsightapps.ui.signup

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityOptionsCompat
import com.yhezra.skinsightapps.databinding.ActivitySignUpBinding
import com.yhezra.skinsightapps.ui.MainMenuActivity
import com.yhezra.skinsightapps.ui.login.LoginActivity

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnTvLogin.setOnClickListener {
            val moveToLoginActivity = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(
                moveToLoginActivity,
                ActivityOptionsCompat.makeSceneTransitionAnimation(this@SignUpActivity).toBundle()
            )
        }

        confInput()
        playAnimation()
    }

    private fun confInput() {
        binding.btnSignup.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            when {
                name.isEmpty() -> {
                    binding.etNameLayout.error = "Masukkan nama"
                }
                email.isEmpty() -> {
                    binding.etEmailLayout.error = "Masukkan email"
                }
                password.isEmpty() -> {
                    binding.etPasswordLayout.error = "Masukkan password"
                }
                else -> {
                    val moveToMainMenuActivity =
                        Intent(this@SignUpActivity, MainMenuActivity::class.java)
                    startActivity(
                        moveToMainMenuActivity,
                        ActivityOptionsCompat.makeSceneTransitionAnimation(this@SignUpActivity)
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
        val name = ObjectAnimator.ofFloat(binding.tvName, View.ALPHA, 1f).setDuration(500)
        val etName = ObjectAnimator.ofFloat(binding.etNameLayout, View.ALPHA, 1f).setDuration(500)
        val email = ObjectAnimator.ofFloat(binding.tvEmail, View.ALPHA, 1f).setDuration(500)
        val etEmail = ObjectAnimator.ofFloat(binding.etEmailLayout, View.ALPHA, 1f).setDuration(500)
        val password = ObjectAnimator.ofFloat(binding.tvPassword, View.ALPHA, 1f).setDuration(500)
        val etPassword =
            ObjectAnimator.ofFloat(binding.etPasswordLayout, View.ALPHA, 1f).setDuration(500)
        val loginNow = ObjectAnimator.ofFloat(binding.btnTvLogin, View.ALPHA, 1f).setDuration(500)
        val btnSignup = ObjectAnimator.ofFloat(binding.btnSignup, View.ALPHA, 1f).setDuration(500)

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
                name,
                etName,
                email,
                etEmail,
                password,
                etPassword,
                loginNow,
                btnSignup
            )
            startDelay = 500
        }.start()
    }
}