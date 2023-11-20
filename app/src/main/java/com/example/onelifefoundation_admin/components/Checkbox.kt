package com.example.onelifefoundation_admin.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.AnnotatedString

@Composable
fun CheckBoxWithLabel(label:String){
    val checked = remember { mutableStateOf(false) }

    Row (horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){
        Checkbox(
            checked = checked.value,
            onCheckedChange = { isChecked ->
                checked.value = isChecked
            }
        )
        ClickableText(text = AnnotatedString( label), onClick ={
            checked.value = !checked.value
        } )
    }
}
