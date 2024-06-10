package com.alura.mexeai.ui.screens.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alura.mexeai.dataStore.UserPreferencesDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val dataStore: UserPreferencesDataStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState>
        get() = _uiState.asStateFlow()

    init {
        loadScoreState()
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

    fun onEndGame(currentScore: Int) {
        viewModelScope.launch {
            val highScore = _uiState.value.highScore
            if (currentScore > highScore) {
                dataStore.saveHighScore(currentScore)
                _uiState.value = _uiState.value.copy(
                    highScore = currentScore
                )
            }
        }
    }
}