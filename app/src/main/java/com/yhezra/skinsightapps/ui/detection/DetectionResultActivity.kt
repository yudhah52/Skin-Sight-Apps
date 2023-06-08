package com.yhezra.skinsightapps.ui.detection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yhezra.skinsightapps.R
import com.yhezra.skinsightapps.data.remote.model.detection.DetectionDiseaseResponse
import com.yhezra.skinsightapps.data.remote.model.history.HistoryDetectionItem
import com.yhezra.skinsightapps.databinding.ActivityDetectionResultBinding
import java.text.SimpleDateFormat
import java.util.*

class DetectionResultActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityDetectionResultBinding

    private var isHistory = false
    private lateinit var currentDate: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetectionResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataResult()
        setToolbar()

    }

    private fun getDataResult() {
        isHistory = intent.getBooleanExtra(IS_HISTORY, false)
        if (!isHistory) {
            val dataResultDetection =
                intent.getParcelableExtra<DetectionDiseaseResponse>(DETECTION_RESULT) as DetectionDiseaseResponse
            currentDate = SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(Date())
            setDataResultView(
                currentDate = currentDate,
                imgUrl = dataResultDetection.detectionImg,
                classResult = dataResultDetection.jsonMemberClass,
                type = dataResultDetection.type
            )
        } else {
            val dataResultHistory =
                intent.getParcelableExtra<HistoryDetectionItem>(DETECTION_RESULT) as HistoryDetectionItem
            currentDate = dataResultHistory.datetime
            setDataResultView(
                currentDate = currentDate,
                imgUrl = dataResultHistory.detectionImg,
                classResult = dataResultHistory.predictedClass,
                type = dataResultHistory.type
            )
        }
    }

    private fun setDataResultView(
        currentDate: String = "",
        imgUrl: String = "",
        classResult: String = "",
        type: String = ""
    ) {
        binding.apply {
            tvDate.text = currentDate
            tvTitle.text = type
            cardResult.tvDetectionResult.text = classResult
            Glide.with(binding.root)
                .load(imgUrl)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(ivDetectionResult)
        }
    }

    private fun setToolbar() {
        binding.toolbar.btnBackToolbar.setOnClickListener(this)
        binding.toolbar.tvToolbarTitle.text = if (!isHistory) "Detection Result" else "History"
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_back_toolbar -> {
                finish()
            }
        }
    }

    companion object {
        const val DETECTION_RESULT = "detection_result"
        const val IS_HISTORY = "is_history"
    }
}