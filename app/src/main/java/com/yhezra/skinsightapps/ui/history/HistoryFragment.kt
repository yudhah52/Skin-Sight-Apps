package com.yhezra.skinsightapps.ui.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yhezra.skinsightapps.data.local.Result
import com.yhezra.skinsightapps.data.remote.model.history.HistoryDetectionItem
import com.yhezra.skinsightapps.databinding.FragmentHistoryBinding
import com.yhezra.skinsightapps.ui.auth.AuthViewModel
import com.yhezra.skinsightapps.ui.auth.AuthViewModelFactory
import com.yhezra.skinsightapps.ui.detection.DetectionResultActivity
import com.yhezra.skinsightapps.ui.detection.DetectionDiseaseViewModel
import com.yhezra.skinsightapps.ui.detection.DetectionDiseaseViewModelFactory
import com.yhezra.skinsightapps.ui.history.adapter.ListHistoryAdapter

class HistoryFragment : Fragment() {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private var uid: String = ""

    private val detectionDiseaseViewModel: DetectionDiseaseViewModel by viewModels {
        DetectionDiseaseViewModelFactory.getInstance()
    }

    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory.getInstance(requireContext().dataStore)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.isLogin().observe(requireActivity()) { uid ->
            if (!uid.isNullOrEmpty()) {
                this.uid = uid
                Log.i("HISTORY", "UID : ${this.uid}")
            }
        }

        getAllHistory(uid)

    }

    private fun getAllHistory(uid: String) {
        Log.i("HISTORY2", "UID : $uid")
        detectionDiseaseViewModel.getAllHistory(uid).observe(requireActivity()) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val listHistory = result.data
                    setHistoryView(listHistory)
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireActivity(),
                        result.error,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }

    }

    private fun setHistoryView(listHistory: List<HistoryDetectionItem>) {
        val adapter = ListHistoryAdapter(listHistory)

        adapter.setOnItemClickCallback(object : ListHistoryAdapter.OnItemClickCallback {
            override fun onItemHistoryClicked(item: HistoryDetectionItem) {
                navigateToDetailHistory(item)
            }
        })

        binding.apply {
            rvHistory.adapter = adapter

            val layoutManager = LinearLayoutManager(requireContext())
            binding.rvHistory.layoutManager = layoutManager
            val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
            binding.rvHistory.addItemDecoration(itemDecoration)
        }
    }


    private fun navigateToDetailHistory(item: HistoryDetectionItem) {
        val intent = Intent(
            requireActivity(),
            DetectionResultActivity::class.java
        )
        intent.putExtra(
            DetectionResultActivity.DETECTION_RESULT,
            item
        )
        intent.putExtra(DetectionResultActivity.IS_HISTORY,true)
        startActivity(intent)
    }
}

