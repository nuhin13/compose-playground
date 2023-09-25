package com.nuhin13.composeplayground.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ScreensViewModel : ViewModel() {
    private val _name = MutableLiveData("")

    val name: LiveData<String>
        get() = _name

    fun setName(name: String) {
        viewModelScope.launch {
            _name.value = name
        }
    }
}