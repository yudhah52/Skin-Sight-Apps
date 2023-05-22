package com.yhezra.skinsightapps.ui.camera

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.yhezra.skinsightapps.R
import com.yhezra.skinsightapps.databinding.ActivityImagePreviewBinding

class ImagePreviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImagePreviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagePreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolBar()
        setPreviewImage()
    }

    private fun setPreviewImage() {
        val imagePath = intent.getStringExtra("picture")
        val imageUri = Uri.parse(imagePath)
        binding.ivPreviewImage.setImageURI(imageUri)

    }

    private fun setToolBar() {
        binding.apply {
            setSupportActionBar(toolbar.toolbar)
            toolbar.tvToolbarTitle.text = getString(R.string.preview)
            toolbar.btnBackToolbar.setOnClickListener {
                finish()
            }
        }
    }
}