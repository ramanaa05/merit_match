package com.example.merit_match

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
val theUser = mutableStateOf("")
@Composable
fun HomePage(navController: NavController){
    theUser.value = "nigger"
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xff7a32cc4),
                        Color(0xff311432)
                    )
                )
            )
    ){
        Box(
            modifier = Modifier
                .offset(y = 35.dp)
                .align(Alignment.TopCenter)
                .clip(RoundedCornerShape(20.dp))
                .height(170.dp)
                .width(400.dp)
                .background(Color.Black.copy(0.4f))
        ){
            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 20.dp, y = 10.dp),
                text = "Welcome, ${theUser.value}",
                color = Color.White,
                fontFamily = FontFamily.Monospace,
                fontSize = 35.sp
            )

            //number of tasks posted
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .offset(x = 20.dp, y = (-30).dp)
            ){
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        modifier = Modifier,
                        text = "9",
                        color = Color.White,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 25.sp
                    )
                    Spacer(modifier = Modifier.padding(3.dp))
                    Text(
                        modifier = Modifier
                            .padding(bottom = 1.dp),
                        text = "tasks",
                        color = Color.White,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 20.sp
                    )
                }
            }
            Text(
                modifier = Modifier
                    .offset(x = 20.dp, y = -(10).dp)
                    .align(Alignment.BottomStart),
                text = "posted",
                color = Color.White.copy(0.5f),
                fontFamily = FontFamily.Monospace,
                fontSize = 25.sp
            )

            //karma points
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-30).dp)
            ){
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        modifier = Modifier,
                        text = "70",
                        color = Color.White,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 25.sp
                    )
                    Spacer(modifier = Modifier.padding(3.dp))
                    Text(
                        modifier = Modifier
                            .padding(bottom = 1.dp),
                        text = "points",
                        color = Color.White,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 20.sp
                    )
                }
            }
            Text(
                modifier = Modifier
                    .offset(y = -(9).dp)
                    .align(Alignment.BottomCenter),
                text = "balance",
                color = Color.White.copy(0.5f),
                fontFamily = FontFamily.Monospace,
                fontSize = 25.sp
            )

            //reserved
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = -(8).dp, y = (-30).dp)
            ){
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        modifier = Modifier,
                        text = "5",
                        color = Color.White,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 25.sp
                    )
                    Spacer(modifier = Modifier.padding(3.dp))
                    Text(
                        modifier = Modifier
                            .padding(bottom = 1.dp),
                        text = "tasks",
                        color = Color.White,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 20.sp
                    )
                }
            }
            Text(
                modifier = Modifier
                    .offset(x = (-13).dp, y = -(9).dp)
                    .align(Alignment.BottomEnd),
                text = "taken",
                color = Color.White.copy(0.5f),
                fontFamily = FontFamily.Monospace,
                fontSize = 25.sp
            )
        }

        Box(
            modifier = Modifier
                .offset(y = 210.dp)
                .align(Alignment.TopCenter)
                .clip(RoundedCornerShape(20.dp))
                .height(80.dp)
                .width(400.dp)
                .background(Color.Black.copy(0.4f))
        ){
            Image(
                modifier = Modifier
                    .offset(x = 30.dp)
                    .size(50.dp)
                    .align(Alignment.CenterStart)
                    .clickable {

                    },
                painter = painterResource(id = R.drawable.add),
                contentDescription = "add task",
            )
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.Center)
                    .clickable {

                    }
                    .align(Alignment.CenterStart),
                painter = painterResource(id = R.drawable.search),
                contentDescription = "search",
            )

            Image(
                modifier = Modifier
                    .offset(x = (-30).dp)
                    .size(50.dp)
                    .align(Alignment.CenterEnd)
                    .clickable {

                    }
                    .align(Alignment.CenterStart),
                painter = painterResource(id = R.drawable.settings),
                contentDescription = "settings"
            )
        }


        Box(
            modifier = Modifier
                .offset(y = 297.dp)
                .align(Alignment.TopCenter)
                .clip(RoundedCornerShape(20.dp))
                .height(500.dp)
                .width(400.dp)
                .background(Color.Black.copy(0.4f))
        ){
            LazyColumn {
                items(count = 25) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 10.dp, start = 15.dp, end = 15.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(color = Color(0xffe39ff6).copy(0.3f))
                    ){
                        Column {
                            Text(
                                modifier = Modifier
                                    .padding(start = 5.dp, top = 5.dp, bottom = 5.dp),
                                text = "Suck my dick, fuck my ass, handjob, bring your mom",
                                color = Color.White,
                                fontSize = 30.sp,
                                fontFamily = FontFamily.Monospace
                            )
                            Text(
                                modifier = Modifier
                                    .padding(start = 5.dp,top = 40.dp, bottom = 25.dp),
                                text = "posted by nigger",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                }
            }
        }
    }
}