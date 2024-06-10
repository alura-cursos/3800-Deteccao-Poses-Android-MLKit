package com.alura.mexeai.ui.screens.home

import androidx.annotation.StringRes
import com.alura.mexeai.R

data class HomeScreenUiState(
    val highScore: Int = 0,
    val useFrontalCamera: Boolean = true,
    val showTutorial: Boolean = false,
    @StringRes val currentCameraName: Int = R.string.front_camera,
)
