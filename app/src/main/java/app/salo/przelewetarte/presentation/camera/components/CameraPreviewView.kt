package app.salo.przelewetarte.presentation.camera.components

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.net.Uri
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import app.salo.przelewetarte.R
import app.salo.przelewetarte.presentation.components.OrangeButton
import java.io.File
import java.util.*
import java.util.concurrent.Executors

@Composable
fun SecondCameraPreviewView(
    imageCapture: ImageCapture,
    cameraAction: () -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val lensFacing: Int = CameraSelector.LENS_FACING_BACK
    val preview = Preview.Builder().build()
    val selector = CameraSelector.Builder()
        .requireLensFacing(lensFacing)
        .build()
    val cameraProvider by remember {
        mutableStateOf(ProcessCameraProvider.getInstance(context).get())
    }

    val previewView = remember {
        PreviewView(context)
    }

    LaunchedEffect(key1 = lensFacing) {
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            selector,
            preview,
            imageCapture
        )

        preview.setSurfaceProvider(previewView.surfaceProvider)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AndroidView(
            factory = { previewView },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
        )

        OrangeButton(
            onClick = cameraAction,
            textInButton = "Cheeese",
            textSize = 20.sp
        )
    }
}

fun ImageCapture.takePicture(
    context: Context,
    onImageCaptured: (Uri, Boolean) -> Unit,
    onError: (ImageCaptureException) -> Unit
) {
    val directory = context.outputDirectory()
    val file = createFile(
        baseFolder = directory
    )
    val outputFileOptions = outputFileOptions(file)

    this.takePicture(
        outputFileOptions,
        Executors.newSingleThreadExecutor(),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val uri = outputFileResults.savedUri ?: Uri.fromFile(file)

                onImageCaptured(uri, false)
            }

            override fun onError(exception: ImageCaptureException) {
                onError(exception)
            }
        }
    )
}

private fun outputFileOptions(
    photoFile: File
): ImageCapture.OutputFileOptions {
    val metadata = ImageCapture.Metadata()

    return ImageCapture.OutputFileOptions
        .Builder(photoFile)
        .setMetadata(metadata)
        .build()
}

private fun createFile(baseFolder: File): File {
    val filename = UUID.randomUUID().toString().replace("f", "7")
    val extension = ".jpg"

    return File(
        baseFolder,
        SimpleDateFormat(filename, Locale.US)
            .format(System.currentTimeMillis()) + extension
    )
}

private fun Context.outputDirectory(): File {
    val mediaDir = this.externalMediaDirs.firstOrNull()?.let {
        File(it, this.resources.getString(R.string.app_name)).apply { mkdirs() }
    }
    return if (mediaDir != null && mediaDir.exists())
        mediaDir else this.filesDir
}