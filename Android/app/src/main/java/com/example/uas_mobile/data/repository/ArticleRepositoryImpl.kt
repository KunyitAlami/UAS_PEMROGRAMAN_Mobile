package com.example.uas_mobile.data.repository

import com.example.uas_mobile.data.local.database.ArticleDao
import com.example.uas_mobile.data.local.entities.ArticleEntity
import com.example.uas_mobile.domain.models.Article
import com.example.uas_mobile.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepositoryImpl @Inject constructor(
    private val articleDao: ArticleDao
) : ArticleRepository {

    override fun getAllArticles(): Flow<List<Article>> {
        return articleDao.getAllArticles().map { entities ->
            entities.map { it.toArticle() }
        }
    }

    override suspend fun getArticleById(id: Int): Article? {
        return articleDao.getArticleById(id)?.toArticle()
    }

    override fun getArticlesByCategory(category: String): Flow<List<Article>> {
        return articleDao.getArticlesByCategory(category).map { entities ->
            entities.map { it.toArticle() }
        }
    }
}

private fun ArticleEntity.toArticle(): Article {
    return Article(
        id = this.id,
        title = this.title,
        summary = this.summary,
        content = this.content,
        author = this.author,
        publishDate = this.publishDate,
        category = this.category,
        imageUrl = this.imageUrl,
        readTimeMinutes = this.readTimeMinutes,
        source = this.source
    )
}