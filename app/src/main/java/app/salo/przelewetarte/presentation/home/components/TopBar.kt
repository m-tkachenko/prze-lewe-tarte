package app.salo.przelewetarte.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.salo.przelewetarte.R
import app.salo.przelewetarte.presentation.components.LeweProgressBar

@Composable
fun FunnyTopBar(
    username: String,
    imageId: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = MaterialTheme.colors.background)
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Hello, ${username}!",
                fontFamily = MaterialTheme.typography.body1.fontFamily,
                fontSize = 24.sp,
                color = Color(0xFF14261F)
            )

            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = CircleShape
                    )
            )
        }

        Text(
            text = "Overall Progress",
            fontFamily = MaterialTheme.typography.body1.fontFamily,
            fontSize = 20.sp,
            color = Color(0xFF14261F),
            modifier = Modifier
                .padding(bottom = 8.dp)
        )

        LeweProgressBar(
            progress = 0.3f,
            width = 0.65f
        )

    }
}