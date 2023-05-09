package com.menesdurak.appterncasestudy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menesdurak.appterncasestudy.data.model.Album
import com.menesdurak.appterncasestudy.data.model.Artist
import com.menesdurak.appterncasestudy.data.model.Genre
import com.menesdurak.appterncasestudy.data.model.Track
import com.menesdurak.appterncasestudy.data.remote.RetrofitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeezerViewModel @Inject constructor(private val retrofitRepository: RetrofitRepository) : ViewModel() {

    private val _genreList: MutableLiveData<Genre> = MutableLiveData()
    val genreList: LiveData<Genre>
        get() = _genreList

    private val _artistList: MutableLiveData<Artist> = MutableLiveData()
    val artistList: LiveData<Artist>
        get() = _artistList

    private val _albumList: MutableLiveData<Album> = MutableLiveData()
    val albumList: LiveData<Album>
        get() = _albumList

    private val _trackList: MutableLiveData<Track> = MutableLiveData()
    val trackList: LiveData<Track>
        get() = _trackList

    fun getGenres() {
        viewModelScope.launch {
            _genreList.value = retrofitRepository.getGenres()
        }
    }

    fun getArtists(genreId: Int) {
        viewModelScope.launch {
            _artistList.value = retrofitRepository.getArtists(genreId)
        }
    }

    fun getAlbums(artistId: Int) {
        viewModelScope.launch {
            _albumList.value = retrofitRepository.getAlbums(artistId)
        }
    }

    fun getTracks(albumId: Int) {
        viewModelScope.launch {
            _trackList.value = retrofitRepository.getTracks(albumId)
        }
    }
}