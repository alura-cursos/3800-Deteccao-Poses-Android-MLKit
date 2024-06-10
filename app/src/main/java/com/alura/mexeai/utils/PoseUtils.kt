package com.alura.mexeai.utils

import android.graphics.PointF
import com.google.mlkit.vision.pose.PoseLandmark
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.sqrt
import kotlin.random.Random

object PoseUtils {
    fun generateRandomPoint(
        imgSize: Pair<Float, Float>
    ): PointF {
        val widthSecurityMargin = imgSize.first - 100
        val heightSecurityMargin = imgSize.second - 50

        val randomX = Random.nextInt(100, widthSecurityMargin.toInt()).toFloat()
        val randomY = Random.nextInt(100, heightSecurityMargin.toInt()).toFloat()

        return PointF(randomX, randomY)
    }

    fun isCollision(
        firstPoint: PointF,
        secondPoint: PointF,
        threshold: Float = 40f
    ): Boolean {
        val dx = firstPoint.x - secondPoint.x
        val dy = firstPoint.y - secondPoint.y
        val distance = sqrt(dx * dx + dy * dy)
        return distance <= threshold
    }

    fun getAngle(
        firstPoint: PoseLandmark?,
        midPoint: PoseLandmark?,
        lastPoint: PoseLandmark?
    ): Double {
        if (firstPoint == null || midPoint == null || lastPoint == null) {
            return 0.0
        }

        val angle1 = atan2(
            lastPoint.position.y - midPoint.position.y,
            lastPoint.position.x - midPoint.position.x
        )
        val angle2 = atan2(
            firstPoint.position.y - midPoint.position.y,
            firstPoint.position.x - midPoint.position.x
        )
        var finalAngle = Math.toDegrees((angle1 - angle2).toDouble())
        finalAngle = abs(finalAngle)

        if (finalAngle > 180) {
            finalAngle = 360.0 - finalAngle
        }
        return finalAngle
    }
}