package com.example.booksearchapp.presentation.search

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.booksearchapp.domain.model.UiEvent
import com.example.booksearchapp.presentation.components.BottomNavigationBar
import com.example.booksearchapp.presentation.components.CustomProgressIndicator
import com.example.booksearchapp.presentation.navigation.Routes
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun SearchScreen(navController: NavController) {

    val parentEntry = remember(navController) {
        navController.getBackStackEntry(Routes.searchScreen)
    }

    val vm: SearchViewModel = koinViewModel(
        viewModelStoreOwner = parentEntry
    )

    val state by vm.state.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        vm.events.collect { event ->

            when(event) {
                UiEvent.NavigateToResults -> {
                    navController.navigate(Routes.booksListScreen)
                }

                is UiEvent.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }

    ) {
        paddingValues ->

        Box(modifier = Modifier.fillMaxSize()){

            if (state.isLoading) {
                CustomProgressIndicator()
            }

            Column(
                modifier = Modifier.
                    fillMaxSize().
                padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                SearchInputSection(
                    state.query,
                    state.recentSearches,
                    vm::onQueryChange,
                    {
                        vm.searchBooks(state.query)
                    },
                    {
                        vm.searchBooks(it)
                    },
                    !state.isLoading
                )

            }

        }
    }
}