package com.example.appejemplo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.appejemplo.db.AnimeDao
import com.example.appejemplo.models.Anime
import kotlinx.coroutines.launch

class FavViewModel(private val animeDao: AnimeDao) : ViewModel() {
    private val _state = MutableLiveData<UiState>()
    val state : LiveData<UiState> get() = _state

    init {
        _state.value = UiState(loading = true)
        fetchFavAnimes()
    }

    private fun fetchFavAnimes() {
        viewModelScope.launch {
            try {
                val response = animeDao.getAllAnime()
                _state.value = _state.value?.copy(
                    list = response.value ?: emptyList(),
                    loading = false,
                    error = if (response.value == null) "No tienes animes favoritos" else null
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

@Suppress("UNCHECKED_CAST")
class FavViewModelFactory(private val animeDao: AnimeDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavViewModel(animeDao) as T
    }
}