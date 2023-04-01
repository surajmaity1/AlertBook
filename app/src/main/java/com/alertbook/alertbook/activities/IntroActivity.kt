package com.alertbook.alertbook.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.alertbook.alertbook.R
import com.alertbook.alertbook.activities.ui.theme.AlertBookTheme
import com.alertbook.alertbook.viewmodel.IntroData
import androidx.annotation.IntRange
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alertbook.alertbook.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.*
class IntroActivity : ComponentActivity() {
    //@OptIn(ExperimentalPagerApi::class, DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}
        /*
        setContent {
            AlertBookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val elements = ArrayList<IntroData>()

                    elements.add(
                        IntroData(
                            img = R.drawable.one,
                            title = stringResource(id = R.string.welcome_here),
                            info = stringResource(id = R.string.info1),
                            bgColor = Color(0xFFE91E63),
                            mainColor = ColorRed
                        )
                    )
                    elements.add(
                        IntroData(
                            img = R.drawable.two,
                            title = stringResource(id = R.string.what_we_do),
                            info = stringResource(id = R.string.info2),
                            bgColor = Color(0xFF96E172),
                            mainColor = ColorGreen
                        )
                    )


                    elements.add(
                        IntroData(
                            img = R.drawable.three,
                            title = stringResource(id = R.string.make_your_life_easy),
                            info = stringResource(id = R.string.info3),
                            bgColor = Color(0xFF0189C5),
                            mainColor = ColorBlue
                        )
                    )

                    val pagerState = rememberPagerState(
                        initialPage = 0,
                    )


                    IntroPager(
                        elements = elements, pagerState = pagerState, modifier = Modifier
                            .fillMaxWidth()
                            .background(color = ColorGreen),

                    )
                }
            }
        }
    }
}


@DelicateCoroutinesApi
@ExperimentalPagerApi
@Composable
fun IntroPager(
    elements: List<IntroData>,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    Box(modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HorizontalPager(state = pagerState, count = elements.size) { page ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(elements[page].bgColor),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {

                    Image(
                        painter = painterResource(id = elements[page].img),
                        contentDescription = elements[page].title,
                        modifier = Modifier
                            .fillMaxWidth()
                    )


                }
            }

        }

        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(390.dp),
                backgroundColor = Color.White,
                elevation = 0.dp,
                shape = BottomCardShape.large
            ) {
                Box() {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        PagerIndicator(items = elements, currentPage = pagerState.currentPage)
                        Text(
                            text = elements[pagerState.currentPage].title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp, end = 30.dp),
                            color = elements[pagerState.currentPage].mainColor,
                            textAlign = TextAlign.Right,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Text(
                            text = elements[pagerState.currentPage].info,
                            modifier = Modifier.padding(top = 20.dp, start = 40.dp, end = 20.dp),
                            color = offBlack,
                            fontSize = 17.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.ExtraLight
                        )

                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(30.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {


                            if (pagerState.currentPage != 2) {
                                TextButton(onClick = {
                                    //skip
                                    val intent = Intent(context, MainActivity::class.java)
                                    context.startActivity(intent)
                                }) {
                                    Text(
                                        text = stringResource(id = R.string.skip),
                                        color = elements[pagerState.currentPage].mainColor,
                                        textAlign = TextAlign.Right,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }

                                OutlinedButton(
                                    onClick = {
                                        GlobalScope.launch {
                                            withContext(Dispatchers.Main) {
                                                pagerState.scrollToPage(
                                                    pagerState.currentPage + 1,
                                                    pageOffset = 0f
                                                )
                                            }

                                        }
                                    },
                                    border = BorderStroke(
                                        14.dp,
                                        elements[pagerState.currentPage].mainColor
                                    ),
                                    shape = RoundedCornerShape(50), // = 50% percent
                                    //or shape = CircleShape
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = elements[pagerState.currentPage].mainColor),
                                    modifier = Modifier.size(65.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_right_arrow),
                                        contentDescription = "",
                                        tint = elements[pagerState.currentPage].mainColor,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            } else {
                                Button(
                                    onClick = {
                                        //show home screen
                                        val intent = Intent(context, MainActivity::class.java)
                                        context.startActivity(intent)
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = elements[pagerState.currentPage].mainColor
                                    ),
                                    contentPadding = PaddingValues(vertical = 12.dp),
                                    elevation = ButtonDefaults.elevation(
                                        defaultElevation = 0.dp
                                    )
                                ) {
                                    Text(
                                        text = "Get Started",
                                        color = Color.White,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun PagerIndicator(currentPage: Int, items: List<IntroData>) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(top = 20.dp)
    ) {
        repeat(items.size) {
            Indicator(isSelected = it == currentPage, color = items[it].mainColor)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean, color: Color) {
    val width = animateDpAsState(targetValue = if (isSelected) 40.dp else 10.dp)

    Box(
        modifier = Modifier
            .padding(4.dp)
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) color else Color.Gray.copy(alpha = 0.5f)
            )
    )
}

@ExperimentalPagerApi
@Composable
fun rememberPagerState(
    @IntRange(from = 0) initialPage: Int = 0
): PagerState = rememberSaveable(saver = PagerState.Saver) {
    PagerState(
        currentPage = initialPage,
    )
}

         */