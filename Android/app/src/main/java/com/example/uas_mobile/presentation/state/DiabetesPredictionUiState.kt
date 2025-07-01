package com.example.uas_mobile.presentation.state

import com.example.uas_mobile.domain.models.DiabetesPredictionResponse

data class DiabetesPredictionUiState(
    val Pregnancies: String = "",
    val Glucose: String = "",
    val BloodPressure: String = "",
    val SkinThickness: String = "",
    val Insulin: String = "",
    val BMI: String = "",
    val DiabetesPedigreeFunction: String = "",
    val Age: String = "",
    val isLoading: Boolean = false,
    val result: DiabetesPredictionResponse? = null,
    val errorMessage: String? = null
)