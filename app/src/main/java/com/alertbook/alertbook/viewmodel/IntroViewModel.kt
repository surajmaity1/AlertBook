package com.alertbook.alertbook.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alertbook.alertbook.data.DataStoreRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
    private val repository: DataStoreRepo
) : ViewModel() {

    fun saveIntroState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveIntroState(completed = completed)
        }
    }

}