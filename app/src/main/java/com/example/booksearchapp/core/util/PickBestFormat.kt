package com.example.booksearchapp.core.util

fun pickBestFormat(formats: Map<String, String>): Pair<String, String>? {

    val preferred = listOf(
        "application/epub+zip" to "epub",
        "text/plain; charset=utf-8" to "txt",
        "text/plain" to "txt",
        "text/html" to "html"
    )

    return preferred.firstNotNullOfOrNull { (mime, ext) ->
        formats[mime]?.let { url ->
            ext to url
        }
    }
}