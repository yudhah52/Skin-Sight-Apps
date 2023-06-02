package com.yhezra.skinsightapps.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yhezra.skinsightapps.data.remote.model.article.ArticleItem
import com.yhezra.skinsightapps.databinding.FragmentHomeBinding
import com.yhezra.skinsightapps.ui.home.article.detailarticle.DetailArticleActivity
import com.yhezra.skinsightapps.ui.home.article.adapter.ListArticleAdapter
import com.yhezra.skinsightapps.ui.home.article.ArticleViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val articleViewModel: ArticleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleViewModel.getListArticle()
        articleViewModel.listArticle.observe(viewLifecycleOwner) { listArticle ->
            setArticleData(listArticle)
        }
        articleViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }
    }

    private fun setArticleData(listArticle: List<ArticleItem>) {
        val adapter = ListArticleAdapter(listArticle)

        adapter.setOnItemClickCallback(object : ListArticleAdapter.OnItemClickCallback {
            override fun onItemArticleClicked(item: ArticleItem) {
                navigateToDetailArticle(item)
            }
        })

        binding.apply {
            rvArticle.adapter = adapter

            val layoutManager = LinearLayoutManager(requireContext())
            binding.rvArticle.layoutManager = layoutManager
            val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
            binding.rvArticle.addItemDecoration(itemDecoration)
        }

    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun navigateToDetailArticle(article: ArticleItem) {
        val moveToDetail =Intent(requireActivity(), DetailArticleActivity::class.java)
        moveToDetail.putExtra(DetailArticleActivity.ID_ARTICLE,article.id)
        startActivity(moveToDetail)
    }
}