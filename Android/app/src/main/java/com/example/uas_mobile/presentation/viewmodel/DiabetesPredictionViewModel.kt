package com.example.uas_mobile.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_mobile.domain.models.DiabetesPredictionRequest
import com.example.uas_mobile.domain.models.Resource
import com.example.uas_mobile.domain.usecase.PredictDiabetesUseCase
import com.example.uas_mobile.presentation.state.DiabetesPredictionUiState
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiabetesPredictionViewModel @Inject constructor (private val predictDiabetesUseCase: PredictDiabetesUseCase) : ViewModel() {

    var uiState by mutableStateOf(DiabetesPredictionUiState())
        private set

    fun updatePregnancies(value: String) {
        uiState = uiState.copy(Pregnancies = value, errorMessage = null)
    }

    fun updateGlucose(value: String) {
        uiState = uiState.copy(Glucose = value, errorMessage = null)
    }

    fun updateBloodPressure(value: String) {
        uiState = uiState.copy(BloodPressure = value, errorMessage = null)
    }

    fun updateSkinThickness(value: String) {
        uiState = uiState.copy(SkinThickness = value, errorMessage = null)
    }

    fun updateInsulin(value: String) {
        uiState = uiState.copy(Insulin = value, errorMessage = null)
    }

    fun updateBmi(value: String) {
        uiState = uiState.copy(BMI = value, errorMessage = null)
    }

    fun updateDiabetesPedigreeFunction(value: String) {
        uiState = uiState.copy(DiabetesPedigreeFunction = value, errorMessage = null)
    }

    fun updateAge(value: String) {
        uiState = uiState.copy(Age = value, errorMessage = null)
    }

    fun predictDiabetes() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null, result = null)

            try {
                val request = DiabetesPredictionRequest(
                    Pregnancies = uiState.Pregnancies.toFloatOrNull() ?: 0f,
                    Glucose = uiState.Glucose.toFloatOrNull() ?: 0f,
                    BloodPressure = uiState.BloodPressure.toFloatOrNull() ?: 0f,
                    SkinThickness = uiState.SkinThickness.toFloatOrNull() ?: 0f,
                    Insulin = uiState.Insulin.toFloatOrNull() ?: 0f,
                    BMI = uiState.BMI.toFloatOrNull() ?: 0f,
                    DiabetesPedigreeFunction = uiState.DiabetesPedigreeFunction.toFloatOrNull() ?: 0f,
                    Age = uiState.Age.toFloatOrNull() ?: 0f
                )

                when (val result = predictDiabetesUseCase(request)) {
                    is Resource.Success -> {
                        uiState = uiState.copy(
                            isLoading = false,
                            result = result.data,
                            errorMessage = null
                        )
                    }
                    is Resource.Error -> {
                        uiState = uiState.copy(
                            isLoading = false,
                            errorMessage = result.message,
                            result = null
                        )
                    }
                    is Resource.Loading -> {
                    }
                }
            } catch (e: NumberFormatException) {
                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = "Pastikan semua field diisi dengan angka yang valid"
                )
            }
        }
    }

    fun clearError() {
        uiState = uiState.copy(errorMessage = null)
    }

    fun clearResult() {
        uiState = uiState.copy(result = null)
    }
}