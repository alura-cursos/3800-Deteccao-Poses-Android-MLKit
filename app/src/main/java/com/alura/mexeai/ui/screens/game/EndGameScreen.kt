package com.alura.mexeai.ui.screens.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alura.mexeai.R
import com.alura.mexeai.ui.components.BoxBackground
import com.alura.mexeai.ui.components.RoundButton
import com.alura.mexeai.ui.theme.Rubik


@Composable
fun EndGameScreen(
    currentScore: Int,
    isEndGame: Boolean,
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<GameViewModel>()
    val state by viewModel.uiState.collectAsState()

    if (isEndGame) {
        viewModel.onEndGame(currentScore)
        BoxBackground(
            modifier = modifier
        ) {
            Column(
                modifier = Modifier
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
                    painter = painterResource(id = R.drawable.people_touch_hands),
                    contentDescription = "App Logo",
                    modifier = Modifier.size(200.dp)
                )
                Spacer(modifier = Modifier.size(56.dp))

                Text(
                    text = "Junte as mãos para reiniciar o jogo!",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontFamily = Rubik,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(32.dp))


                RoundButton("Pontuação atual: $currentScore")
                Spacer(modifier = Modifier.padding(20.dp))
                RoundButton(
                    "Recorde: ${state.highScore}",
                    backgroundColor = Color(0xFF400001),
                    borderColor = Color(0xFFCC0309),
                )
            }
        }
    }
}
