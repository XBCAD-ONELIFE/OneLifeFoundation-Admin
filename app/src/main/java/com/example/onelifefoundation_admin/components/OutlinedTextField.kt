package com.example.onelifefoundation_admin.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onelifefoundation_admin.repository.JoinRequest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldD(
    value: String,
    onValueChange: (String) -> Unit,
    label: String= "",
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue->
            onValueChange.invoke(newValue)
        },
        label = { Text(text = label) },
        placeholder = {Text(text=label)}
    )
}

@Composable
fun TextFieldBasic(
    text: MutableState<String>,
    label: String,

    ){
//    var passwordVisible = rememberSaveable { mutableStateOf(fieldType != FieldType.Password) }
//    val visibilityIcon =
//        if (passwordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visibility

    Column {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text.value,
            onValueChange = {
                text.value = it
            },
            label = { Text(text = label) },
            placeholder = { Text(text = label) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.background,
                cursorColor = MaterialTheme.colors.primary,
                leadingIconColor = MaterialTheme.colors.primaryVariant,
                trailingIconColor = MaterialTheme.colors.primaryVariant,
                focusedLabelColor = MaterialTheme.colors.secondary,
                unfocusedLabelColor = MaterialTheme.colors.onSecondary,
                errorLabelColor = MaterialTheme.colors.error,
                errorCursorColor =  MaterialTheme.colors.onError,
                errorTrailingIconColor = MaterialTheme.colors.error,
            ),

            )
//        if (errorMessage.isNotEmpty()) {
//            Spacer(Modifier.height(6.dp))
//            Text(
//                text = errorMessage,
//                color = MaterialTheme.colors.error,
//                fontSize = 16.sp,
//                modifier = Modifier.padding(start = 6.dp)
//            )
//        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenuBoxD(
    text:List<String>,
    label:String
) {
    var suggestions =text
    var expanded = remember { mutableStateOf(false) }
    var selectedText = remember { mutableStateOf(suggestions[0]) }


    Column {
        ExposedDropdownMenuBox(
            expanded = expanded.value,
            onExpandedChange = {
                expanded.value = it
            },
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            OutlinedTextField(
                value = selectedText.value,
                onValueChange = {
                    selectedText.value=it
                },
                label = {
                    Text(text = label)},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value) },
                modifier = Modifier.menuAnchor(),
            )

            ExposedDropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false }
            ) {
                suggestions.forEach { item ->
                    DropdownMenuItem(
                        onClick = {
                            selectedText.value = item
                            expanded.value = false
                        }
                    ){
                        Text(text = item)
                    }
                }
            }
        }
    }
}


@Composable
fun UserView(user: User){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }.clip(RoundedCornerShape(8.dp)).shadow(elevation = 5.dp, ambientColor = Color.White),
    ) {
        Column(modifier=Modifier.padding(6.dp)) {
            Text(text = user.username, fontSize = 22.sp)
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = user.email, fontSize = 18.sp)
        }
    }
}
@Composable
fun RequestViewList(requests: List<JoinRequest>) {
    LazyColumn(modifier = Modifier
        .fillMaxWidth(1f)
        .padding(12.dp)) {
        items(requests) { request ->
            RequestView(request = request)
            Spacer(modifier = Modifier.size(12.dp))
        }
    }
}


@Composable
fun RequestView(request: JoinRequest) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }.clip(RoundedCornerShape(8.dp)).shadow(elevation = 5.dp),
    ) {
        Column(modifier = Modifier.padding(4.dp)) {
            Text(text = request.projectName, fontSize = 22.sp)
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = request.projectLeader, fontSize = 18.sp)
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = request.userEmail, fontSize = 18.sp)
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = request.userName, fontSize = 18.sp)
        }
    }
}

data class User(
    val username:String,
    val email:String,
)