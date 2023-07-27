package com.example.augfutre.View


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.augfutre.Model.AuthViewModel
import com.example.augfutre.Model.ModelUseCase.State.UiEvents
import com.example.augfutre.Navigation.Screen
import com.example.augfutre.R
import kotlinx.coroutines.flow.collectLatest


@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    val loginState = viewModel.loginState.value
    val scaffoldState = rememberScaffoldState()
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvents.SnackbarEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        duration = SnackbarDuration.Short
                    )
                }else ->{}
            }
        }

        }
        Scaffold(
            scaffoldState = scaffoldState
        ) {


            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                        horizontalAlignment = Alignment . CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(360.dp)
                        .clip(
                            shape = RoundedCornerShape(
                                bottomStart = 25.dp,
                                bottomEnd = 25.dp
                            )
                        )
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color(0xFFB42B93),
                                    Color(0xFF000000)
                                ),
                                radius = 415f
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .size(280.dp)
                            .offset(y = (-20.dp)),
                        painter = painterResource(id = R.drawable.logchair),
                        contentDescription = "Background Image"
                    )
                }
                Card(
                    modifier = Modifier
                        .offset(y = -20.dp)
                        .width(290.dp),
                    shape = RoundedCornerShape(25.dp),
                    elevation = 15.dp
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 30.dp)
                            .padding(top = 30.dp, bottom = 50.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (loginState.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                        }
                    }
                    Text(
                        text = "Augfutre",
                        style = MaterialTheme.typography.h1,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = emailState.text,
                        onValueChange = {
                            viewModel.setEmail(it)
                        },
                        placeholder = {
                            Text(text = "Enter Email")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                        ),
                        isError = emailState.error != null
                    )
                    if (emailState.error != "") {
                        Text(
                            text = emailState.error ?: "",
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.error,
                            textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = passwordState.text,
                        onValueChange = {
                            viewModel.setPassword(it)
                        },
                        label = { Text("Password") },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardActions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        isError = passwordState.error != null,
                        traillingIcon = {
                            val image = if (passwordVisible)
                                Icons.Filled.Visibility
                            else Icons.Filled.VisibilityOff
                            val description =
                                if (passwordVisible) "Hide password" else "Show password"
                            //Toggle button for hiding password
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(imageVector = image, description)
                            }
                        }
                    )
                    if (passwordState.error != "") {
                        Text(
                            text = passwordState.error ?: "",
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.error,
                            textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
@Composable
fun GradientButton( navController: NavController,
                modifier: Modifier = Modifier,
            text: String,
            textColor: Color,
            gradient: Brush,
            onClick: () -> Unit
            ) {
    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = {
            navController.navigate(Screen.SignUpScreen)
        },
        modifier = modifier.height(48.dp),
        shape = RoundedCornerShape(40.dp),
        elevation = ButtonDefaults.elevation(defaultElevation = 10.dp, pressedElevation = 5.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(gradient)
                .padding(horizontal = 32.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Log in",
                color = Color.White,
                gradient = Brush.horizontalGradient(
                    listOf(
                        Color(0XFFC4A0F4),
                        Color(0XFFCF4CB9),
                        Color(0xFFE60B41)
                    )
                )
            )
        }
    }
}

    @Preview
    @Composable
    fun PreviewGradientButton() {
        GradientButton(
            text = "Log in",
            textColor = Color.White,
            gradient = Brush.horizontalGradient(
                listOf(
                    Color(0XFFC4A0F4),
                    Color(0XFFCF4CB9),
                    Color(0xFFE60B41)
                )
            )
        )
    }