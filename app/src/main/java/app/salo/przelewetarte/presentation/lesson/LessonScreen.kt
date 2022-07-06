package app.salo.przelewetarte.presentation.lesson

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.salo.przelewetarte.R
import app.salo.przelewetarte.presentation.Screen
import app.salo.przelewetarte.presentation.components.BorderlandsImageCard
import app.salo.przelewetarte.presentation.components.OrangeButton
import app.salo.przelewetarte.presentation.components.SadTopBar

@Composable
fun LessonScreen(
    lessonViewModel: LessonViewModel = hiltViewModel(),
    navController: NavController,
    lessonId: String? = "0"
) {
    val id = lessonId?.toInt() ?: 0
    val InterFont = FontFamily(
        Font(R.font.inter_font, weight = FontWeight.Light),
        Font(R.font.inter_bold_font, weight = FontWeight.Bold)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SadTopBar(
                navController = navController,
                mainText = "Lesson ${id+1}",
                route = Screen.HomeScreen.route
            )

            BorderlandsImageCard(
                photo = lessonViewModel.images[id],
                height = 200.dp,
                horizontalPadding = 16.dp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 20.dp),
            ) {
                Text(
                    text = stringResource(id = lessonViewModel.beginnings[id]),
                    fontSize = 20.sp,
                    fontFamily = InterFont,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFF14261F)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(id = R.string.printable),
                    fontSize = 20.sp,
                    fontFamily = InterFont,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFF14261F)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Task",
                    textAlign = TextAlign.Start,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF14261F)
                )

                Spacer(modifier = Modifier.height(9.dp))

                Text(
                    text = stringResource(id = lessonViewModel.tasks[id]),
                    fontSize = 20.sp,
                    fontFamily = InterFont,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFF14261F)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(id = R.string.ending),
                    fontSize = 20.sp,
                    fontFamily = InterFont,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFF14261F)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            OrangeButton(
                onClick = {
                    navController.navigate(Screen.CameraScreen.route)
                },
                textInButton = "Photo maker",
                textSize = 32.sp
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}