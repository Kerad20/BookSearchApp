package com.example.booksearchapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.booksearchapp.presentation.details.BooksListScreen
import com.example.booksearchapp.presentation.saved.SavedScreen
import com.example.booksearchapp.presentation.search.SearchScreen

@Composable
fun Navigation() {
    val navHostController = rememberNavController()

    NavHost(navHostController, startDestination = Routes.searchScreen) {
        composable (Routes.searchScreen) {
            SearchScreen(navHostController)
        }

        composable (Routes.savedScreen) {
            SavedScreen(navHostController)
        }

        composable (Routes.booksListScreen) {
            BooksListScreen(navHostController)
        }
    }
}