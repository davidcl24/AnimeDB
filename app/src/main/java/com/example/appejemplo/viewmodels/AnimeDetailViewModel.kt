package com.example.appejemplo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appejemplo.models.Anime

class AnimeDetailViewModel : ViewModel() {
    private val _state = MutableLiveData<UiState>()
    val state : LiveData<UiState> get() = _state



    data class UiState(
        val loading:Boolean = true,
        val anime: Anime? = null,
        val error: String? = null
    )
}