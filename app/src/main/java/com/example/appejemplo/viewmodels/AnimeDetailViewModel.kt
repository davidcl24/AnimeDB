package com.example.appejemplo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.appejemplo.api.MALRepository.animesService
import com.example.appejemplo.db.AnimeDao
import com.example.appejemplo.models.Anime
import kotlinx.coroutines.launch

class AnimeDetailViewModel(private val animeDao: AnimeDao) : ViewModel() {
    private val _state = MutableLiveData<UiState>()
    val state : LiveData<UiState> get() = _state

    init {
        _state.value = UiState(loading = true)
    }

    fun fetchAnimeById(id: Int) {
        viewModelScope.launch {
            try {
                val response = animesService.getAnimeById(id)
                _state.value = _state.value?.copy(
                    anime = response.anime,
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

    fun addAnimeToFav() {
        viewModelScope.launch {
            try {
                _state.value?.anime?.let { anime -> animeDao.insertAnime(anime) }
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
        val anime: Anime? = null,
        val error: String? = null
    )
}

@Suppress("UNCHECKED_CAST")
class AnimeDetailViewModelFactory(private val animeDao: AnimeDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimeDetailViewModel(animeDao) as T
    }
}