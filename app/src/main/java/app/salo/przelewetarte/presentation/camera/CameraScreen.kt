package app.salo.przelewetarte.presentation.camera

import android.Manifest
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.salo.przelewetarte.presentation.Screen
import app.salo.przelewetarte.presentation.camera.components.SecondCameraDialog
import app.salo.przelewetarte.presentation.camera.components.SecondCameraView
import app.salo.przelewetarte.presentation.components.SadTopBar
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(
    cameraViewModel: CameraViewModel = hiltViewModel(),
    navController: NavController
) {
    val permissionState = rememberPermissionState(Manifest.permission.CAMERA)
    val addPhotoState = cameraViewModel.addPhotoState.value

    LaunchedEffect(Unit) {
        if (!permissionState.hasPermission) {
            permissionState.launchPermissionRequest()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            SadTopBar(
                navController = navController,
                mainText = "Camera",
                route = Screen.ProfileScreen.route
            )

            SecondCameraView(
                onImageCaptured = { uri ->
                    Log.d("Camera", "$uri")

                    cameraViewModel.addPhoto(
                        photoUri = uri
                    )
                },
                onError = {
                    Log.e("Camera", "I hope not")
                }
            )

            if (addPhotoState.isLoading) {
                SecondCameraDialog()
            }
            if (addPhotoState.isSuccess) {
                LaunchedEffect(Unit) {
                    navController.navigate(Screen.LessonScreen.route + "/id1")
                }
            }
            if (addPhotoState.error.isNotBlank()) {
                Toast.makeText(
                    LocalContext.current,
                    addPhotoState.error,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}