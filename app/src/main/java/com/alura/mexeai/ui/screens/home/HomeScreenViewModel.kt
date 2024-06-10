package com.alura.mexeai.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alura.mexeai.R
import com.alura.mexeai.dataStore.UserPreferencesDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val dataStore: UserPreferencesDataStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState>
        get() = _uiState.asStateFlow()

    init {
        loadScoreState()
        loadCameraState()
    }

    private fun loadScoreState() {
        viewModelScope.launch {
            dataStore.getHighScore().collect { highScore ->
                _uiState.value = _uiState.value.copy(
                    highScore = highScore
                )
            }
        }
    }

    private fun loadCameraState() {
        viewModelScope.launch {
            dataStore.getUseFrontalCamera().collect {
                it.let { useFrontalCamera ->
                    _uiState.value = _uiState.value.copy(
                        useFrontalCamera = useFrontalCamera,
                        currentCameraName = if (useFrontalCamera) R.string.front_camera else R.string.back_camera
                    )
                }
            }
        }
    }

    fun changeCamera() {
        viewModelScope.launch {
            dataStore.saveUseFrontalCamera(!_uiState.value.useFrontalCamera)
            loadCameraState()
        }
    }

    fun changeShowTutorial(value: Boolean) {
        _uiState.value = _uiState.value.copy(
            showTutorial = value
        )
    }
}