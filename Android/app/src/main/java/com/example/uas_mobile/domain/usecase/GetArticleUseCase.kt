package com.example.uas_mobile.domain.usecase

import com.example.uas_mobile.domain.models.Article
import com.example.uas_mobile.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val repository: ArticleRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return repository.getAllArticles()
    }
}

class GetArticleByIdUseCase @Inject constructor(
    private val repository: ArticleRepository
) {
    suspend operator fun invoke(id: Int): Article? {
        return repository.getArticleById(id)
    }
}

class GetArticlesByCategoryUseCase @Inject constructor(
    private val repository: ArticleRepository
) {
    operator fun invoke(category: String): Flow<List<Article>> {
        return repository.getArticlesByCategory(category)
    }
}