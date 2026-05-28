package com.example.booksearchapp.presentation.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.booksearchapp.presentation.components.BottomNavigationBar
import com.example.booksearchapp.presentation.navigation.Routes
import com.example.booksearchapp.presentation.search.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun BooksListScreen(navController: NavController){

    val parentEntry = remember(navController) {
        navController.getBackStackEntry(Routes.searchScreen)
    }

    val vm: SearchViewModel = koinViewModel(
        viewModelStoreOwner = parentEntry
    )

    val state by vm.state.collectAsState()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }

    ) {
        paddingValues ->

        Box {
            LazyColumn(
                Modifier.padding(paddingValues)
            ) {
                items(state.books) {
                    BookCard(
                        it.title,
                        it.authors.joinToString(),
                        it.languages.joinToString(),
                        onClick = {
                            vm.selectBook(it)
                        }
                    )
                }
            }
        }

        if (state.selectedBook != null) {
            BookDetailSheet(
                state.selectedBook!!, {vm.selectBook(null)}
            )
        }

    }

}