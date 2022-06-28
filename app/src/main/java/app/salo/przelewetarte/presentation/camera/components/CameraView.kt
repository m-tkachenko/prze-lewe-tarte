package app.salo.przelewetarte.presentation.camera.components

import android.net.Uri
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SecondCameraView(
    onImageCaptured: (Uri) -> Unit,
    onError: (ImageCaptureException) -> Unit
) {
    val context = LocalContext.current
    val imageCapture: ImageCapture = remember {
        ImageCapture.Builder().build()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        SecondCameraPreviewView(
            imageCapture = imageCapture,
            cameraAction = {
                imageCapture.takePicture(context, onImageCaptured, onError)
            }
        )
    }
}