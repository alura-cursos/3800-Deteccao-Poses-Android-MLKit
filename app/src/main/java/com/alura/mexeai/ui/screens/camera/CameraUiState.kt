package com.alura.mexeai.ui.screens.camera

data class CameraUiState(
    val imageSize: Pair<Float, Float> = Pair(480f, 640f),
    val previewSize: Pair<Float, Float> = Pair(0f, 0f),
    val postScaleWidthOffset: Float = 0f,
    val imageAspectRatio: Float = 0f,
    val scaleFactor: Float = 0f,
    val useFrontalCamera: Boolean = true,
    val currentScaleX: Float? = null,
)
