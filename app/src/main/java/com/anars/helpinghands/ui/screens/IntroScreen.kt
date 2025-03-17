package com.anars.helpinghands.ui.screens

import android.transition.Slide
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.anars.helpinghands.R
import com.anars.helpinghands.ui.theme.LatoFontFamily
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroScreen(navController: NavController) {

    val greetings = listOf(
        Triple(
            R.drawable.ic_intro_first,
            "Caring Across Distances",
            "Stay connected and ensure immediate care for your loved ones, no matter where you are."
        ),
        Triple(
            R.drawable.ic_intro_second,
            "Instant Emergency Notifications",
            "Notify emergency contacts and nearby volunteers for swift action during a medical crisis."
        ),
        Triple(
            R.drawable.ic_intro_third,
            "Real-Time Tracking",
            "Share precise locations to guide emergency services or local volunteers quickly."
        ),
        Triple(
            R.drawable.ic_intro_fourth,
            "Immediate First Aid Guidance",
            "Access quick first-aid instructions tailored to the emergency type."
        ),
        Triple(
            R.drawable.ic_intro_fifth,
            "Find Nearby Hospitals",
            "Quickly locate hospitals and clinics with contact details for timely care"
        ),
        Triple(
            R.drawable.ic_intro_sixth,
            "Essential Medical Information",
            "Store allergies, medications, and other critical details for faster hospital intake."
        )

    )


    val pagerState = rememberPagerState { greetings.size }
    val scope = rememberCoroutineScope()



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp, bottom = 16.dp)
    ) {

        Text(
            text = stringResource(id = R.string.skip),
            modifier = Modifier
                .wrapContentWidth() // Ensures width wraps the content
                .wrapContentHeight() // Ensures height wraps the content
                .align(Alignment.End)
                .padding( 20.dp) // Optional padding
                .clickable {
                    navController.navigate("home_screen") {
                        popUpTo("intro_screen") { inclusive = true }
                    }
                },

            fontFamily = LatoFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = colorResource(id = R.color.black),
            textAlign = TextAlign.Right,
            textDecoration = TextDecoration.Underline // Adds underline
        )



        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = pagerState,
            key = { greetings[it] },
            pageSize = PageSize.Fill
        ) {
            val realPageIndex = it % 6
            ItemGreeting(greetings[realPageIndex])
        }




        Spacer(modifier = Modifier.height(32.dp))

        DotsIndicator(
            totalDots = greetings.size,
            selectedIndex = pagerState.currentPage,
            selectedColor = R.color.indicator_selected_color,
            unSelectedColor = R.color.indicator_unselected_color
        )

        Spacer(modifier = Modifier.height(80.dp))


        OrangeButton(
            title = stringResource(id = R.string.proceed_to_login),
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 80.dp)
        ) {
            navController.navigate("login_screen") {
                popUpTo("intro_screen") { inclusive = true }
            }
        }


    }
}

@Composable
fun ItemGreeting(item: Triple<Int, String, String>) {


    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxHeight()
            .padding(start = 24.dp, end = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        Text(
            text = item.second,
            fontSize = 20.sp,
            fontFamily = LatoFontFamily,
            fontWeight = FontWeight.Black,
            color = colorResource(id = R.color.color_accent)

        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = item.third,
            fontFamily = LatoFontFamily,
            fontWeight = FontWeight.Normal,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 26.sp // Adjust the line height (spacing)
            ),
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.black)
        )

        Spacer(modifier = Modifier.height(100.dp))



        Image(
            painter = painterResource(id = item.first),
            contentDescription = "image",
            contentScale = ContentScale.Fit
        )


    }


}


@Composable
fun OrangeButton(
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(42.dp),
        shape = RoundedCornerShape(8),
        colors = ButtonDefaults.textButtonColors(colorResource(id = R.color.color_accent)),
        onClick = {
            onClick()
        }) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontFamily = LatoFontFamily,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white)
        )
    }
}


@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Int,
    unSelectedColor: Int,
) {

    LazyRow(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()

    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(colorResource(id = selectedColor))
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(colorResource(id = unSelectedColor))
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            }
        }
    }
}