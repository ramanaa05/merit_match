package com.example.merit_match

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("InvalidColorHexValue")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreatePage(navController: NavController){
    val context = LocalContext.current
    var max_id = 0
    val family = FontFamily(
        Font(R.font.helvetica)
    )
    for (i in history_all){
        if(i.id > max_id){
            max_id = i.id
        }
    }
    var description by remember { mutableStateOf("") }
    var deadline = remember {
        mutableStateListOf("", "", "")
    }
    var karma by remember { mutableStateOf("") }
    val mainViewModel: MainViewModel = viewModel()
    val viewState by mainViewModel.infoState
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
                    .padding(top = 40.dp, start = 3.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .height(170.dp)
                    .width(400.dp)
                    .background(Color.Black.copy(0.4f))
            ){
                val currentDate = LocalDate.now()
                val day = currentDate.dayOfWeek
                Text(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = 20.dp, y = 10.dp),
                    text = "Date: $currentDate",
                    color = Color.White,
                    fontFamily = family,
                    fontSize = 20.sp
                )
            }


            Text(
                modifier = Modifier
                    .padding(top = 30.dp, start = 10.dp),
                text = "Description",
                color = Color.White,
                fontFamily = family,
                fontSize = 20.sp
            )
            TextField(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .width(370.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .defaultMinSize(minHeight = 200.dp),
                value = description,
                onValueChange = {
                    description = it
                },
                textStyle = LocalTextStyle
                    .current.copy(
                        color = Color(0xfffff0db),
                        fontSize = 20.sp,
                        fontFamily = family
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Black.copy(alpha = 0.4f)
                )
            )

            Text(
                modifier = Modifier
                    .padding(top = 30.dp, start = 10.dp),
                text = "Deadline (dd/mm/yyyy)",
                color = Color.White,
                fontFamily = family,
                fontSize = 20.sp
            )
            Row {
                TextField(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .width(90.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    value = deadline[0],
                    onValueChange = {
                        deadline[0] = it
                    },
                    textStyle = LocalTextStyle
                        .current.copy(
                            color = Color(0xfffff0db),
                            fontSize = 20.sp,
                            fontFamily = family
                        ),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Black.copy(alpha = 0.4f)
                    )
                )
                TextField(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .width(90.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    value = deadline[1],
                    onValueChange = {
                        deadline[1] = it
                    },
                    textStyle = LocalTextStyle
                        .current.copy(
                            color = Color(0xfffff0db),
                            fontSize = 20.sp,
                            fontFamily = family
                        ),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Black.copy(alpha = 0.4f)
                    )
                )
                TextField(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .width(120.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    value = deadline[2],
                    onValueChange = {
                        deadline[2] = it
                    },
                    textStyle = LocalTextStyle
                        .current.copy(
                            color = Color(0xfffff0db),
                            fontSize = 20.sp,
                            fontFamily = family
                        ),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Black.copy(alpha = 0.4f)
                    )
                )
            }

            Text(
                modifier = Modifier
                    .padding(top = 30.dp, start = 10.dp),
                text = "karma points",
                color = Color.White,
                fontFamily = family,
                fontSize = 20.sp
            )
            TextField(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .width(200.dp)
                    .clip(RoundedCornerShape(10.dp)),
                value = karma,
                onValueChange = {
                    karma = it
                },
                textStyle = LocalTextStyle
                    .current.copy(
                        color = Color(0xfffff0db),
                        fontSize = 20.sp,
                        fontFamily = family
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Black.copy(alpha = 0.4f)
                )
            )
            Button(
                modifier = Modifier
                    .padding(start = 250.dp, top = 50.dp, bottom = 20.dp),
                onClick = {
                    mainViewModel.createTask(
                        Task(
                            id = max_id+1,
                            task = description,
                            username = theUser.value.username,
                            karmaPoints = karma.toInt(),
                            reserved = ""
                        )
                    )
                    task_list_all.add(
                        Task(
                            id = max_id+1,
                            task = description,
                            username = theUser.value.username,
                            karmaPoints = karma.toInt(),
                            reserved = ""
                        )
                    )
                    task_posted.add(
                        Task(
                            id = max_id+1,
                            task = description,
                            username = theUser.value.username,
                            karmaPoints = karma.toInt(),
                            reserved = ""
                        )
                    )
                    history.add(
                        History(
                            id = max_id+1,
                            task = description,
                            username = theUser.value.username,
                            karmaPoints = karma.toInt(),
                            reserved = "",
                            status = "posted"
                        )
                    )
                    history_all.add(
                        History(
                            id = max_id+1,
                            task = description,
                            username = theUser.value.username,
                            karmaPoints = karma.toInt(),
                            reserved = "",
                            status = "posted"
                        )
                    )

                    if(!viewState.loading){
                        Toast.makeText(context, "task uploaded", Toast.LENGTH_SHORT).show()
                        description = ""
                        karma = ""
                        deadline[0] = ""
                        deadline[1] = ""
                        deadline[2] = ""
                        navController.popBackStack()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff7a32cc4))
            ){
                Text(
                    modifier = Modifier
                        .padding(start = 3.dp, end = 3.dp),
                    text = "POST",
                    color = Color.White,
                    fontFamily = family,
                    fontSize = 25.sp
                )
            }
        }
    }
}