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
    mainViewModel.fetchUser(user)
    val viewState by mainViewModel.infoState
    val context = LocalContext.current

    val family = FontFamily(
        Font(R.font.gilroy)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.Black
            )
    ) {
        Box(
            modifier = Modifier
                .offset(y = -(260).dp)
                .align(Alignment.Center)
                .clip(RoundedCornerShape(30.dp))
                .height(100.dp)
                .width(400.dp)
                .background(Color.Black.copy(0.4f))
        ){
            Text(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 4.dp),
                text = "MERIT MATCH",
                fontFamily = FontFamily.Monospace,
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 60.dp),
                text = "what goes around comes around",
                fontFamily = FontFamily.Monospace,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Box(
            modifier = Modifier
                .offset(y = 50.dp)
                .align(Alignment.Center)
                .clip(RoundedCornerShape(40.dp))
                .height(500.dp)
                .width(400.dp)
                .background(Color.Black.copy(0.4f))
        ){
            Text(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 4.dp),
                text = if (mode == 0) "LOGIN" else "REGISTER",
                fontFamily = FontFamily.Monospace,
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(y = 70.dp, x = 20.dp),
                text = "username",
                fontFamily = FontFamily.Monospace,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
            TextField(
                modifier = Modifier
                    .offset(y = -(110).dp, x = 15.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .align(Alignment.CenterStart)
                    .width(350.dp),
                textStyle = LocalTextStyle
                    .current.copy(
                        color = Color(0xfffff0db),
                        fontSize = 30.sp,
                        fontFamily = FontFamily.Monospace
                    ),
                value = user,
                label = { Text(text = "username") },
                onValueChange = {
                    user = it
                    mainViewModel.fetchUser(user)
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Black
                )
            )

            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(y = 195.dp, x = 20.dp),
                text = "password",
                fontFamily = FontFamily.Monospace,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
            TextField(
                modifier = Modifier
                    .offset(y = 15.dp, x = 15.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .align(Alignment.CenterStart)
                    .width(350.dp),
                textStyle = LocalTextStyle
                    .current.copy(
                        color = Color(0xfffff0db),
                        fontSize = 30.sp,
                        fontFamily = FontFamily.Monospace
                    ),
                value = password,
                label = { Text(text = "password") },
                onValueChange = {
                    password = it
                    mainViewModel.fetchUser(user)
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Black
                )
            )

            if (mode == 1){
                Text(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(y = 320.dp, x = 20.dp),
                    text = "e-mail",
                    fontFamily = FontFamily.Monospace,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                TextField(
                    modifier = Modifier
                        .offset(y = 140.dp, x = 15.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .align(Alignment.CenterStart)
                        .width(350.dp),
                    textStyle = LocalTextStyle
                        .current.copy(
                            color = Color(0xfffff0db),
                            fontSize = 30.sp,
                            fontFamily = FontFamily.Monospace
                        ),
                    value = email,
                    label = { Text(text = "email") },
                    onValueChange = {
                        email = it
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Black
                    )
                )
            }

            Text(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(y = -(15).dp, x =-(20).dp)
                    .clickable {
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
                text = if (mode == 1) "register" else "login",
                fontFamily = FontFamily.Monospace,
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Box(
            modifier = Modifier
                .offset(x = -(103.dp), y = 360.dp)
                .align(Alignment.Center)
                .clip(RoundedCornerShape(30.dp))
                .height(100.dp)
                .width(195.dp)
                .background(color = if (mode == 0) Color.White.copy(0.4f) else Color.Black.copy(0.4f))
                .clickable {
                    mode = 0
                }
        ){
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "LOGIN",
                fontFamily = FontFamily.Monospace,
                color = if (mode == 0) Color.Black else Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Box(
            modifier = Modifier
                .offset(x = (103.dp), y = 360.dp)
                .align(Alignment.Center)
                .clip(RoundedCornerShape(30.dp))
                .height(100.dp)
                .width(195.dp)
                .background(color = if (mode == 1) Color.White.copy(0.4f) else Color.Black.copy(0.4f))
                .clickable {
                    mode = 1
                }
        ){
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "REGISTER",
                fontFamily = FontFamily.Monospace,
                color = if (mode == 1) Color.Black else Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}