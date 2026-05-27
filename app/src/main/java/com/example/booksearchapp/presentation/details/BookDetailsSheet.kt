package com.example.booksearchapp.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.booksearchapp.domain.model.Book

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailSheet(
    book: Book,
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
                .padding(16.dp)
        ) {

            Text(
                text = book.title,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(Modifier.height(12.dp))

            SectionTitle("Authors")
            book.authors.forEach { author ->
                Text(
                    "${author.name} " +
                            "(${author.birth_year ?: "?"}–${author.death_year ?: "?"})"
                )
            }

            SectionTitle("Languages")
            Text(book.languages.joinToString())

            SectionTitle("Subjects")
            Text(book.subjects.joinToString())

            SectionTitle("Bookshelves")
            Text(book.bookshelves.joinToString())

            SectionTitle("Downloads")
            Text(book.download_count.toString())

            SectionTitle("Formats")
            book.formats.forEach {
                Text(it.key)
            }

            Spacer(Modifier.height(24.dp))
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