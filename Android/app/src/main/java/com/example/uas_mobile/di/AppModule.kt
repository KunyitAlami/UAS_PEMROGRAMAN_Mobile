package com.example.uas_mobile.di

import android.content.Context
import androidx.room.Room
import com.example.uas_mobile.data.local.database.ArticleDao
import com.example.uas_mobile.data.local.database.ArticleDatabase
import com.example.uas_mobile.data.remote.ApiService
import com.example.uas_mobile.data.repository.ArticleRepositoryImpl
import com.example.uas_mobile.data.repository.DiabetesPredictionRepositoryImpl
import com.example.uas_mobile.domain.repository.ArticleRepository
import com.example.uas_mobile.domain.repository.DiabetesPredictionRepository
import com.example.uas_mobile.domain.usecase.GetArticleByIdUseCase
import com.example.uas_mobile.domain.usecase.GetArticlesByCategoryUseCase
import com.example.uas_mobile.domain.usecase.GetArticlesUseCase
import com.example.uas_mobile.domain.usecase.PredictDiabetesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ApplicationScope

     // untuk modul coroutine
    @Module
    @InstallIn(SingletonComponent::class)
    object CoroutineModule {
        @ApplicationScope
        @Provides
        @Singleton
        fun provideApplicationScope(): CoroutineScope = CoroutineScope(SupervisorJob())
    }

    // untuk modul usecase article
    @Provides
    @Singleton
    fun provideGetArticlesUseCase(repository: ArticleRepository): GetArticlesUseCase =
        GetArticlesUseCase(repository)

    @Provides
    @Singleton
    fun provideGetArticlesByCategoryUseCase(repository: ArticleRepository): GetArticlesByCategoryUseCase =
        GetArticlesByCategoryUseCase(repository)

    @Provides
    @Singleton
    fun provideGetArticleByIdUseCase(repository: ArticleRepository): GetArticleByIdUseCase =
        GetArticleByIdUseCase(repository)


    // untuk modul Room database
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        @ApplicationScope scope: CoroutineScope
    ): ArticleDatabase {
        return ArticleDatabase.getDatabase(context, scope)
    }


    @Provides
    @Singleton
    fun provideArticleDao(db: ArticleDatabase): ArticleDao = db.articleDao()

    @Provides
    @Singleton
    fun provideArticleRepository(dao: ArticleDao): ArticleRepository =
        ArticleRepositoryImpl(dao)

    // untuk modul Retrofit atau untuk FastAPI kita nanti
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideHttpClient(logging: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            //.baseUrl("http://192.168.1.3:8000/") // device fisik-safe kalau makai wifi rumah
            .baseUrl("http://192.168.161.109:8000/") // device fisik kalau makai hospot
            //.baseUrl("http://10.0.2.2:8000/") // device avd kalau makai wifi rumah
            // .baseUrl("http://10.16.36.11:8000/") // device fisik kalau makai wifi ulm hospot
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    // untuk modul  Prediction diabetesnya
    @Provides
    @Singleton
    fun provideDiabetesRepository(apiService: ApiService): DiabetesPredictionRepository =
        DiabetesPredictionRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun providePredictUseCase(repository: DiabetesPredictionRepository): PredictDiabetesUseCase =
        PredictDiabetesUseCase(repository)
}
