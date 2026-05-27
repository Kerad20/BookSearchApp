package com.example.booksearchapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchInputSection(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    isSearchEnabled: Boolean
) {

    Column {

        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = {
                Text("Search books or authors")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onSearchClick,
            enabled = isSearchEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search")
        }
    }
}
