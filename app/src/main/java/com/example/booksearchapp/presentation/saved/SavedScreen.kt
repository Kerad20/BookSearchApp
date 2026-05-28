package com.example.booksearchapp.presentation.saved

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.booksearchapp.presentation.components.BottomNavigationBar
import com.example.booksearchapp.presentation.details.BookCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavedScreen(navController: NavController, vm: SavedViewModel = koinViewModel()) {

    val state by vm.state.collectAsState()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }

    ) {
            paddingValues ->

        Box(modifier = Modifier.fillMaxSize()){

            Column(
                modifier = Modifier.
                fillMaxSize().
                padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                LazyColumn {
                    items(state.books) {
                        BookCard(
                            it.title,
                            it.authors.joinToString(),
                            onClick = {}
                        )
                    }
                }

            }

        }
    }
}