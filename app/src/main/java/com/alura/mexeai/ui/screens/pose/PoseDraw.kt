package com.alura.mexeai.ui.screens.pose

import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseLandmark

@Composable
fun PoseDraw(
    pose: Pose,
    scaleFactor: Float,
    extraOffset: Float,
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val poseLandmarks = pose.allPoseLandmarks
        if (poseLandmarks.isEmpty()) return@Canvas

        poseLandmarks.forEach {
            this.drawPoint(it.position, scaleFactor, extraOffset)
        }

        val landmarkPairs = listOf(
            PoseLandmark.LEFT_SHOULDER to PoseLandmark.RIGHT_SHOULDER,
            PoseLandmark.LEFT_HIP to PoseLandmark.RIGHT_HIP,
            PoseLandmark.LEFT_SHOULDER to PoseLandmark.LEFT_ELBOW,
            PoseLandmark.LEFT_ELBOW to PoseLandmark.LEFT_WRIST,
            PoseLandmark.LEFT_SHOULDER to PoseLandmark.LEFT_HIP,
            PoseLandmark.LEFT_HIP to PoseLandmark.LEFT_KNEE,
            PoseLandmark.LEFT_KNEE to PoseLandmark.LEFT_ANKLE,
            PoseLandmark.LEFT_WRIST to PoseLandmark.LEFT_THUMB,
            PoseLandmark.LEFT_WRIST to PoseLandmark.LEFT_PINKY,
            PoseLandmark.LEFT_WRIST to PoseLandmark.LEFT_INDEX,
            PoseLandmark.LEFT_INDEX to PoseLandmark.LEFT_PINKY,
            PoseLandmark.LEFT_ANKLE to PoseLandmark.LEFT_HEEL,
            PoseLandmark.LEFT_HEEL to PoseLandmark.LEFT_FOOT_INDEX,
            PoseLandmark.RIGHT_SHOULDER to PoseLandmark.RIGHT_ELBOW,
            PoseLandmark.RIGHT_ELBOW to PoseLandmark.RIGHT_WRIST,
            PoseLandmark.RIGHT_SHOULDER to PoseLandmark.RIGHT_HIP,
            PoseLandmark.RIGHT_HIP to PoseLandmark.RIGHT_KNEE,
            PoseLandmark.RIGHT_KNEE to PoseLandmark.RIGHT_ANKLE,
            PoseLandmark.RIGHT_WRIST to PoseLandmark.RIGHT_THUMB,
            PoseLandmark.RIGHT_WRIST to PoseLandmark.RIGHT_PINKY,
            PoseLandmark.RIGHT_WRIST to PoseLandmark.RIGHT_INDEX,
            PoseLandmark.RIGHT_INDEX to PoseLandmark.RIGHT_PINKY,
            PoseLandmark.RIGHT_ANKLE to PoseLandmark.RIGHT_HEEL,
            PoseLandmark.RIGHT_HEEL to PoseLandmark.RIGHT_FOOT_INDEX

        )

        landmarkPairs.forEach { (startLandmarkType, endLandmarkType) ->
            val startLandmark = pose.getPoseLandmark(startLandmarkType)
            val endLandmark = pose.getPoseLandmark(endLandmarkType)
            drawLine(scaleFactor, extraOffset, startLandmark, endLandmark)
        }
    }
}


fun DrawScope.drawLine(
    scaleFactor: Float,
    extraOffset: Float,
    startLandmark: PoseLandmark?,
    endLandmark: PoseLandmark?,
    color: Color = Color.White
) {
    if (startLandmark == null || endLandmark == null) return

    val start = startLandmark.position3D
    val end = endLandmark.position3D

    this.drawLine(
        start = Offset(
            setScale(start.x, scaleFactor, extraOffset),
            setScale(start.y, scaleFactor)
        ),
        end = Offset(
            setScale(end.x, scaleFactor, extraOffset),
            setScale(end.y, scaleFactor)
        ),
        color = color,
        strokeWidth = STROKE_WIDTH
    )
}

fun DrawScope.drawPoint(
    coordinates: PointF,
    scaleFactor: Float,
    postScaleWidthOffset: Float,
    color: Color = Color.White
) {
    this.drawCircle(
        center = Offset(
            setScale(coordinates.x, scaleFactor, postScaleWidthOffset),
            setScale(coordinates.y, scaleFactor)
        ),
        radius = DOT_RADIUS,
        color = color
    )
}

fun DrawScope.drawCustomPoint(
    coordinates: PointF,
    scaleFactor: Float,
    postScaleWidthOffset: Float,
    centerColor: Color = Color.White,
    strokeColor: Color = Color.Blue,
    radius: Float = DOT_RADIUS
) {

    this.drawCircle(
        center = Offset(
            setScale(coordinates.x, scaleFactor, postScaleWidthOffset),
            setScale(coordinates.y, scaleFactor)
        ),
        radius = radius,
        color = centerColor,
    )

    this.drawCircle(
        center = Offset(
            setScale(coordinates.x, scaleFactor, postScaleWidthOffset),
            setScale(coordinates.y, scaleFactor)
        ),
        radius = radius,
        color = strokeColor,
        alpha = 0.5f,
        style = Stroke(
            width = 25f,
            cap = StrokeCap.Round,
            join = StrokeJoin.Round,
            pathEffect = PathEffect.chainPathEffect(
                PathEffect.dashPathEffect(floatArrayOf(10f, 5f), 0f),
                PathEffect.cornerPathEffect(10f)
            ),
        )
    )
}

fun setScale(
    coordinate: Float,
    scaleFactor: Float,
    extraOffset: Float = 0f
): Float {
    return coordinate * scaleFactor - extraOffset
}

private const val DOT_RADIUS = 8.0f
private const val STROKE_WIDTH = 10.0f


