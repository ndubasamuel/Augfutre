package com.example.augfutre.View


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.augfutre.Components.CustomInputField
import com.example.augfutre.R


@Composable
fun LoginScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        val inputValueID = remember {
            mutableStateOf("")
        }
        val inputValuePass = remember {
            mutableStateOf("")
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
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
                Image (
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
                    Text(
                        text = "Augfutre",
                        style = MaterialTheme.typography.h1,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    CustomInputField(inputValue = inputValueID, type = "uid")
                    CustomInputField(inputValue = inputValuePass, type = "password")
                }
            }

        }
    }

}