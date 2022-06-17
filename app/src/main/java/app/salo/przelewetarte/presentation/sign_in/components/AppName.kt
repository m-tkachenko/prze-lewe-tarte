package app.salo.przelewetarte.presentation.sign_in.components

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.salo.przelewetarte.R

@Composable
fun AppNameText() {
    val LexendaFont = FontFamily(
        Font(R.font.lexenda_black_font, weight = FontWeight.Normal)
    )

    Text(
        modifier = Modifier
            .padding(top = 16.dp)
            .offset(x = 32.dp),
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
                append("Prze\n")
            }
            withStyle(style = SpanStyle(color = MaterialTheme.colors.secondary)) {
                append("Lewe\n")
            }
            withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
                append("Tarte\n")
            }
        },
        textAlign = TextAlign.Center,
        fontFamily = LexendaFont,
        style = TextStyle(
            fontSize = 40.sp,
            shadow = Shadow(
                color = Color.Black,
                offset = Offset(4.0f, 7.0f),
                blurRadius = 1f
            )
        )
    )
}