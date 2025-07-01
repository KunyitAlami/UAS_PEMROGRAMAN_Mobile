package com.example.uas_mobile.domain.models

data class DiabetesPredictionRequest(
    val Pregnancies: Float,
    val Glucose: Float,
    val BloodPressure: Float,
    val SkinThickness: Float,
    val Insulin: Float,
    val BMI: Float,
    val DiabetesPedigreeFunction: Float,
    val Age: Float
)