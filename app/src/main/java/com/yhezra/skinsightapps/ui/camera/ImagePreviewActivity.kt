package com.yhezra.skinsightapps.ui.camera

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.yhezra.skinsightapps.R
import com.yhezra.skinsightapps.databinding.ActivityImagePreviewBinding
import com.yhezra.skinsightapps.ui.auth.AuthViewModel
import com.yhezra.skinsightapps.ui.auth.AuthViewModelFactory
import com.yhezra.skinsightapps.ui.detection.DetectionResultActivity
import com.yhezra.skinsightapps.ui.profile.ProfileViewModel
import java.io.File

class ImagePreviewActivity : AppCompatActivity() {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")

    private var uid: String? = null

    private var isDetection = true
    private var isSkinDisease = true
    private var imageFile: File? = null

    private lateinit var binding: ActivityImagePreviewBinding
    private val profileViewModel: ProfileViewModel by viewModels()

    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory.getInstance(dataStore)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagePreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profileViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        profileViewModel.isSuccess.observe(this) { isSuccess ->
            showPopUp(isSuccess)
        }

        authViewModel.isLogin().observe(this) { uid ->
            if (!uid.isNullOrEmpty()) {
                this.uid = uid
            }
        }

        getCondition()

        setToolBar()
        setPreviewImage()
        setAction()
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


    private fun showPopUp(success: Boolean) {
        if (success) {
            binding.progressBar.visibility = View.GONE
            AlertDialog.Builder(this).apply {
                setTitle("Berhasil!")
                setMessage("Update Profile Berhasil")
                setPositiveButton("Lanjut") { _, _ ->
                    finish()
                }
                create()
                show()
            }
        }
    }

    private fun getCondition() {
        isDetection = intent.getBooleanExtra(IS_DETECTION, true)
        isSkinDisease = intent.getBooleanExtra(IS_SKIN_DISEASE, true)
    }

    private fun setAction() {
        binding.btnUpload.setOnClickListener {
            Log.i("SIUUUU", "SIUUUU $isDetection $isSkinDisease")
            if (isDetection) {
                val intent = Intent(this@ImagePreviewActivity, DetectionResultActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                uploadProfilePicture()

            }

        }
    }

    private fun uploadProfilePicture() {
        if (!uid.isNullOrEmpty())
            profileViewModel.editProfilePicture(uid!!, imageFile = imageFile!!)
    }

    private fun setPreviewImage() {
        val imagePath = intent.getStringExtra(IMAGE_PATH)
        val imageUri = Uri.parse(imagePath)
        imageFile = imageUri.path?.let { File(it) }
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

    companion object {
        const val IS_DETECTION = "is_detection"
        const val IS_SKIN_DISEASE = "is_skin_disease"
        const val IMAGE_PATH = "image_absolute_path"
    }
}