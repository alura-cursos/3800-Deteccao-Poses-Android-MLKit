package com.alura.mexeai

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.alura.mexeai.ui.screens.home.HomeNavHost
import com.alura.mexeai.ui.theme.MexeAITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var windowInsetsController: WindowInsetsControllerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MexeAITheme {
                WindowInsetsSetup()
                HomeNavHost(
                    onHideNavBar = { hideNavBar() }
                )
            }
        }
    }

    private fun hideNavBar() {
        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())
    }

    @Composable
    private fun WindowInsetsSetup() {
        val view = LocalView.current
        val window = (view.context as Activity).window
        windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
    }
}
