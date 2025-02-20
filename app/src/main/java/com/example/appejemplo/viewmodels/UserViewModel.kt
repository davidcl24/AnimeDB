package com.example.appejemplo.viewmodels

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    private val _state = MutableLiveData<UiState>()
    val state : LiveData<UiState> get() = _state

    fun updatePhotoUri(uri: Uri?) {
        _state.value?.photoUri = uri
    }


    data class UiState(
        val loading:Boolean = true,
        var photoUri: Uri? = null,
        val error: String? = null
    )
}