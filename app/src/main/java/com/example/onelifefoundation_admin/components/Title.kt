package com.example.onelifefoundation_admin.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun Title(
    title:String,
    modifier: Modifier=Modifier
){
    Column {
        Text(
            text =title,
            modifier=Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    }
}