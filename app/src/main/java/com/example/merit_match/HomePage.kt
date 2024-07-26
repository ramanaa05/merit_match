package com.example.merit_match

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import java.time.LocalDate

val transaction = mutableStateOf(false)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomePage(navController: NavController){
    var mainViewModel: MainViewModel = viewModel()
    val viewState by mainViewModel.infoState
    val context = LocalContext.current
    val family = FontFamily(
        Font(R.font.helvetica)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xff7a32cc4),
                        Color(0xff007FFF)
                    )
                )
            )
    ){
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState, true)
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 80.dp, start = 8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .height(170.dp)
                    .width(375.dp)
                    .background(Color.Black.copy(0.4f))
            ){
                Text(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 20.dp, y = 10.dp),
                    text = "Welcome, ${theUser.value.username}",
                    color = Color.White,
                    fontFamily = family,
                    fontSize = 30.sp
                )
                val currentDate = LocalDate.now()
                val day = currentDate.dayOfWeek
                Text(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 20.dp, y = 50.dp),
                    text = "A ${listOf("beautiful", "wonderful", "spectacular").random()} $day, isn't it!",
                    color = Color.White,
                    fontFamily = family,
                    fontSize = 15.sp
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
                            text = "${task_list_all.size - task_list.size}",
                            color = Color.White,
                            fontFamily = family,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.padding(3.dp))
                        Text(
                            modifier = Modifier
                                .padding(bottom = 1.dp),
                            text = "tasks",
                            color = Color.White,
                            fontFamily = family,
                            fontSize = 15.sp
                        )
                    }
                }
                Text(
                    modifier = Modifier
                        .offset(x = 20.dp, y = -(10).dp)
                        .align(Alignment.BottomStart),
                    text = "posted",
                    color = Color.White.copy(0.5f),
                    fontFamily = family,
                    fontSize = 20.sp
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
                            text = theUser.value.karmaPoints.toString(),
                            color = Color.White,
                            fontFamily = family,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.padding(3.dp))
                        Text(
                            modifier = Modifier
                                .padding(bottom = 1.dp),
                            text = "points",
                            color = Color.White,
                            fontFamily = family,
                            fontSize = 15.sp
                        )
                    }
                }
                Text(
                    modifier = Modifier
                        .offset(y = -(9).dp)
                        .align(Alignment.BottomCenter),
                    text = "balance",
                    color = Color.White.copy(0.5f),
                    fontFamily = family,
                    fontSize = 20.sp
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
                            text = task_list_reserved.size.toString(),
                            color = Color.White,
                            fontFamily = family,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.padding(3.dp))
                        Text(
                            modifier = Modifier
                                .padding(bottom = 1.dp),
                            text = "tasks",
                            color = Color.White,
                            fontFamily = family,
                            fontSize = 15.sp
                        )
                    }
                }
                Text(
                    modifier = Modifier
                        .offset(x = (-13).dp, y = -(9).dp)
                        .align(Alignment.BottomEnd),
                    text = "taken",
                    color = Color.White.copy(0.5f),
                    fontFamily = family,
                    fontSize = 20.sp
                )
            }

            Box(
                modifier = Modifier
                    .padding(top = 5.dp, start = 8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .height(60.dp)
                    .width(375.dp)
                    .background(Color.Black.copy(0.4f))
            ){
                Image(
                    modifier = Modifier
                        .offset(x = 30.dp)
                        .size(35.dp)
                        .align(Alignment.CenterStart)
                        .clickable {
                            navController.navigate(Screen.CreatePage.route)
                        },
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = "add task",
                )
                Image(
                    modifier = Modifier
                        .offset(x = 120.dp)
                        .size(30.dp)
                        .align(Alignment.CenterStart)
                        .clickable {

                        },
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "search",
                )

                Image(
                    modifier = Modifier
                        .offset(x = 210.dp)
                        .size(38.dp)
                        .align(Alignment.CenterStart)
                        .clickable {
                            navController.navigate(Screen.History.route)
                        }
                        .align(Alignment.CenterStart),
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = "settings"
                )

                Image(
                    modifier = Modifier
                        .offset(x = (-30).dp)
                        .size(38.dp)
                        .align(Alignment.CenterEnd)
                        .clickable {
                            navController.popBackStack()
                        }
                        .align(Alignment.CenterStart),
                    painter = painterResource(id = R.drawable.logout),
                    contentDescription = "logout"
                )
            }

            Text(
                modifier = Modifier
                    .padding(top = 40.dp, start = 14.dp),
                fontFamily = family,
                fontSize = 18.sp,
                text = "AVAILABLE TASKS:"
            )

            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .height(300.dp)
                    .width(375.dp)
                    .background(Color.Black.copy(0.4f))
            ){
                LazyColumn {
                    items(count = task_available_display.size) {
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
                                    text = task_available_display[it].task,
                                    color = Color.White,
                                    fontSize = 25.sp,
                                    fontFamily = family
                                )
                                Text(
                                    modifier = Modifier
                                        .padding(start = 5.dp,top = 40.dp),
                                    text = "posted by ${task_available_display[it].username}",
                                    color = Color.Black,
                                    fontSize = 15.sp,
                                    fontFamily = family
                                )
                                Text(
                                    modifier = Modifier
                                        .padding(start = 5.dp, bottom = 25.dp),
                                    text = "offering ${task_available_display[it].karmaPoints} points",
                                    color = Color.Black,
                                    fontSize = 15.sp,
                                    fontFamily = family
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .offset(x = (-70).dp, y = (-10).dp),
                            ){
                                Button(
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .size(45.dp)
                                        .clip(RoundedCornerShape(100)),
                                    onClick = {
                                        val add_to_reserved = task_available_display[it].copy(
                                            reserved = theUser.value.username
                                        )
                                        val approval_status = ApprovalStatus(add_to_reserved.id, false)
                                        mainViewModel.reserveTask(add_to_reserved)
                                        mainViewModel.approveTask(approval_status)
                                        task_list_reserved.add(add_to_reserved)
                                        task_list.removeAt(it)
                                        history.add(
                                            History(
                                                task_available_display[it].id,
                                                task_available_display[it].task,
                                                task_available_display[it].username,
                                                task_available_display[it].karmaPoints,
                                                task_available_display[it].reserved,
                                                "reserved"
                                            )
                                        )
                                        history_all.add(
                                            History(
                                                task_available_display[it].id,
                                                task_available_display[it].task,
                                                task_available_display[it].username,
                                                task_available_display[it].karmaPoints,
                                                task_available_display[it].reserved,
                                                "reserved"
                                            )
                                        )
                                        task_available_display.removeAt(it)
                                        if (!viewState.loading){
                                            Toast.makeText(context, "Task reserved", Toast.LENGTH_SHORT).show()
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xff7a32cc4)
                                    )
                                ) {
                                }
                                Image(
                                    modifier = Modifier
                                        .size(25.dp)
                                        .align(Alignment.Center),
                                    painter = painterResource(id = R.drawable.reserved),
                                    contentDescription = "reserved"
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .offset(x = (-10).dp, y = (-10).dp),
                            ){
                                Button(
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .size(45.dp)
                                        .clip(RoundedCornerShape(100)),
                                    onClick = { /*TODO*/ },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xff7a32cc4)
                                    )
                                ) {
                                }
                                Image(
                                    modifier = Modifier
                                        .offset(x = 2.dp)
                                        .size(25.dp)
                                        .align(Alignment.Center),
                                    painter = painterResource(id = R.drawable.user),
                                    contentDescription = "account"
                                )
                            }
                        }

                    }
                }
            }

            Text(
                modifier = Modifier
                    .padding(top = 40.dp, start = 14.dp),
                fontFamily = family,
                fontSize = 18.sp,
                text = "RESERVED TASKS:"
            )
            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .height(300.dp)
                    .width(375.dp)
                    .background(Color.Black.copy(0.4f))
            ){
                LazyColumn {
                    items(count = task_list_reserved.size) {
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
                                    text = task_list_reserved[it].task,
                                    color = Color.White,
                                    fontSize = 25.sp,
                                    fontFamily = family
                                )
                                Text(
                                    modifier = Modifier
                                        .padding(start = 5.dp,top = 40.dp, bottom = 25.dp),
                                    text = "posted by ${task_list_reserved[it].username}",
                                    color = Color.Black,
                                    fontSize = 15.sp,
                                    fontFamily = family
                                )
                            }
                        }
                    }
                }
            }

            Text(
                modifier = Modifier
                    .padding(top = 40.dp, start = 14.dp),
                fontFamily = family,
                fontSize = 18.sp,
                text = "POSTED TASKS:"
            )
            Box(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .padding(bottom = 80.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .height(300.dp)
                    .width(375.dp)
                    .background(Color.Black.copy(0.4f))
            ){
                LazyColumn {
                    items(count = task_posted.size) {
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
                                    text = task_posted[it].task,
                                    color = Color.White,
                                    fontSize = 25.sp,
                                    fontFamily = family
                                )
                                Text(
                                    modifier = Modifier
                                        .padding(start = 5.dp,top = 40.dp, bottom = 25.dp),
                                    text = if (task_posted[it].reserved == "") "yet to be reserved" else "reserved by ${task_posted[it].reserved}",
                                    color = Color.Black,
                                    fontSize = 15.sp,
                                    fontFamily = family
                                )
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 10.dp),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Box(
                                        modifier = Modifier,
                                    ){
                                        Button(
                                            modifier = Modifier
                                                .padding(end = 10.dp)
                                                .align(Alignment.Center)
                                                .size(45.dp)
                                                .clip(RoundedCornerShape(100)),
                                            onClick = {
                                                taskGlobal.value = task_posted[it]
                                                navController.navigate(Screen.Edit.route)
                                            },
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color(0xff7a32cc4)
                                            )
                                        ) {
                                        }
                                        Image(
                                            modifier = Modifier
                                                .offset(x = (-5).dp)
                                                .size(25.dp)
                                                .align(Alignment.Center),
                                            painter = painterResource(id = R.drawable.pencil),
                                            contentDescription = "edit"
                                        )
                                    }
                                    if (task_posted[it].id in status_id){
                                        val temp = status_id.indexOf(task_posted[it].id)
                                        if (!status_list[temp].approved){
                                            Button(
                                                modifier = Modifier
                                                    .padding(end = 10.dp),
                                                onClick = {
                                                    pageFlag.value = 1
                                                    rateUser.value = task_posted[it].reserved
                                                }
                                            ) {
                                                Text(
                                                    modifier = Modifier,
                                                    text = "approve",
                                                    color = Color.Black,
                                                    fontSize = 15.sp,
                                                    fontFamily = family
                                                )
                                            }
                                        }
                                        if (transaction.value){
                                            if (theUser.value.karmaPoints >= task_posted[it].karmaPoints){
                                                mainViewModel.transaction(task_posted[it])
                                                mainViewModel.deleteTask(task_posted[it])
                                                mainViewModel.deleteApproval(status_list[temp])
                                                var index = 0;
                                                for ( i in task_list_all.indices){
                                                    if (task_list_all[i].id == task_posted[it].id){
                                                        index = i
                                                    }
                                                }


                                                for (i in history.indices){
                                                    if (history[i].id == task_posted[it].id){
                                                        index = i
                                                    }
                                                }

                                                task_list_all.removeAt(index)
                                                status_id.removeAt(temp)
                                                status_list.removeAt(temp)
                                                task_posted.removeAt(it)
                                                history[index].status = "completed"
                                                history_all[index].status = "completed"
                                            }
                                            else{
                                                Toast.makeText(context, "Not enough karma points", Toast.LENGTH_SHORT).show()
                                            }
                                            transaction.value = false
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}