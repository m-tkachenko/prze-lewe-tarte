package app.salo.przelewetarte.presentation.camera

import android.Manifest
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.salo.przelewetarte.presentation.Screen
import app.salo.przelewetarte.presentation.camera.components.SecondCameraView
import app.salo.przelewetarte.presentation.components.LeweProgressBar
import app.salo.przelewetarte.presentation.components.SadTopBar
import app.salo.przelewetarte.presentation.home.components.FunnyTopBar
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
                Dialog(
                    onDismissRequest = {},
                    properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
                ) {
                    Box(
                        contentAlignment= Center,
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