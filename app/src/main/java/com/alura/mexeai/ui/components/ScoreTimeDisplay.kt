package com.alura.mexeai.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alura.mexeai.R
import com.alura.mexeai.extensions.toTimeString
import com.alura.mexeai.ui.theme.Rubik
import kotlinx.coroutines.delay


@Composable
fun ScoreTimeDisplay(
    currentScore: Int,
    currentTime: Int,
    inGame: Boolean,
    modifier: Modifier = Modifier
) {
    var showAlert by remember { mutableStateOf(false) }
    var showBorder by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .border(
                width = if (showBorder) 5.dp else 0.dp,
                color = Color.Black.copy(if (showBorder) 0.8f else 0f),
                shape = RoundedCornerShape(100f),
            )
            .animateContentSize()
    ) {
        Column(
            modifier = Modifier
                .background(
                    Color.Black.copy(0.8f),
                    RoundedCornerShape(0f, 0f, 100f, 100f)
                )
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_radio_button),
                        contentDescription = "Points",
                        modifier = Modifier.size(32.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "$currentScore",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Rubik
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_timer),
                        contentDescription = "Time",
                        modifier = Modifier.size(28.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = currentTime.toTimeString(),
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Rubik
                    )
                }
            }

            if (inGame) {
                LaunchedEffect(Unit) {
                    showAlert = true
                    showBorder = true
                    delay(500)
                    showBorder = false
                    delay(1000)
                    showAlert = false
                }

                AnimatedVisibility(
                    visible = showAlert,
                    enter = slideInVertically(
                        initialOffsetY = { -it },
                        animationSpec = tween(durationMillis = 500)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Começou!",
                            color = Color.Yellow,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = Rubik,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScoreTimeDisplayPreview() {
    ScoreTimeDisplay(42, 90, true)
}


