package com.example.augfutre.Components


import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Badge
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun CustomInputField(
    type: String = "uid",
    inputValue: MutableState<String> = remember {
                                                mutableStateOf("")

    },
    modifier: Modifier = Modifier,
    iconColor: MutableState<Color> = remember {
        mutableStateOf(Color(0xFFC9C9C9))
    },
    seePasswordToggle: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
){
    val focusRequester = FocusRequester()
    val customTextSelectionColors = TextSelectionColors(
        handleColor = Color(0xFFF48FB1),
        backgroundColor = Color(0xA1F48FB1)
    )
    val rippleColor = rememberRipple(color = Color(0xFFF48FB1))

    CompositionLocalProvider(
        LocalTextSelectionColors provides customTextSelectionColors,
        LocalIndication provides rippleColor
    ){
        OutlinedTextField(
            value = inputValue.value,
            onValueChange = { inputValue.value = it},
        modifier = Modifier
            .height(72.dp)
            .focusRequester(focusRequester)
            .onFocusChanged {
                iconColor.value = if (it.isFocused) Color(0xFFF06292)
                else Color(0xFFFC9C9C9)
            },
            label = {
                if (type == "uid")
                    Text(text = "Username")
                else
                    Text(text = "Password")
            },
            leadingIcon = {
                if (type == "uid") {
                    Icon(
                        imageVector = Icons.Rounded.Badge,
                        contentDescription = "Login"
                    )
                } else {
                    Icon(
                        imageVector = Icons.Rounded.Lock,
                        contentDescription = "Password"
                    )
                }
            },
            visualTransformation =
                if (type == "password") {
                    if (!seePasswordToggle.value) {
                        PasswordVisualTransformation()
                    } else {
                        VisualTransformation.None
                    }
                } else {
                    VisualTransformation.None
                },
            trailingIcon = {
                if (type == "password") Icon(
                    imageVector = if (seePasswordToggle.value) Icons.Rounded.Visibility
                else Icons.Rounded.VisibilityOff,
                    contentDescription = "Trailing Icon",
                    modifier = Modifier
                        .size(20.dp)
                        .clip(shape = RoundedCornerShape(4.dp))
                        .clickable { seePasswordToggle.value = !seePasswordToggle.value }

                ) else {

                }
            },
            shape = RoundedCornerShape(25.dp),
            singleLine = true,
            colors = TextFieldDefaults
                .outlinedTextFieldColors(
                    unfocusedBorderColor = Color(0xFFC9C9C9),
                    focusedBorderColor = Color(0xFFF48FB1),
                    leadingIconColor = iconColor.value,
                    trailingIconColor = iconColor.value,
                    cursorColor = Color(0xFFF48FB1),
                    focusedLabelColor = Color(0xFFF48FB1)
                ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = if (type == "uid") ImeAction.Next else ImeAction.Go
            )
            )
    }
}