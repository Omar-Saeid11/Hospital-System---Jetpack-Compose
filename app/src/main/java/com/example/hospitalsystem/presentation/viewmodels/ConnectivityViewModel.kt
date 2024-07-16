package com.example.hospitalsystem.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.presentation.connectivity.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConnectivityViewModel @Inject constructor(
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {

    private val _isConnected = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch {
            connectivityObserver.observe().collect { isConnected ->
                _isConnected.value = isConnected
            }
        }
    }

    val isConnected: LiveData<Boolean>
        get() = _isConnected
}
