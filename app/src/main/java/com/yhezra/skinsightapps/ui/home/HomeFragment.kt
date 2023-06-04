package com.yhezra.skinsightapps.ui.home

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
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yhezra.skinsightapps.R
import com.yhezra.skinsightapps.data.remote.model.article.ArticleItem
import com.yhezra.skinsightapps.data.remote.model.auth.DataUser
import com.yhezra.skinsightapps.databinding.FragmentHomeBinding
import com.yhezra.skinsightapps.ui.auth.AuthViewModel
import com.yhezra.skinsightapps.ui.auth.AuthViewModelFactory
import com.yhezra.skinsightapps.ui.auth.signup.SignUpActivity
import com.yhezra.skinsightapps.ui.home.article.detailarticle.DetailArticleActivity
import com.yhezra.skinsightapps.ui.home.article.adapter.ListArticleAdapter
import com.yhezra.skinsightapps.ui.home.article.ArticleViewModel
import com.yhezra.skinsightapps.ui.profile.ProfileViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val articleViewModel: ArticleViewModel by viewModels()

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")

    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory.getInstance(requireContext().dataStore)
    }
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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
                profileViewModel.getDataUser(uid)
            }
        }
        profileViewModel.dataUser.observe(requireActivity()) { dataUser ->
            setView(dataUser)
        }

        articleViewModel.getListArticle()
        articleViewModel.listArticle.observe(viewLifecycleOwner) { listArticle ->
            setArticleData(listArticle)
        }
        articleViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        setupAction()
    }

    private fun setupAction() {
        binding.btnTry.setOnClickListener {

            val bottomNavView =
                requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav_view)
            bottomNavView.selectedItemId = R.id.navigation_detection

            val navController =
                Navigation.findNavController(requireActivity(), R.id.bottom_nav_host_fragment)
            navController.navigate(R.id.navigation_detection)

        }
    }

    private fun setView(dataUser: DataUser) {
        val defaultImg = "https://picsum.photos/200/300.jpg"
        val shownImgUrl = dataUser.imgUrl ?: defaultImg
        binding.apply {
            val greet = getString(R.string.greet, dataUser.name)
            tvGreet.text = greet
            Glide.with(binding.root)
                .load(shownImgUrl)
                .into(binding.imgPhoto)
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
        val moveToDetail = Intent(requireActivity(), DetailArticleActivity::class.java)
        moveToDetail.putExtra(DetailArticleActivity.ID_ARTICLE, article.id)
        startActivity(moveToDetail)
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


}