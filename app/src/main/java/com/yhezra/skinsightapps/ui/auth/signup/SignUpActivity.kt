package com.yhezra.skinsightapps.ui.auth.signup

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
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityOptionsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.yhezra.skinsightapps.databinding.ActivitySignUpBinding
import com.yhezra.skinsightapps.ui.MainMenuActivity
import com.yhezra.skinsightapps.ui.auth.AuthViewModel
import com.yhezra.skinsightapps.ui.auth.AuthViewModelFactory
import com.yhezra.skinsightapps.ui.auth.login.LoginActivity
import com.yhezra.skinsightapps.data.local.Result

class SignUpActivity : AppCompatActivity() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")

    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory.getInstance(dataStore)
    }

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupAction()
        playAnimation()
    }

    private fun setupAction() {

        binding.btnTvLogin.setOnClickListener {
            val moveToLoginActivity = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(
                moveToLoginActivity,
                ActivityOptionsCompat.makeSceneTransitionAnimation(this@SignUpActivity).toBundle()
            )
        }

        binding.btnSignup.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val phone = "08123123123"
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
                Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 8 -> {
                    binding.apply {
                        etName.onEditorAction(EditorInfo.IME_ACTION_DONE)
                        etEmail.onEditorAction(EditorInfo.IME_ACTION_DONE)
                        etPassword.onEditorAction(EditorInfo.IME_ACTION_DONE)
                    }
                    authViewModel.register(email, password, name, phone).observe(this) { result ->
                        when (result) {
                            is Result.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }
                            is Result.Success -> {
                                binding.progressBar.visibility = View.GONE
                                AlertDialog.Builder(this).apply {
                                    setTitle("Yey!")
                                    setMessage("Akun berhasil terdaftar! Masuk dan bagikan cerita!")
                                    setPositiveButton("Lanjut") { _, _ ->
                                        finish()
                                    }
                                    create()
                                    show()
                                }
                            }
                            is Result.Error -> {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(
                                    this@SignUpActivity, result.error, Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
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