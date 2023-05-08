package com.menesdurak.appterncasestudy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menesdurak.appterncasestudy.data.model.Genre
import com.menesdurak.appterncasestudy.data.remote.RetrofitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeezerViewModel @Inject constructor(private val retrofitRepository: RetrofitRepository) : ViewModel() {

    private val _genreList: MutableLiveData<Genre> = MutableLiveData()
    val genreList: LiveData<Genre>
        get() = _genreList

    fun getGenres() {
        viewModelScope.launch {
            _genreList.value = retrofitRepository.getGenres()
        }
    }
}