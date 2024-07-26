package com.example.merit_match

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties

@Composable
fun Confirmation(){
    AlertDialog(
        onDismissRequest = {
            pageFlag.value = 0
        },
        title = { Text(text = "Confirm Transaction") },
        text = { Text(text = "Are you sure you want to move forward with the transaction? Once paid, you cannot claim for a refund") },
        confirmButton = {
            Button(
                onClick = { pageFlag.value = 0; transaction.value = true }
            ) {
                Text("Yes")
            }
        },
        dismissButton = {
            Button(
                onClick = { pageFlag.value = 2; transaction.value = true}
            ) {
                Text("No")
            }
        },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    )
}