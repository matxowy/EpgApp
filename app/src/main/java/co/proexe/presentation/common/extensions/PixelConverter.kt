package co.proexe.presentation.common.extensions

import android.content.res.Resources
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val Int.toDp get() = (this / Resources.getSystem().displayMetrics.density).dp
val Double.toDp get() = (this / Resources.getSystem().displayMetrics.density).dp

val Int.toSp get() = (this / Resources.getSystem().displayMetrics.density).sp
val Double.toSp get() = (this / Resources.getSystem().displayMetrics.density).sp
