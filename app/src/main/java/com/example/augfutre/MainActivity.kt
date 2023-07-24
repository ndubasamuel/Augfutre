package com.example.augfutre

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.augfutre.Navigation.Navigation
import com.example.augfutre.ui.theme.AugfutreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AugfutreShopApp ()

        }
    }

@Composable
fun AugfutreShopApp () {
    AugfutreTheme  {
        Surface(color = MaterialTheme.colors.background) {
            Navigation()

        }
    }
   }

}
