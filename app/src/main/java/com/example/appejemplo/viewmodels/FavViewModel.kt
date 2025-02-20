package com.example.appejemplo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.appejemplo.db.AnimeDao
import com.example.appejemplo.models.Anime
import kotlinx.coroutines.launch

class FavViewModel(private val animeDao: AnimeDao) : ViewModel() {
    private val _state = MutableLiveData<UiState>()
    val state : LiveData<UiState> get() = _state

    private val observer = Observer<List<Anime>> { list ->
        _state.value = UiState(
            loading = false,
            list = list ?: emptyList(),
            error = null
        )
    }

    init {
        _state.value = UiState(loading = true)
        animeDao.getAllAnime().observeForever(observer)
    }

    override fun onCleared() {
        super.onCleared()
        animeDao.getAllAnime().removeObserver(observer)
    }

    fun deleteFav(anime: Anime) = viewModelScope.launch {
        animeDao.deleteAnime(anime)
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