package com.example.uas_mobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_mobile.domain.models.Article
import com.example.uas_mobile.domain.usecase.GetArticleByIdUseCase
import com.example.uas_mobile.domain.usecase.GetArticlesUseCase
import com.example.uas_mobile.domain.usecase.GetArticlesByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase,
    private val getArticleByIdUseCase: GetArticleByIdUseCase,
    private val getArticlesByCategoryUseCase: GetArticlesByCategoryUseCase
) : ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles.asStateFlow()

    private val _selectedArticle = MutableStateFlow<Article?>(null)
    val selectedArticle: StateFlow<Article?> = _selectedArticle.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _selectedCategory = MutableStateFlow("All")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    val categories = listOf("All", "Pencegahan", "Nutrisi", "Olahraga", "Komplikasi", "Teknologi")

    init {
        loadArticles()
    }

    fun loadArticles() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                getArticlesUseCase().collect { articleList ->
                    _articles.value = articleList
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }

    fun loadArticleById(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val article = getArticleByIdUseCase(id)
                _selectedArticle.value = article
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }

    fun filterByCategory(category: String) {
        _selectedCategory.value = category
        viewModelScope.launch {
            _isLoading.value = true
            try {
                if (category == "All") {
                    getArticlesUseCase().collect { articleList ->
                        _articles.value = articleList
                        _isLoading.value = false
                    }
                } else {
                    getArticlesByCategoryUseCase(category).collect { articleList ->
                        _articles.value = articleList
                        _isLoading.value = false
                    }
                }
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }
}