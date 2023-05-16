package com.yhezra.skinsightapps.ui.detection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yhezra.skinsightapps.databinding.ActivityDetectionResultBinding

class DetectionResultActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetectionResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetectionResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}