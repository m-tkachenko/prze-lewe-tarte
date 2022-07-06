package app.salo.przelewetarte.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.salo.przelewetarte.presentation.Screen

@Composable
fun ProfileSnakeImage(
    imageId: Int,
    onSnakeClick: () -> Unit,
    imageSize: Dp,
    borderWidth: Dp
) {
    Image(
        painter = painterResource(id = imageId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(imageSize)
            .clip(CircleShape)
            .border(
                width = borderWidth,
                color = Color(0xFF14261F),
                shape = CircleShape
            )
            .clickable {
                onSnakeClick.invoke()
            }
    )
}