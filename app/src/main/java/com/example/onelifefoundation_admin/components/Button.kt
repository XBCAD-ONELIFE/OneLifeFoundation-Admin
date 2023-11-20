package com.example.onelifefoundation_admin.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun FilledButtond(
    onClick:() -> Unit,
    label:String,
    modifier: Modifier =Modifier
){
    Column {
        Button(
            onClick = { onClick.invoke() },
            modifier=modifier.fillMaxWidth(.8f)
        ) {
            Text(text=label)
        }
    }
}

@Composable
fun FilledButtonWithICon(
    onClick:() -> Unit,
    label:String,
    modifier: Modifier =Modifier,
    icon:ImageVector
){
    Column {
        Button(
            onClick = { onClick.invoke() },
            modifier=modifier.fillMaxWidth(.8f)
        ) {
            Icon(icon, contentDescription = "Attach File") // Add an icon for attaching files
            Spacer(Modifier.size(4.dp)) // Add some space between the icon and the text
            Text(text=label)
        }
    }
}

@Composable
fun OutlinedButtonD(label: String,onClick: () -> Unit){
    Column {
        OutlinedButton(onClick = { onClick.invoke() }) {
            Text(text = label)
        }
    }
}

@Composable
fun ButtonWithIcon(onClick: () -> Unit, iconResource: Int, buttonText: String){
    Column {
        Button(
            onClick = { onClick.invoke() },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = iconResource),
                    contentDescription = buttonText,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = buttonText
                )
            }
        }
    }
}
