package co.proexe.presentation.common.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object AppTypography {
    val appTitle = TextStyle(
        color = Silver,
        fontFamily = AppFonts.poppins,
        fontWeight = FontWeight.W600,
        fontSize = 17.sp
    )
    val programmeTitle = TextStyle(
        color = Silver,
        fontFamily = AppFonts.poppins,
        fontWeight = FontWeight.W600,
        fontSize = 14.sp
    )
    val programmeDescription = TextStyle(
        color = Silver,
        fontFamily = AppFonts.poppins,
        fontWeight = FontWeight.W300,
        fontSize = 12.sp
    )
    val dayLabel = TextStyle(
        color = Silver,
        fontFamily = AppFonts.poppins,
        fontWeight = FontWeight.W600,
        fontSize = 12.sp
    )
}