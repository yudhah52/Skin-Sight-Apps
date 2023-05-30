package com.yhezra.skinsightapps.ui.detection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yhezra.skinsightapps.R
import com.yhezra.skinsightapps.databinding.ActivityDetectionResultBinding

class DetectionResultActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding:ActivityDetectionResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetectionResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar()
    }

    private fun setToolbar() {
        binding.toolbar.btnBackToolbar.setOnClickListener(this)
        binding.toolbar.tvToolbarTitle.text = "Detection Result"
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_back_toolbar -> {
                finish()
            }
        }
    }

    companion object {
        const val CAMERA_X_RESULT = 200
    }
}