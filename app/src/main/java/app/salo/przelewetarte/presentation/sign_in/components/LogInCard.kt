package app.salo.przelewetarte.presentation.sign_in.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.salo.przelewetarte.presentation.components.BeautifulTextField
import app.salo.przelewetarte.presentation.components.OrangeButton

@Composable
fun LogInCard() {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp)
            .offset(y = (-35).dp)
            .border(
                width = 4.dp,
                color = Color.Black,
                shape = RoundedCornerShape(10.dp)
            ),
        backgroundColor = MaterialTheme.colors.onPrimary,
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 25.dp)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OrangeButton(
                    onClick = { /*TODO*/ },
                    paddingHorizontal = 16.dp,
                    textInButton = "sign in",
                    textSize = 16.sp
                )

                OrangeButton(
                    onClick = { /*TODO*/ },
                    paddingHorizontal = 16.dp,
                    textInButton = "register",
                    textSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            BeautifulTextField(
                labelText = "Email",
                hintText = "user@gmail.com"
            )
            
            Spacer(modifier = Modifier.height(20.dp))

            BeautifulTextField(
                labelText = "Password",
                hintText = "**********"
            )

            Spacer(modifier = Modifier.height(25.dp))

            OrangeButton(
                onClick = { /*TODO*/ },
                textInButton = "Sign In",
                textSize = 24.sp,
                fillMaxWidth = true,
                paddingVertical = 5.dp
            )
        }
    }
}