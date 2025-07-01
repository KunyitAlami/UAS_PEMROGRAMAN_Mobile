package com.example.uas_mobile.data.repository

import com.example.uas_mobile.data.remote.ApiService
import com.example.uas_mobile.data.remote.dto.DiabetesPredictionRequestDto
import com.example.uas_mobile.data.remote.dto.DiabetesPredictionResponseDto
import com.example.uas_mobile.domain.models.DiabetesPredictionRequest
import com.example.uas_mobile.domain.models.DiabetesPredictionResponse
import com.example.uas_mobile.domain.models.Resource
import com.example.uas_mobile.domain.repository.DiabetesPredictionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class DiabetesPredictionRepositoryImpl(private val apiService: ApiService) : DiabetesPredictionRepository {

    override suspend fun predictDiabetes(request: DiabetesPredictionRequest): Resource<DiabetesPredictionResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val requestDto = request.toDto()
                val response = apiService.predictDiabetes(requestDto)

                when {
                    response.isSuccessful -> {
                        val responseDto = response.body()
                        if (responseDto != null) {
                            Resource.Success(responseDto.toDomain())
                        } else {
                            Resource.Error("Response kosong dari server")
                        }
                    }
                    response.code() == 400 -> {
                        Resource.Error("Data input tidak valid")
                    }
                    response.code() == 500 -> {
                        Resource.Error("Server mengalami masalah, coba lagi nanti")
                    }
                    else -> {
                        Resource.Error("Terjadi kesalahan: ${response.code()}")
                    }
                }
            } catch (e: UnknownHostException) {
                Resource.Error("Tidak ada koneksi internet", e)
            } catch (e: ConnectException) {
                Resource.Error("Gagal terhubung ke server", e)
            } catch (e: SocketTimeoutException) {
                Resource.Error("Koneksi timeout, coba lagi", e)
            } catch (e: Exception) {
                Resource.Error("Terjadi kesalahan tidak terduga: ${e.message}", e)
            }
        }
    }
}

fun DiabetesPredictionRequest.toDto() = DiabetesPredictionRequestDto(
    Pregnancies = Pregnancies,
    Glucose = Glucose,
    BloodPressure = BloodPressure,
    SkinThickness = SkinThickness,
    Insulin = Insulin,
    BMI = BMI,
    DiabetesPedigreeFunction = DiabetesPedigreeFunction,
    Age = Age
)

fun DiabetesPredictionResponseDto.toDomain() = DiabetesPredictionResponse(
    probability = probability,
    message = message
)
