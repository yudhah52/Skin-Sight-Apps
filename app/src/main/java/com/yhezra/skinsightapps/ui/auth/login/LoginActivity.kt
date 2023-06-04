package com.yhezra.skinsightapps.ui.auth.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.yhezra.skinsightapps.databinding.ActivityLoginBinding
import com.yhezra.skinsightapps.ui.MainMenuActivity
import com.yhezra.skinsightapps.ui.auth.AuthViewModel
import com.yhezra.skinsightapps.ui.auth.AuthViewModelFactory
import com.yhezra.skinsightapps.ui.auth.signup.SignUpActivity
import com.yhezra.skinsightapps.data.local.Result

class LoginActivity : AppCompatActivity() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")

    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory.getInstance(dataStore)
    }
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupAction()
        playAnimation()
    }

    private fun setupAction() {
        binding.btnTvReset.setOnClickListener {
            val moveToResetPasswordActivity =
                Intent(this@LoginActivity, ResetPasswordActivity::class.java)
            startActivity(
                moveToResetPasswordActivity,
                ActivityOptionsCompat.makeSceneTransitionAnimation(this@LoginActivity).toBundle()
            )
        }
        binding.btnTvSignup.setOnClickListener {
            val moveToSignUpActivity = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(
                moveToSignUpActivity,
                ActivityOptionsCompat.makeSceneTransitionAnimation(this@LoginActivity).toBundle()
            )
        }
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
                Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    binding.apply {
                        etEmail.onEditorAction(EditorInfo.IME_ACTION_DONE)
                        etPassword.onEditorAction(EditorInfo.IME_ACTION_DONE)
                    }
                    authViewModel.login(email, password).observe(this) { result ->
                        when (result) {
                            is Result.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }
                            is Result.Success -> {
                                binding.progressBar.visibility = View.GONE
                                val moveToMainMenuActivity =
                                    Intent(this@LoginActivity, MainMenuActivity::class.java)
                                startActivity(
                                    moveToMainMenuActivity,
                                    ActivityOptionsCompat.makeSceneTransitionAnimation(this@LoginActivity)
                                        .toBundle()
                                )
                            }
                            is Result.Error -> {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Email atau password salah",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }

                else -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Email atau password invalid",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun playAnimation() {
        val logo =
            ObjectAnimator.ofFloat(binding.imgLogoHorizontal, View.ALPHA, 1f).setDuration(500)
        val title = ObjectAnimator.ofFloat(binding.tvTitle, View.ALPHA, 1f).setDuration(500)
        val email = ObjectAnimator.ofFloat(binding.tvEmail, View.ALPHA, 1f).setDuration(500)
        val etEmail = ObjectAnimator.ofFloat(binding.etEmailLayout, View.ALPHA, 1f).setDuration(500)
        val password = ObjectAnimator.ofFloat(binding.tvPassword, View.ALPHA, 1f).setDuration(500)
        val etPassword =
            ObjectAnimator.ofFloat(binding.etPasswordLayout, View.ALPHA, 1f).setDuration(500)
        val reset = ObjectAnimator.ofFloat(binding.btnTvReset, View.ALPHA, 1f).setDuration(500)
        val signupNow = ObjectAnimator.ofFloat(binding.btnTvSignup, View.ALPHA, 1f).setDuration(500)
        val btnLogin = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playTogether(
                logo,
                title,
                email,
                etEmail,
                password,
                etPassword,
                reset,
                signupNow,
                btnLogin
            )
            startDelay = 500
        }.start()
    }
}