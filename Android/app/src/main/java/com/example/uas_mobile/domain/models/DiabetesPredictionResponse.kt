package com.example.uas_mobile.domain.models

data class DiabetesPredictionResponse(
    val probability: Double,
    val message: String
)