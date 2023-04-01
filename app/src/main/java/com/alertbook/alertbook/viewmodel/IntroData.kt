package com.alertbook.alertbook.viewmodel

import androidx.compose.ui.graphics.Color
import com.alertbook.alertbook.ui.theme.ColorGreen

data class IntroData(
    val img: Int,
    val title: String,
    val info: String,
    val bgColor:Color,
    val mainColor: Color = ColorGreen
)
