package com.alura.mexeai.ui.screens.camera

import androidx.camera.core.CameraSelector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alura.mexeai.dataStore.UserPreferencesDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val dataStore: UserPreferencesDataStore,
) : ViewModel() {
    private val _uiState = MutableStateFlow(CameraUiState())
    var uiState = _uiState.asStateFlow()

    init {
        loadCameraState()
    }

    fun setPreviewSize(value: Pair<Int, Int>) {
        _uiState.value = _uiState.value.copy(
            previewSize = Pair(
                first = value.first.toFloat(),
                second = value.second.toFloat()
            )
        )
        redefineScale()
    }

    fun setScreenSize(value: Pair<Int, Int>) {
        _uiState.value = _uiState.value.copy(
            imageSize = Pair(
                first = value.first.toFloat(),
                second = value.second.toFloat()
            )
        )
        redefineScale()
    }

    private fun redefineScale() {
        with(uiState.value) {
            val scaleFactor: Float = previewSize.second / imageSize.second
            val imageAspectRatio: Float = imageSize.first / imageSize.second
            _uiState.value = _uiState.value.copy(
                scaleFactor = scaleFactor,
                imageAspectRatio = imageAspectRatio,
                postScaleWidthOffset = (previewSize.second * imageAspectRatio - previewSize.first) / 2
            )
        }
    }

    private fun loadCameraState() {
        viewModelScope.launch {
            dataStore.getUseFrontalCamera().collect {
                it.let { useFrontalCamera ->
                    _uiState.value = _uiState.value.copy(
                        useFrontalCamera = useFrontalCamera,
                        currentScaleX = if (useFrontalCamera) -1f else 1f
                    )
                }
            }
        }
    }

    fun getCameraSelector(): CameraSelector {
        return if (_uiState.value.useFrontalCamera) CameraSelector.DEFAULT_FRONT_CAMERA else CameraSelector.DEFAULT_BACK_CAMERA
    }
}