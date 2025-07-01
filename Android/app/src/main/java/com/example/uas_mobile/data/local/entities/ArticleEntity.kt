package com.example.uas_mobile.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
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