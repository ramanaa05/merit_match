package com.example.merit_match

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

val rated = mutableStateListOf(0, 0, 0, 0, 0)

@Composable
fun RatingPage(){
    AlertDialog(
        modifier = Modifier,
        onDismissRequest = {
            pageFlag.value = 0
        },
        title = { Text(text = "Rating") },
        text = {
            Column {
                Text(text = "on a scale of 1 to 5, how would you rate the work done by ${rateUser.value}?")
                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(top = 10.dp),
                    columns = GridCells.Fixed(5)
                ) {
                    items(count = 5){
                        if (rated[it] == 0){
                            Image(
                                modifier = Modifier.size(30.dp)
                                    .clickable {
                                        if (it == 0){
                                            if (rated[it+1] == 0){
                                                rated[it] = 0
                                            }
                                        }
                                        for (i in 0..4){
                                            if (i <= it){
                                                rated[i] = 1
                                            }
                                            else {
                                                rated[i] = 0
                                            }
                                        }
                                    },
                                painter = painterResource(id = R.drawable.white_star),
                                contentDescription = "empty star"
                            )
                            Spacer(Modifier.padding(10.dp))
                        }
                        else {
                            Image(
                                modifier = Modifier.size(30.dp)
                                    .clickable {
                                        if (it == 0){
                                            if (rated[it+1] == 0){
                                                rated[it] = 0
                                            }
                                        }
                                        if (rated[it] == 1){
                                            for (i in 0..4){
                                                if (i <= it){
                                                    rated[i] = 1
                                                }
                                                else {
                                                    rated[i] = 0
                                                }
                                            }
                                        }
                                    },
                                painter = painterResource(id = R.drawable.yellow_star),
                                contentDescription = "filled star"
                            )
                            Spacer(Modifier.padding(10.dp))
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { pageFlag.value = 0 }
            ) {
                Text("Yes")
            }
        },
        dismissButton = {
            Button(
                onClick = { pageFlag.value = 0 }
            ) {
                Text("No")
            }
        },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    )
}