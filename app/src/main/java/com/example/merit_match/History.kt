package com.example.merit_match

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun History(navController: NavController){
    val family = FontFamily(
        Font(R.font.helvetica)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
    ){
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(count = history.size) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 10.dp, start = 15.dp, end = 15.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            color = if ((history[it].status == "reserved")and (history[it].username != theUser.value.username)) {
                                Color.Red.copy(0.5f)
                            } else if ((history[it].status == "completed")){
                                Color.Green.copy(0.5f)
                            } else if ((history[it].status == "reserved") and (history[it].username == theUser.value.username)){
                                Color.Yellow.copy(0.5f)
                            }else{
                                Color.Black.copy(0.5f)
                            }
                        )
                ){
                    Column {
                        Text(
                            modifier = Modifier
                                .padding(start = 5.dp, top = 5.dp, bottom = 5.dp),
                            text = history[it].task,
                            color = Color.White,
                            fontSize = 25.sp,
                            fontFamily = family
                        )
                        if (history[it].reserved == theUser.value.username){
                            Text(
                                modifier = Modifier
                                    .padding(start = 5.dp,top = 40.dp, bottom = 25.dp),
                                text = "status: ${history[it].status}",
                                color = Color.Black,
                                fontSize = 15.sp,
                                fontFamily = family
                            )
                        }
                        else{
                            Text(
                                modifier = Modifier
                                    .padding(start = 5.dp,top = 40.dp, bottom = 25.dp),
                                text = if (history[it].status == "posted") "yet to be reserved" else "${history[it].status} by ${history[it].reserved}",
                                color = Color.Black,
                                fontSize = 15.sp,
                                fontFamily = family
                            )
                        }
                    }
                }
            }
        }
    }
}