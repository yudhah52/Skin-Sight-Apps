package com.yhezra.skinsightapps.ui.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yhezra.skinsightapps.data.remote.model.history.HistoryDetectionItem
import com.yhezra.skinsightapps.databinding.ItemHistoryBinding

class ListHistoryAdapter(private val listHistory: List<HistoryDetectionItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HistoryViewHolder -> holder.bind(listHistory[position])
        }
    }

    override fun getItemCount(): Int = listHistory.size


    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(history: HistoryDetectionItem) {
            val detectionClass = history.predictedClass
            val date = history.datetime
            val imageUrl = history.detectionImg
            val category = history.type
            binding.tvDateHistory.text = date
            binding.tvDetection.text = detectionClass
            binding.tvDetectionCategory.text = category
            Glide.with(binding.root)
                .load(imageUrl)
                .into(binding.ivItemHistory)
            binding.root.setOnClickListener {
                onItemClickCallback.onItemHistoryClicked(history)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemHistoryClicked(item: HistoryDetectionItem)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}