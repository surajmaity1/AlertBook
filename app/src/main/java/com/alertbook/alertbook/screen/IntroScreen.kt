package com.alertbook.alertbook.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alertbook.alertbook.navigation.Screen
import com.alertbook.alertbook.utils.IntroPage
import com.alertbook.alertbook.viewmodel.IntroViewModel
import com.google.accompanist.pager.*

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun IntroScreen(
    navController: NavHostController,
    introViewModel: IntroViewModel = hiltViewModel()
) {
    val pages = listOf(
        IntroPage.First,
        IntroPage.Second,
        IntroPage.Third
    )

    val pagerState = rememberPagerState()

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            count = 3,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            PagerScreen(introPage = pages[position])
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f),
            pagerState = pagerState
        )
        GetStartedButton(
            modifier = Modifier.weight(1f),
            pagerState = pagerState
        ) {
            introViewModel.saveIntroState(completed = true)
            navController.popBackStack()
            navController.navigate(Screen.DashBoard.route)
        }
    }
}


@Composable
fun PagerScreen(introPage: IntroPage) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = introPage.img),
            contentDescription = "Pager Image"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = introPage.title,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 20.dp),
            text = introPage.info,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}



@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun GetStartedButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 2
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White
                )
            ) {
                Text(text = "Get Started")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FirstIntroScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(introPage = IntroPage.First)
    }
}

@Composable
@Preview(showBackground = true)
fun SecondIntroScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(introPage = IntroPage.Second)
    }
}

@Composable
@Preview(showBackground = true)
fun ThirdIntroScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(introPage = IntroPage.Third)
    }
}