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

        binding.btnTvSignup.setOnClickListener {
            val moveToSignInActivity = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(moveToSignInActivity)
        }

        confInput()
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
                    val moveToSignInActivity = Intent(this@LoginActivity, MainMenuActivity::class.java)
                    startActivity(moveToSignInActivity)
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
}