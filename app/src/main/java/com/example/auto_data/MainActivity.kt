package com.example.auto_data

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.auto_data.navigation.Main_Navigation
import com.example.auto_data.ui.theme.AutoDataTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutoDataTheme {
                Main_Navigation()
            }
        }
    }
}


