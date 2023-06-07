package com.yhezra.skinsightapps.ui.onboard

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.yhezra.skinsightapps.R
import com.yhezra.skinsightapps.data.local.model.Onboard
import com.yhezra.skinsightapps.databinding.ActivityOnboardingBinding
import com.yhezra.skinsightapps.ui.MainMenuActivity
import com.yhezra.skinsightapps.ui.auth.AuthViewModel
import com.yhezra.skinsightapps.ui.auth.AuthViewModelFactory
import com.yhezra.skinsightapps.ui.onboard.adapter.OnboardListAdapter
import com.yhezra.skinsightapps.ui.onboard.adapter.OnboardingPageChangeCallback
import com.yhezra.skinsightapps.ui.auth.signup.SignUpActivity

class OnboardingActivity : AppCompatActivity() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")

    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory.getInstance(dataStore)
    }

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var onboardListAdapter: OnboardListAdapter

    private lateinit var onboardingPageChangeCallback: OnboardingPageChangeCallback

    private var currentPosition = 0
    private val onboardList: List<Onboard> = listOf(
        Onboard(
            img = R.drawable.img_onboard,
            description = "Selamat datang di aplikasi SkinSight! Ayo lihat lebih jauh seputar kulit kamu!"
        ),
        Onboard(
            img = R.drawable.img_onboard_2,
            description = "Ketahui kondisi kulit dan tipe kulit kamu dalam sekejap!"
        ),
        Onboard(
            img = R.drawable.img_onboard_3,
            description = "Dapatkan kulit sehat yang kamu inginkan, coba sekarang dan lihat hasilnya!"
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        authViewModel.isLogin().observe(this) {
            if (!it.isNullOrEmpty()) {
                val moveToMainMenuActivity =
                    Intent(this@OnboardingActivity, MainMenuActivity::class.java)
                startActivity(moveToMainMenuActivity)
                finish()
            }
        }

        onboardingPageChangeCallback = OnboardingPageChangeCallback(
            viewPager = binding.viewpagerOnboard,
            btnLeft = binding.btnLeftOnboard,
            btnRight = binding.btnRightOnboard,
            btnOnboard = binding.btnOnboard,
            pageSize = onboardList.size,
        )
        setOnboardList()
        setButtonVisibility()

        binding.apply {
            btnOnboard.setOnClickListener {
                val moveToSignInActivity =
                    Intent(this@OnboardingActivity, SignUpActivity::class.java)
                startActivity(moveToSignInActivity,
                    ActivityOptionsCompat.makeSceneTransitionAnimation(this@OnboardingActivity)
                        .toBundle()
                )
            }
            btnRightOnboard.setOnClickListener {
                if (currentPosition < onboardList.size - 1)
                    currentPosition += 1
                setButtonVisibility()
                onboardingPageChangeCallback.setCurrentItem(currentPosition)
            }
            btnLeftOnboard.setOnClickListener {
                if (currentPosition > 0)
                    currentPosition -= 1
                setButtonVisibility()
                onboardingPageChangeCallback.setCurrentItem(currentPosition)

            }
        }
    }

    private fun setButtonVisibility() {
        binding.apply {
            when (currentPosition) {
                onboardList.size - 1 -> btnRightOnboard.visibility = View.INVISIBLE
                0 -> btnLeftOnboard.visibility = View.INVISIBLE
                else -> {
                    btnLeftOnboard.visibility = View.VISIBLE
                    btnRightOnboard.visibility = View.VISIBLE
                }
            }

            if (currentPosition == onboardList.size - 1)
                btnOnboard.visibility = View.VISIBLE
            else
                btnOnboard.visibility = View.INVISIBLE
        }

    }

    private fun setOnboardList() {
        onboardListAdapter = OnboardListAdapter(onboardList)
        binding.viewpagerOnboard.adapter = onboardListAdapter
        binding.viewpagerOnboard.registerOnPageChangeCallback(onboardingPageChangeCallback)
        currentPosition = onboardingPageChangeCallback.getSelectedPosition()
    }
}