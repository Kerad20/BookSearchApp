package com.example.booksearchapp.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.booksearchapp.domain.model.BottomNavItem

@Composable
fun BottomNavigationBar(
    navController: NavController
) {

    val items = listOf(
        BottomNavItem(
            "search",
            "Search",
            Icons.Default.Search
        ),
        BottomNavItem(
            "saved",
            "Saved Books",
            Icons.Default.Book
        )
    )

    NavigationBar {

        items.forEach { screen ->

            NavigationBarItem(
                selected = false,
                onClick = {
                    navController.navigate(screen.route)
                },
                icon = { Icon(screen.icon, null) },
                label = { Text(screen.label) }
            )
        }
    }
}