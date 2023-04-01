package com.alertbook.alertbook.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alertbook.alertbook.data.DataStoreRepo
import com.alertbook.alertbook.navigation.Screen
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val repository: DataStoreRepo
) : ViewModel() {

    /*
    private val mutableStateFlow = MutableStateFlow(true)
    val isLoading = mutableStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            mutableStateFlow.value = false
        }
    }

     */


    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Screen.Intro.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            repository.readIntroState().collect { completed ->
                if (completed) {
                    _startDestination.value = Screen.DashBoard.route
                } else {
                    _startDestination.value = Screen.Intro.route
                }
            }
            _isLoading.value = false
        }
    }

}