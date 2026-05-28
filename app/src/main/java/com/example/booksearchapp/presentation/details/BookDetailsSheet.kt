package com.example.booksearchapp.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.booksearchapp.data.remote.dto.BookDto
import com.example.booksearchapp.domain.model.Book

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailSheet(
    book: Book,
    onSaveOfflineClick: () -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = false)
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {

                Text(book.title, style = MaterialTheme.typography.headlineSmall)

                Spacer(Modifier.height(12.dp))

                SectionTitle("Authors")
                book.authors.forEach {
                    Text("${it.name} (${it.birth_year ?: "?"}–${it.death_year ?: "?"})")
                }

                SectionTitle("Languages")
                Text(book.languages.joinToString())

                SectionTitle("Subjects")
                Text(book.subjects.joinToString())

                SectionTitle("Bookshelves")
                Text(book.bookshelves.joinToString())

                SectionTitle("Downloads")
                Text(book.downloadCount.toString())

                SectionTitle("Formats")
                book.formats.forEach {
                    Text(it.key)
                }
            }

            Button(
                onClick = onSaveOfflineClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Save Offline")
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
    )
}