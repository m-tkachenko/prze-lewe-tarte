package app.salo.przelewetarte.presentation.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun OrangeDialog(
    titleText: String,
    otherText: String,
    textOnConfirmButton: String,
    onConfirmClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { /*blbla*/ },
        title = {
            Text(
                text = titleText
            )
        },
        text = {
            Text(
                text = otherText
            )
        },
        confirmButton = {
            OrangeButton(
                onClick = onConfirmClick,
                textInButton = textOnConfirmButton,
                textSize = 14.sp
            )
        }
    )
}