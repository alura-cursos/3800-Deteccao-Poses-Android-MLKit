package com.alura.mexeai.ui.screens.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alura.mexeai.R
import com.alura.mexeai.ui.components.BoxBackground
import com.alura.mexeai.ui.components.RoundButton
import com.alura.mexeai.ui.theme.Orbitron
import com.alura.mexeai.ui.theme.Rubik
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToCamera: () -> Unit
) {
    val viewModel = hiltViewModel<HomeScreenViewModel>()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.showTutorial) {
        if (state.showTutorial) {
            delay(3.seconds)
            onNavigateToCamera()
            viewModel.changeShowTutorial(false)
        }
    }

    BoxBackground {
        Crossfade(targetState = state.showTutorial, label = "Tutorial") { showTutorial ->
            if (!showTutorial) {
                Column(
                    modifier = modifier
                        .wrapContentSize(Alignment.Center)
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.mexeai_text_logo),
                        contentDescription = "App Logo",
                        modifier = Modifier.size(200.dp)
                    )

                    Button(
                        onClick = { viewModel.changeShowTutorial(true) },
                        modifier = Modifier.size(200.dp),
                        border = BorderStroke(
                            width = 2.dp,
                            color = Color(0xFFCC0309),

                            ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF400001),
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "INICIAR",
                            color = Color.White,
                            fontSize = 32.sp,
                            fontFamily = Rubik,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Spacer(modifier = Modifier.size(42.dp))

                    Text(
                        text = "${state.highScore}",
                        color = Color.White,
                        fontSize = 42.sp,
                        fontFamily = Orbitron,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "recorde",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontFamily = Orbitron
                    )
                    Spacer(modifier = Modifier.size(32.dp))


                    RoundButton("Câmera: ${stringResource(state.currentCameraName)}") {
                        viewModel.changeCamera()
                    }
                }
            } else {
                TutorialScreen(modifier)
            }

        }
    }
}

@Composable
private fun TutorialScreen(
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .wrapContentSize(Alignment.Center)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.mexeai_text_logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(200.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.people_rise_hands),
            contentDescription = "App Logo",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.size(56.dp))

        Text(
            text = "Na próxima tela, levante os braços para iniciar o jogo!",
            color = Color.White,
            fontSize = 22.sp,
            fontFamily = Rubik,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(32.dp))

        CircularProgressIndicator(
            color = Color.White,
        )
    }
}


