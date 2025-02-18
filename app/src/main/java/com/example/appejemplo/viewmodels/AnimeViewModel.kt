package com.example.appejemplo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appejemplo.api.MALRepository.animesService
import com.example.appejemplo.models.Anime
import kotlinx.coroutines.launch

class AnimeViewModel : ViewModel() {
    private val _state = MutableLiveData<UiState>()
    val state : LiveData<UiState> get() = _state

    init {
        _state.value = UiState(loading = true)
        fetchAnimes()
    }

    private fun fetchAnimes() {
        viewModelScope.launch {
            try {
                val response = animesService.getAllAnime()
                _state.value = _state.value?.copy(
                    list = response.animes,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _state.value = _state.value?.copy(
                    loading = false,
                    error = e.message
                )
            }
        }
    }


    data class UiState(
        val loading:Boolean = true,
        val list: List<Anime> = emptyList(),
        val error: String? = null
    )
}