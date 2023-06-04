package com.yhezra.skinsightapps.ui.profile

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color.green
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.yhezra.skinsightapps.R
import com.yhezra.skinsightapps.databinding.FragmentProfileBinding
import com.yhezra.skinsightapps.ui.auth.AuthViewModel
import com.yhezra.skinsightapps.ui.auth.AuthViewModelFactory
import com.yhezra.skinsightapps.data.local.Result
import com.yhezra.skinsightapps.data.remote.model.auth.DataUser
import com.yhezra.skinsightapps.ui.auth.signup.SignUpActivity
import com.yhezra.skinsightapps.ui.camera.CameraActivity
import com.yhezra.skinsightapps.ui.detection.DetectionFragment

class ProfileFragment : Fragment() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")

    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory.getInstance(requireContext().dataStore)
    }
    private val profileViewModel: ProfileViewModel by viewModels()

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private var isEditing = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.isLogin().observe(requireActivity()) { uid ->
            if (uid.isNullOrEmpty()) {
                Log.i("PROFILE", "SIUUUU GA GET DATA")
                navigateToSignup()
            } else {
                Log.i("PROFILE", "SIUUUU GETDATA $uid")
                profileViewModel.getDataUser(uid);
            }
        }
        profileViewModel.dataUser.observe(requireActivity()) { dataUser ->
            setView(dataUser)
        }

        profileViewModel.isLoading.observe(requireActivity()) {
            showLoading(it)
        }

        setupAction()
    }

    private fun setView(dataUser: DataUser) {
        val defaultImg = "https://picsum.photos/200/300.jpg"
        val shownImgUrl = dataUser.imgUrl ?: defaultImg
        binding.apply {
            etName.setText(dataUser.name)
            etEmail.setText(dataUser.email)
            Glide.with(binding.root)
                .load(shownImgUrl)
                .into(binding.imgPhoto)
        }
    }

    private fun navigateToSignup() {
        Toast.makeText(
            requireContext(),
            getString(R.string.logout),
            Toast.LENGTH_SHORT
        ).show()
        startActivity(Intent(requireActivity(), SignUpActivity::class.java))
        activity?.finish()
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


    private fun setupAction() {
        binding.apply {
            btnLogout.setOnClickListener {
                authViewModel.logout().observe(requireActivity()) { result ->
                    when (result) {
                        is Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                        }
                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(requireActivity(), "Gagal logout", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
            btnIvEditProfile.setOnClickListener {
                isEditing = true
                changeEditable()
            }
            btnCancel.setOnClickListener {
                isEditing = false
                changeEditable()
            }
            imgPhoto.setOnClickListener {
                if (isEditing) {
                    if (!allPermissionsGranted()) ActivityCompat.requestPermissions(
                        requireActivity(),
                        REQUIRED_PERMISSIONS,
                        REQUEST_CODE_PERMISSIONS
                    )
                    else {
                        startCameraX()
                    }
                }
//                    startCamera
            }
        }
    }

    private fun changeEditable() {
        binding.apply {
//            etName.isEnabled = isEditing
            etEmail.isEnabled = isEditing
            etPassword.isEnabled = isEditing
            etNewPassword.isEnabled = isEditing
            if (isEditing) {
                tvNewPassword.visibility = View.VISIBLE
                etNewPasswordLayout.visibility = View.VISIBLE
                btnSaveChanges.visibility = View.VISIBLE
                btnCancel.visibility = View.VISIBLE
                btnIvEditProfile.visibility = View.GONE
                imgPhoto.borderColor =
                    ContextCompat.getColor(requireActivity(), R.color.light_green)
                imgPhoto.alpha = 0.7f
            } else {
                tvNewPassword.visibility = View.INVISIBLE
                etNewPasswordLayout.visibility = View.INVISIBLE
                btnIvEditProfile.visibility = View.VISIBLE
                btnSaveChanges.visibility = View.INVISIBLE
                btnCancel.visibility = View.INVISIBLE
                imgPhoto.borderColor = ContextCompat.getColor(requireActivity(), R.color.white)
                imgPhoto.alpha = 1f
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.allow_permission),
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                startCameraX()
            }
        }
    }


    private fun startCameraX() {
        val intent = Intent(requireActivity(), CameraActivity::class.java)
        intent.putExtra(CameraActivity.IS_DETECTION, false)

        startActivity(intent)
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }


}