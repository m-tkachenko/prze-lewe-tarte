package app.salo.przelewetarte.presentation.sign_in.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.salo.przelewetarte.presentation.components.BeautifulTextField
import app.salo.przelewetarte.presentation.components.OrangeButton

@Composable
fun SignUpCard() {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp)
            .offset(y = (-20).dp)
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
                labelText = "Name",
                hintText = "Brian"
            )

            Spacer(modifier = Modifier.height(20.dp))

            BeautifulTextField(
                labelText = "Email",
                hintText = "user@gmail.com"
            )

            Spacer(modifier = Modifier.height(20.dp))

            BeautifulTextField(
                labelText = "Password",
                hintText = "********"
            )

            val checkedState = remember { mutableStateOf(true) }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .offset(x = (-13).dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checkedState.value,
                    onCheckedChange = {checkedState.value = it},
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = Color.White,
                        checkedColor = Color(0xFF071224),
                        uncheckedColor = Color(0xFF14261F),
                    )
                )

                Text(
                    text = "Accept Terms of Service",
                    color = Color(0xFF14261F),
                )
            }

            OrangeButton(
                onClick = { /*TODO*/ },
                textInButton = "Register",
                textSize = 24.sp,
                fillMaxWidth = true,
                paddingVertical = 5.dp
            )
        }
    }
}