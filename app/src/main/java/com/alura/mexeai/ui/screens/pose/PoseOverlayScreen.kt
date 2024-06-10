package com.alura.mexeai.ui.screens.pose

import android.graphics.PointF
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alura.mexeai.ui.components.HandPoints
import com.alura.mexeai.ui.components.RandomPoint
import com.alura.mexeai.ui.components.ScoreTimeDisplay
import com.alura.mexeai.ui.screens.game.EndGameScreen
import com.alura.mexeai.utils.PoseUtils.generateRandomPoint
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun PoseOverlayScreen(
    scaleFactor: Float,
    postScaleWidthOffset: Float,
    imgSize: Pair<Float, Float>,
    modifier: Modifier = Modifier,
) {
    val startGame = false
    var currentScore by remember { mutableIntStateOf(0) }
    var currentTime by remember { mutableIntStateOf(90) }
    var resetGame by remember { mutableStateOf(false) }
    var inGame by remember { mutableStateOf(false) }
    val isEndGame = currentTime == 0

    if (startGame || inGame) {
        LaunchedEffect(currentTime) {
            if (currentTime > 0) {
                inGame = true
                delay(1.seconds)
                currentTime--
            } else {
                inGame = false
            }
        }
    }
    if (resetGame) {
        currentTime = 90
        currentScore = 0
        resetGame = false
    }

    var randomPoint by remember { mutableStateOf(generateRandomPoint(imgSize)) }
    var collisionDetected by remember { mutableStateOf(false) }
    var leftHandPosition by remember { mutableStateOf(PointF(0f, 0f)) }
    var rightHandPosition by remember { mutableStateOf(PointF(0f, 0f)) }

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxSize()
            .border(
                width = 5.dp,
                color = Color.Red.copy(if (collisionDetected && inGame) 0.8f else 0f),
                shape = RoundedCornerShape(100f)
            )
    ) {

        LaunchedEffect(collisionDetected) {
            if (collisionDetected && inGame) {
                randomPoint = generateRandomPoint(imgSize)
                currentScore += 1
            }
        }


        if (inGame) {
            RandomPoint(randomPoint, scaleFactor, postScaleWidthOffset)
        }

        ScoreTimeDisplay(currentScore, currentTime, inGame, modifier)
        EndGameScreen(currentScore, isEndGame, modifier)
        HandPoints(
            leftHandPosition,
            rightHandPosition,
            postScaleWidthOffset,
            scaleFactor
        )
    }
}
