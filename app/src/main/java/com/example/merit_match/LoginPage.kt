package com.example.merit_match

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(navController: NavController){
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember{ mutableStateOf("") }
    var mode by remember { mutableStateOf(0) }
    var mainViewModel: MainViewModel = viewModel()
    mainViewModel.fetchUser(User(username = user, password = password, email = email, 0, 0, 0))
    mainViewModel.fetchAllTasks()
    mainViewModel.fetchAllStatus()
    mainViewModel.fetchHistory()
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
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 15.dp, top = 150.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .height(100.dp)
                    .width(380.dp)
                    .background(Color.Black.copy(0.4f))
            ){
                Text(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = 4.dp),
                    text = "MERIT MATCH",
                    fontFamily = family,
                    color = Color.White,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = 60.dp),
                    text = "what goes around, comes around",
                    fontFamily = family,
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Box(
                modifier = Modifier
                    .padding(start = 15.dp, top = 10.dp)
                    .clip(RoundedCornerShape(40.dp))
                    .width(380.dp)
                    .background(Color.Black.copy(0.4f))
            ){
                Text(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = 4.dp),
                    text = if (mode == 0) "LOGIN" else "REGISTER",
                    fontFamily = family,
                    color = Color.White,
                    fontSize = 27.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Column(
                    modifier = Modifier
                        .defaultMinSize(minHeight = 400.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 8.dp, top = 70.dp),
                        text = "username",
                        fontFamily = family,
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    TextField(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .width(350.dp),
                        textStyle = LocalTextStyle
                            .current.copy(
                                color = Color(0xfffff0db),
                                fontSize = 15.sp,
                                fontFamily = family
                            ),
                        value = user,
                        placeholder = { Text(text = "username") },
                        onValueChange = {
                            user = it
                            mainViewModel.fetchUser(User(username = user, password = password, email = email, 0, 0, 0))
                            mainViewModel.fetchTasks(user)
                            mainViewModel.fetchReservedTasks(user)
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Black
                        )
                    )

                    Text(
                        modifier = Modifier
                            .padding(start = 8.dp, top = 15.dp),
                        text = "password",
                        fontFamily = family,
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    TextField(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .width(350.dp),
                        textStyle = LocalTextStyle
                            .current.copy(
                                color = Color(0xfffff0db),
                                fontSize = 15.sp,
                                fontFamily = family
                            ),
                        value = password,
                        placeholder = { Text(text = "password") },
                        onValueChange = {
                            password = it
                            mainViewModel.fetchUser(User(username = user, password = password, email = email, 0, 0, 0))
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Black
                        )
                    )

                    if (mode == 1){
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp, top = 15.dp),
                            text = "e-mail",
                            fontFamily = family,
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        TextField(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .width(350.dp),
                            textStyle = LocalTextStyle
                                .current.copy(
                                    color = Color(0xfffff0db),
                                    fontSize = 15.sp,
                                    fontFamily = family
                                ),
                            value = email,
                            placeholder = { Text(text = "email") },
                            onValueChange = {
                                email = it
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Black
                            )
                        )
                    }

                    Button(
                        modifier = Modifier
                            .padding(top = 15.dp, start = 200.dp, bottom = 20.dp),
                        onClick = {
                            if (mode == 0) {
                                if (viewState.user.username != "404 error: user not found") {
                                    if (viewState.user.username == "error code -1: incorrect password") {
                                        Toast
                                            .makeText(context, "incorrect password", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                    else {
                                        theUser.value.username = user
                                        theUser.value.password = password
                                        theUser.value.email = email
                                        theUser.value.karmaPoints = viewState.user.karmaPoints
                                        Toast
                                            .makeText(
                                                context,
                                                "logged in successfully",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                        task_list.clear()
                                        for (i in viewState.tasks.tasks) {
                                            task_list.add(i)
                                        }
                                        task_list_all.clear()
                                        for (i in viewState.allTasks.tasks) {
                                            task_list_all.add(i)
                                        }
                                        task_list_reserved.clear()
                                        for (i in viewState.reservedTasks.tasks) {
                                            task_list_reserved.add(i)
                                        }
                                        task_available_display.clear()
                                        for (i in task_list){
                                            var flag_add = true
                                            for (j in task_list_all){
                                                if (j.reserved != ""){
                                                    if (i.id == j.id){
                                                        flag_add = false
                                                    }
                                                }
                                            }
                                            if (flag_add){
                                                task_available_display.add(i)
                                            }
                                        }

                                        task_posted.clear()
                                        for (i in task_list_all){
                                            if (i.username == user){
                                                task_posted.add(i)
                                            }
                                        }

                                        status_list.clear()
                                        status_id.clear()
                                        for (i in viewState.statusList.status){
                                            status_list.add(i)
                                            status_id.add(i.id)
                                        }
                                        val temp = mutableListOf<Int>()
                                        history_all.clear()
                                        for (i in viewState.history.history.indices){
                                            temp.add(i)
                                            history_all.add(viewState.history.history[i])
                                        }
                                        history.clear()
                                        for (i in temp){
                                            if ((viewState.history.history[i].username == user) or (viewState.history.history[i].reserved == user)){
                                                history.add(viewState.history.history[i])
                                                history_all.add(viewState.history.history[i])
                                            }
                                        }

                                        navController.navigate(Screen.HomePage.route)
                                    }
                                } else {
                                    Toast
                                        .makeText(context, "no such user exits", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            } else {
                                mainViewModel.createUser(user = User(user, password, email, 100, 0, 0))

                                if (!viewState.loading) {
                                    Toast
                                        .makeText(context, "created successfully", Toast.LENGTH_SHORT)
                                        .show()
                                    mode = 0
                                    user = ""
                                    password = ""
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(Color(0xff7a32cc4).copy(0.6f))
                    ) {
                        Text(
                            modifier = Modifier,
                            text = if (mode == 1) "REGISTER" else "LOGIN",
                            fontFamily = family,
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .padding(start = 15.dp, top = 40.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .fillMaxWidth()
            ){
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 30.dp),
                        text = if (mode == 0) "New here?" else "Already have an account?",
                        fontFamily = family,
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        modifier = Modifier
                            .clickable {
                                       mode = (mode+1)%2
                            },
                        text = if (mode == 0) " Register" else " Login",
                        fontFamily = family,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                    )
                }
            }
        }
    }
    

}