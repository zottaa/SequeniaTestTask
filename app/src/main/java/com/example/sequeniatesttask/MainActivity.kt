package com.example.sequeniatesttask

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.presentation.screens.details.FilmDetailsScreen
import com.example.presentation.screens.list.FilmsListScreen
import com.example.presentation.theme.SequeniaTestTaskTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        configureStatusBar()
    }

    private fun configureStatusBar() {
        this.window.statusBarColor = getColor(R.color.status_bar_color)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = this.window.insetsController
            controller?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else {
            this.window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
    }
}