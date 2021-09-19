package com.example.myapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.ApiClient
import com.example.myapplication.network.SearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainViewModel(private val repository: Repository = Repository(ApiClient.apiService)) :
    ViewModel() {

    private var _movieListLiveData: MutableLiveData<SearchResponse> = MutableLiveData()
    val movieListLiveData: LiveData<SearchResponse>
        get() = _movieListLiveData

    private val apiKey = "320f6ab2"

    init {
        searchMovies("star wars")
    }

    fun searchMovies(searchText:String) {
        Log.e("searchMovies()", "")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val client = repository.searchMovies(searchText, apiKey)
                _movieListLiveData.postValue(client)
            } catch (throwable: Throwable) {
                Log.e("getFilmMovies", throwable.toString())
                handleThrowable(throwable)
            }
        }
    }

    private fun handleThrowable(throwable: Throwable) {
        when (throwable) {
            is IOException -> Log.e("handleThrowable", "NetworkError")
            is HttpException -> {
                val code = throwable.code()
                val errorMessage = throwable.message()
                Log.e("handleThrowable", "HttpException, code: $code, message: $errorMessage")
            }
            else -> {
                Log.e("handleThrowable", throwable.message.toString())
            }
        }
    }

}