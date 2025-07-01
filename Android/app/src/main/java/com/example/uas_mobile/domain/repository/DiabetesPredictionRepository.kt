package com.example.uas_mobile.domain.repository

import com.example.uas_mobile.domain.models.DiabetesPredictionRequest
import com.example.uas_mobile.domain.models.DiabetesPredictionResponse
import com.example.uas_mobile.domain.models.Resource

interface DiabetesPredictionRepository {
    suspend fun predictDiabetes(request: DiabetesPredictionRequest): Resource<DiabetesPredictionResponse>
}
