package com.yhezra.skinsightapps.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
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
            val moveToSignInActivity = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(moveToSignInActivity)
        }

        confInput()
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
                    val moveToSignInActivity = Intent(this@SignUpActivity, MainMenuActivity::class.java)
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