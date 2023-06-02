package com.yhezra.skinsightapps.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yhezra.skinsightapps.data.local.preference.UserPreference
import com.yhezra.skinsightapps.data.remote.api.ApiConfig
import com.yhezra.skinsightapps.data.remote.model.article.ArticleResponse
import com.yhezra.skinsightapps.data.remote.model.auth.DataUser
import com.yhezra.skinsightapps.data.remote.model.auth.UserResponse
import com.yhezra.skinsightapps.ui.home.article.ArticleViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {
    private val _dataUser = MutableLiveData<DataUser>()
    val dataUser: LiveData<DataUser> = _dataUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

//    fun getToken() = userRepository.getToken().asLiveData()

    fun getDataUser(token: String) {
        Log.i("siuuu", "suiiii ${token}")

        _isLoading.value = true
        val client = ApiConfig.getUserApiService().getDataUser(uid = token)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                if (response.isSuccessful) {

                    val responseBody = response.body()
                    Log.i("siuuu", "suiiii ${responseBody?.data} ${dataUser.value?.name}")
                    if (responseBody != null) {
                        _dataUser.value = responseBody.data
//                        val newArticleList =
//                            _listArticle.value?.plus(responseBody.data) ?: responseBody.data
//                        _listArticle.postValue(newArticleList)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
                _isLoading.value = false
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "ProfileViewModel"
    }

}