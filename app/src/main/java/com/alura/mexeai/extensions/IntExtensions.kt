package com.alura.mexeai.extensions

import java.util.Locale

fun Int.toTimeString(): String {
    val minutes = this / 60
    val remainingSeconds = this % 60
    return String.format(Locale.ROOT, "%02d:%02d", minutes, remainingSeconds)
}