package app.salo.przelewetarte.presentation.camera

import android.Manifest
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.salo.przelewetarte.presentation.camera.components.SecondCameraView
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(
    profileViewModel: CameraViewModel = hiltViewModel(),
    navController: NavController
) {
    val permissionState = rememberPermissionState(Manifest.permission.CAMERA)
    var uriText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        if (!permissionState.hasPermission) {
            permissionState.launchPermissionRequest()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SecondCameraView(
            onImageCaptured = { uri, b ->
                uriText = uri.toString()
            },
            onError = {
                Log.d("Camera", "I hope not")
            }
        )

        Text(text = uriText)
    }
}