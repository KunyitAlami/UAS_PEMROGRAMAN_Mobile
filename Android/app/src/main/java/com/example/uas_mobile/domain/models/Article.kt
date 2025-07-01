package com.example.uas_mobile.domain.models

data class Article(
    val id: Int,
    val title: String,
    val summary: String,
    val content: String,
    val author: String,
    val publishDate: String,
    val category: String,
    val imageUrl: String? = null,
    val readTimeMinutes: Int,
    val source: String
)