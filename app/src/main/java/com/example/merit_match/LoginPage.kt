package com.example.merit_match

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
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
    mainViewModel.fetchUser(user)
    val viewState by mainViewModel.infoState
    val context = LocalContext.current

    val family = FontFamily(
        Font(R.font.sdasian)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfffff0db))
    ){
        Image(
            modifier = Modifier
                .offset(y = 170.dp)
                .align(Alignment.TopCenter)
                .size(150.dp),
            painter = painterResource(id = R.drawable.yinyang),
            contentDescription = "yin yang"
        )
        Text(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(y = 90.dp, x = 10.dp),
            text = "MERIT",
            fontFamily = family,
            color = Color(0xff3a3f49),
            fontSize = 80.sp
        )
        Text(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(y = 90.dp, x = (-5).dp),
            text = "MATCH",
            fontFamily = family,
            color = Color(0xff3a3f49),
            fontSize = 80.sp
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = 30.dp),
            text = "username:",
            fontFamily = family,
            color = Color(0xff3a3f49),
            fontSize = 40.sp
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(y = 70.dp, x = 30.dp),
            text = "password:",
            fontFamily = family,
            color = Color(0xff3a3f49),
            fontSize = 40.sp
        )

        TextField(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(x = (-10).dp)
                .width(190.dp),
            textStyle = LocalTextStyle
                .current.copy(
                    color = Color(0xfffff0db),
                    fontSize = 30.sp,
                    fontFamily = family
                ),
            value = user,
            onValueChange = {
                user = it
                mainViewModel.fetchUser(user)
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xffb24434)
            )
        )
        TextField(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(y = 70.dp, x = (-10).dp)
                .width(190.dp),
            textStyle = LocalTextStyle
                .current.copy(
                    color = Color(0xfffff0db),
                    fontSize = 30.sp,
                    fontFamily = family
                ),
            value = password,
            onValueChange = {
                password = it
                mainViewModel.fetchUser(user)
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xffb24434)
            )
        )

        if (mode == 1){
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(y = 140.dp, x = 30.dp),
                text = "email:",
                fontFamily = family,
                color = Color(0xff3a3f49),
                fontSize = 40.sp
            )
            TextField(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(y = 140.dp, x = (-10).dp)
                        .width(190.dp),
            textStyle = LocalTextStyle
                .current.copy(
                    color = Color(0xfffff0db),
                    fontSize = 30.sp,
                    fontFamily = family
                ),
            value = email,
            onValueChange = { email = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xffb24434)
            )
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(x = 30.dp, y = (-70).dp),
            text = if (mode == 0) "new user?" else "already have an account?",
            fontFamily = family,
            color = Color(0xff3a3f49),
            fontSize = 30.sp
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(x = 35.dp, y = (-40).dp)
                .clickable {
                    mode = (mode + 1) % 2
                },
            text = if (mode == 1) "LOGIN" else "REGISTER",
            fontFamily = family,
            color = Color(0xffb24434),
            fontSize = 30.sp
        )

        Button(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-30).dp, y = (-140).dp),
            onClick = {
                if (mode == 0){
                    if(viewState.user.username != "404 error"){
                        if (viewState.user.password == password){
                            navController.navigate(Screen.HomePage.route)
                        }
                        else{
                            Toast.makeText(context, "incorrect password", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Toast.makeText(context, "no such user exits", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    mainViewModel.createUser(user = User(user, password, email, 100))

                    if(!viewState.loading){
                        Toast.makeText(context, "created successfully", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffb24434))
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, top = 3.dp, bottom = 3.dp),
                text = if (mode == 1) "REGISTER" else "LOGIN",
                fontFamily = family,
                color = Color(0xfffff0db),
                fontSize = 40.sp
            )
        }
    }

}