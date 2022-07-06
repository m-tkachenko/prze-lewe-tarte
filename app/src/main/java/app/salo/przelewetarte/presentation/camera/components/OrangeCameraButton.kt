package app.salo.przelewetarte.presentation.camera.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OrangeCameraButton(
    onCameraClick: () -> Unit
) {
    var clickedOnce = false

    Box( modifier = Modifier
        .size(70.dp)
        .clip(CircleShape)
        .background(MaterialTheme.colors.secondary)
        .border(
            width = 3.dp,
            shape = CircleShape,
            color = Color(0xFF14261F)
        )
        .clickable {
            if (!clickedOnce) {
                onCameraClick.invoke()
                clickedOnce = true
            }
        }
    )
}