package com.yhezra.skinsightapps.ui.camera

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yhezra.skinsightapps.databinding.ActivityImagePreviewBinding

class ImagePreviewActivity : AppCompatActivity() {
    private lateinit var binding :ActivityImagePreviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagePreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengambil data gambar dari intent
        val imagePath = intent.getStringExtra("picture")

        // Mengubah path gambar menjadi URI
        val imageUri = Uri.parse(imagePath)

        // Menampilkan gambar di ImageView
        binding.ivPreviewImage.setImageURI(imageUri)

    }
}