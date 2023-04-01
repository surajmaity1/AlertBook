package com.alertbook.alertbook.utils

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.alertbook.alertbook.R
import com.alertbook.alertbook.ui.theme.ColorBlue
import com.alertbook.alertbook.ui.theme.ColorGreen
import com.alertbook.alertbook.ui.theme.ColorRed


sealed class IntroPage(
    @DrawableRes
    val img: Int,
    val title: String,
    val info: String,
    val bgColor: Color,
    val mainColor: Color
){
    object First : IntroPage(
        img = R.drawable.one,
        title = "Welcome Here …",
        info = "Your greatest resource is your time …",
        bgColor = Color(0xFFE91E63),
        mainColor = ColorRed
    )
    object Second : IntroPage(
        img = R.drawable.two,
        title = "What We Do …",
        info = "Give me six hours to chop down a tree and I will spend the first four sharpening the axe …",
        bgColor = Color(0xFF96E172),
        mainColor = ColorGreen
    )
    object Third : IntroPage(
        img = R.drawable.three,
        title = "Make Your Life Easy …",
        info = "You can only manage time if you track it right …",
        bgColor = Color(0xFF0189C5),
        mainColor = ColorBlue
    )
}