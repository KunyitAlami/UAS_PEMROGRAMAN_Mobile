package com.example.uas_mobile.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DiabetesPredictionRequestDto(
    @SerializedName("Pregnancies") val Pregnancies: Float,
    @SerializedName("Glucose") val Glucose: Float,
    @SerializedName("BloodPressure") val BloodPressure: Float,
    @SerializedName("SkinThickness") val SkinThickness: Float,
    @SerializedName("Insulin") val Insulin: Float,
    @SerializedName("BMI") val BMI: Float,
    @SerializedName("DiabetesPedigreeFunction") val DiabetesPedigreeFunction: Float,
    @SerializedName("Age") val Age: Float
)