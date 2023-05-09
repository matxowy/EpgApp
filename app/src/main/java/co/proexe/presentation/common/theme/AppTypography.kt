package co.proexe.presentation.common.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import co.proexe.presentation.common.extensions.toSp

object AppTypography {
    val title = TextStyle(
        color = Silver,
        fontFamily = AppFonts.poppins,
        fontWeight = FontWeight.W300,
        fontSize = 17.toSp
    )
}