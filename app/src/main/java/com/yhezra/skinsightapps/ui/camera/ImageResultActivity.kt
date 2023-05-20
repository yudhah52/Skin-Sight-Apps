package com.yhezra.skinsightapps.ui.camera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yhezra.skinsightapps.databinding.ActivityImageResultBinding

class ImageResultActivity : AppCompatActivity() {
    private lateinit var binding :ActivityImageResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}