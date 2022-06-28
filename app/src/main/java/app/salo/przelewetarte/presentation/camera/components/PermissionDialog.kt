package app.salo.przelewetarte.presentation.camera.components

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import app.salo.przelewetarte.presentation.components.OrangeDialog
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun DangerPermission(
    permission: String = android.Manifest.permission.CAMERA,
    content: @Composable () -> Unit = { }
) {
    val permissionState = rememberPermissionState(permission)
    val context = LocalContext.current

    PermissionRequired(
        permissionState = permissionState,
        permissionNotAvailableContent = {
            OrangeDialog(
                titleText = "Upsi",
                otherText = "Permission needed",
                textOnConfirmButton = "Ok",
                onConfirmClick = {
                    permissionState.launchPermissionRequest()
                })
        },
        permissionNotGrantedContent = {
            OrangeDialog(
                titleText = "Sorry",
                otherText = "Without permission i cant do that :(",
                textOnConfirmButton = "Settings",
                onConfirmClick = {
                    context.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts(
                            "package",
                            context.packageName,
                            null
                        )
                    })
                })
        },
        content = content
    )
}