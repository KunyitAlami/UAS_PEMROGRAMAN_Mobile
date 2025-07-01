package com.example.uas_mobile.domain.repository

import com.example.uas_mobile.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    fun getAllArticles(): Flow<List<Article>>
    suspend fun getArticleById(id: Int): Article?
    fun getArticlesByCategory(category: String): Flow<List<Article>>
}