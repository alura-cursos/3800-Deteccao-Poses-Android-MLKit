package com.alura.mexeai.ui.components

import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alura.mexeai.ui.screens.pose.drawCustomPoint


@Composable
fun HandPoints(
    currentLeftHandPosition: PointF,
    currentRightHandPosition: PointF,
    postScaleWidthOffset: Float,
    scaleFactor: Float
) {
    HandPointDraw(
        position = currentLeftHandPosition,
        scaleFactor = scaleFactor,
        postScaleWidthOffset = postScaleWidthOffset,
    )

    HandPointDraw(
        position = currentRightHandPosition,
        scaleFactor = scaleFactor,
        postScaleWidthOffset = postScaleWidthOffset,
    )
}

@Composable
private fun HandPointDraw(
    position: PointF,
    scaleFactor: Float,
    postScaleWidthOffset: Float,
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        this.drawCustomPoint(
            position,
            scaleFactor = scaleFactor,
            postScaleWidthOffset = postScaleWidthOffset,
            radius = 60f
        )
    }
}
