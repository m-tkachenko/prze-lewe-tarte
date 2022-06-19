package app.salo.przelewetarte.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.salo.przelewetarte.presentation.components.OrangeButton
import app.salo.przelewetarte.R


@Composable
fun PrettyItem(
    img: Int,
    titleText: String,
    descriptionTextId: Int
) {
    val InterFont = FontFamily(
        Font(R.font.inter_font, weight = FontWeight.Light),
        Font(R.font.inter_bold_font, weight = FontWeight.Bold)
    )

    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = MaterialTheme.colors.onPrimary,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 12.dp)
            .border(
                width = 4.dp,
                color = Color.Black,
                shape = RoundedCornerShape(10.dp)
            ),
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = img),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = titleText,
                    fontSize = 24.sp,
                    fontFamily = InterFont,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                OrangeButton(
                    onClick = { /*TODO*/ },
                    textInButton = "Next up",
                    textSize = 12.sp,
                    fontFamily = InterFont,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(id = descriptionTextId),
                fontSize = 16.sp,
                fontFamily = InterFont,
                fontWeight = FontWeight.Light,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 20.dp)
            )
        }
    }
}