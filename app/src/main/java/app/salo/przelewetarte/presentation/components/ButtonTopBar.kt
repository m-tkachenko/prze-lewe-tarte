package app.salo.przelewetarte.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.salo.przelewetarte.R
import app.salo.przelewetarte.presentation.Screen

@Composable
fun SadTopBar(
    navController: NavController,
    route: String,
    mainText: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
            .padding(
                start = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(
            onClick = {
                navController.navigate(route)
            },
            modifier = Modifier
                .height(30.dp)
                .width(15.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back_icon),
                contentDescription = "Back"
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = (-10).dp),
            text = mainText,
            textAlign = TextAlign.Center,
            fontFamily = MaterialTheme.typography.body1.fontFamily,
            fontSize = 34.sp,
            color = Color(0xFF14261F),
        )
    }
}