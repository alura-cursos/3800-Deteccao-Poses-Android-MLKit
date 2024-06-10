package com.alura.mexeai.ui.components

import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.alura.mexeai.ui.screens.pose.drawCustomPoint

@Composable
fun RandomPoint(
    randomPoint: PointF,
    scaleFactor: Float,
    postScaleWidthOffset: Float
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        this.drawCustomPoint(
            randomPoint,
            scaleFactor = scaleFactor,
            postScaleWidthOffset = postScaleWidthOffset,
            centerColor = Color(0xFFFF1641),
            strokeColor = Color(0xFFFF1641),
            radius = 50f
        )
    }
}