package com.yhezra.skinsightapps.ui.onboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yhezra.skinsightapps.R
import com.yhezra.skinsightapps.data.local.model.Onboard
import com.yhezra.skinsightapps.databinding.ActivityOnboardingBinding
import com.yhezra.skinsightapps.ui.onboard.adapter.OnboardListAdapter
import com.yhezra.skinsightapps.ui.signup.SignUpActivity

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var onboardListAdapter:OnboardListAdapter

    val onboardList:List<Onboard> = listOf(
        Onboard(
            img = R.drawable.img_onboard,
            description = "Selamat datang di aplikasi SkinSight! Ayo lihat lebih jauh seputar kulit kamu!"
        ),
        Onboard(
            img = R.drawable.img_onboard,
            description = "Ketahui kondisi kulit dan tipe kulit kamu dalam sekejap!"
        ),
        Onboard(
            img = R.drawable.img_onboard,
            description = "Dapatkan kulit sehat yang kamu inginkan, coba sekarang dan lihat hasilnya!"
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setOnboardList()

        binding.btnOnboard.setOnClickListener {
            val moveToSignInActivity = Intent(this@OnboardingActivity, SignUpActivity::class.java)
            startActivity(moveToSignInActivity)
        }
    }

    private fun setOnboardList() {
        onboardListAdapter = OnboardListAdapter(onboardList)
        binding.viewpagerOnboard.adapter = onboardListAdapter
    }
}