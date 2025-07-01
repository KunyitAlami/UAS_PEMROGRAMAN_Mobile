package com.example.uas_mobile.domain.usecase

import com.example.uas_mobile.domain.models.DiabetesPredictionRequest
import com.example.uas_mobile.domain.models.DiabetesPredictionResponse
import com.example.uas_mobile.domain.models.Resource
import com.example.uas_mobile.domain.repository.DiabetesPredictionRepository

class PredictDiabetesUseCase (private val repository: DiabetesPredictionRepository){
    suspend operator fun invoke(request: DiabetesPredictionRequest): Resource<DiabetesPredictionResponse>{
        return try {
            validateInput(request)
            repository.predictDiabetes(request)
        }catch (e: IllegalArgumentException){
            Resource.Error(e.message ?: "Input Tidak Valid!")
        }catch (e: Exception){
            Resource.Error("Terjadi error: ${e.message}")
        }
    }

    private fun validateInput(request: DiabetesPredictionRequest) {
        when {
            request.Pregnancies < 0 -> throw IllegalArgumentException("Jumlah kehamilan tidak boleh negatif")
            request.Glucose <= 0 -> throw IllegalArgumentException("Kadar glukosa harus lebih dari 0")
            request.BloodPressure <= 0 -> throw IllegalArgumentException("Tekanan darah harus lebih dari 0")
            request.SkinThickness < 0 -> throw IllegalArgumentException("Ketebalan kulit tidak boleh negatif")
            request.Insulin < 0 -> throw IllegalArgumentException("Insulin tidak boleh negatif")
            request.BMI <= 0 -> throw IllegalArgumentException("BMI harus lebih dari 0")
            request.DiabetesPedigreeFunction < 0 -> throw IllegalArgumentException("Diabetes Pedigree Function tidak boleh negatif")
            request.Age <= 0 -> throw IllegalArgumentException("Umur harus lebih dari 0")
        }
    }
}