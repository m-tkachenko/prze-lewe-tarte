package app.salo.przelewetarte.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun BorderlandsImageCard(
    photo: Any?,
    height: Dp,
    allPadding: Dp = 0.dp,
    horizontalPadding: Dp = 0.dp
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.onPrimary,
        modifier = Modifier
            .height(height)
            .padding(all = allPadding)
            .padding(horizontal = horizontalPadding)
            .border(
                width = 4.dp,
                color = Color.Black,
                shape = RoundedCornerShape(8.dp)
            ),
        elevation = 5.dp
    ) {
        AsyncImage(
            model = photo,
            contentDescription = "image",
            contentScale = ContentScale.Crop
        )
    }
}