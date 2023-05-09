package co.proexe.presentation.common.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import co.proexe.R

object AppFonts {
    val poppins = FontFamily(
        Font(
            resId = R.font.poppins_extralight,
            style = FontStyle.Normal,
            weight = FontWeight.W300,
        ),
        Font(
            resId = R.font.poppins_bold,
            style = FontStyle.Normal,
            weight = FontWeight.W600,
        )
    )
}