package com.alura.mexeai.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alura.mexeai.ui.theme.Rubik

@Composable
fun RoundButton(
    text: String,
    modifier: Modifier = Modifier,
    borderColor: Color = Color(0xFF007AFD),
    backgroundColor: Color = Color(0xFF0B003F),
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .background(
                backgroundColor,
                CircleShape
            )
            .border(
                width = 2.dp,
                color = borderColor,
                shape = CircleShape
            )
            .padding(24.dp, 16.dp)
            .clickable(onClick = onClick),
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 24.sp,
            fontFamily = Rubik,
            fontWeight = FontWeight.Medium
        )
    }
}