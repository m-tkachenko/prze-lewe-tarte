package app.salo.przelewetarte.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LeweProgressBar(
    progress: Float = 0.001f,
    width: Float = 0.9f
) {
    if (progress < 0.01) {
        LinearProgressIndicator(
            backgroundColor = Color(0xFFF1F1F1),
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .fillMaxWidth(width)
                .height(32.dp)
                .background(
                    color = Color(0xFFF1F1F1),
                    shape = RoundedCornerShape(3.dp)
                )
                .border(
                    width = 2.dp,
                    color = Color(0xFF14261F),
                    shape = RoundedCornerShape(3.dp)
                )
        )
    }
    else {
        LinearProgressIndicator(
            progress = progress,
            backgroundColor = Color(0xFFF1F1F1),
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .fillMaxWidth(width)
                .height(32.dp)
                .background(
                    color = Color(0xFFF1F1F1),
                    shape = RoundedCornerShape(4.dp)
                )
                .border(
                    width = 2.dp,
                    color = Color(0xFF14261F),
                    shape = RoundedCornerShape(4.dp)
                )
        )
    }
}