package com.example.uas_mobile.data.remote

import com.example.uas_mobile.data.remote.dto.DiabetesPredictionRequestDto
import com.example.uas_mobile.data.remote.dto.DiabetesPredictionResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("predict")
    suspend fun predictDiabetes(@Body request: DiabetesPredictionRequestDto): Response<DiabetesPredictionResponseDto>
}