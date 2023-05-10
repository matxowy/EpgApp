package co.proexe.presentation.common


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import co.proexe.presentation.common.theme.White

@Composable
fun CircularLoadingIndicator(
    modifier: Modifier = Modifier,
    color: Color = White
) {
    Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
        CircularProgressIndicator(color = color)
    }
}