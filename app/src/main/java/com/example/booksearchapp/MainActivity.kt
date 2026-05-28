package com.example.booksearchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.booksearchapp.presentation.navigation.Navigation
import com.example.booksearchapp.ui.theme.BookSearchAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookSearchAppTheme {
                Navigation()
            }
        }
    }
}