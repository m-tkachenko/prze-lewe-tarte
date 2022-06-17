package app.salo.przelewetarte.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.salo.przelewetarte.presentation.theme.mainFont

@Composable
fun OrangeButton(
    onClick: () -> Unit,
    isEnabled: Boolean = true,
    isClicked: Boolean = true,
    paddingStart: Dp = 0.dp,
    paddingEnd: Dp = 0.dp,
    paddingTop: Dp = 0.dp,
    paddingBottom: Dp = 0.dp,
    paddingAll: Dp = 0.dp,
    paddingHorizontal: Dp = 0.dp,
    paddingVertical: Dp = 0.dp,
    textInButton: String,
    textSize: TextUnit,
    fillMaxWidth: Boolean = false
) {
    val localModifier =
        if (fillMaxWidth)
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        else
            Modifier
                .wrapContentHeight()
                .wrapContentWidth()

    Button(
        onClick = onClick,
        modifier = localModifier,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(
            width = 2.dp,
            color =
                if (isEnabled)
                    Color(0xFF633926)
                else
                    Color(0xFF14261F)
        ),
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary,
            disabledBackgroundColor = Color(0xFFB8BEBC)
        ),
        elevation = ButtonDefaults.elevation(5.dp)
    ) {
        Text(
            text = textInButton,
            fontFamily = mainFont,
            fontSize = textSize,
            color = Color.White,
            modifier = Modifier
                .padding(
                    start = paddingStart,
                    end = paddingEnd,
                    top = paddingTop,
                    bottom = paddingBottom
                )
                .padding(
                    all = paddingAll
                )
                .padding(
                    horizontal = paddingHorizontal,
                    vertical = paddingVertical
                )
        )
    }
}