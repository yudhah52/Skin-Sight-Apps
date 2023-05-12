package com.yhezra.skinsightapps.ui.detailarticle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yhezra.skinsightapps.databinding.ActivityDetailArticleBinding

class DetailArticleActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}