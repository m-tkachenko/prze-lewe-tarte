package app.salo.przelewetarte.presentation.camera.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import app.salo.przelewetarte.presentation.components.LeweProgressBar

@Composable
fun SecondCameraDialog() {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Box(
            contentAlignment= Alignment.Center,
            modifier = Modifier
                .size(230.dp)
                .background(
                    color = MaterialTheme.colors.background,
                    shape = RoundedCornerShape(5.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = 16.dp
                        ),
                    text = "Preparing your masterpiece",
                    textAlign = TextAlign.Center,
                    fontFamily = MaterialTheme.typography.body1.fontFamily,
                    fontSize = 20.sp,
                    color = Color(0xFF14261F),
                )

                LeweProgressBar(width = 0.8f)
            }
        }
    }
}