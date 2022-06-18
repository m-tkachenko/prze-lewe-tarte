package app.salo.przelewetarte.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.salo.przelewetarte.presentation.theme.mainFont

@Composable
fun BeautifulTextField(
    labelText: String,
    value: String,
    errorText: String = "",
    isError: Boolean = false,
    onValueChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = labelText,
            textAlign = TextAlign.Start,
            fontFamily = mainFont,
            fontSize = 16.sp,
            color = errorColor(isError)
        )

        TextField(
            value = value,
            onValueChange = onValueChanged,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.5.dp,
                    color = errorColor(isError),
                    shape = RoundedCornerShape(7.dp)
                ),
            shape = RoundedCornerShape(7.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF1F1F1),
                textColor = errorColor(isError),
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            visualTransformation =
                if (labelText.contains("Password"))
                    PasswordVisualTransformation()
                else
                    VisualTransformation.None
        )

        if (isError) {
            Text(
                text = errorText,
                textAlign = TextAlign.Start,
                fontFamily = mainFont,
                fontSize = 14.sp,
                color = MaterialTheme.colors.error
            )
        }
    }
}

@Composable
fun errorColor(isError: Boolean): Color =
    if (isError)
        MaterialTheme.colors.error
    else
        Color(0xFF14261F)
