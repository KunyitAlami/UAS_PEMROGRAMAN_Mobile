package com.example.uas_mobile.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uas_mobile.presentation.viewmodel.DiabetesPredictionViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiabetesPredictionScreen(
    viewModel: DiabetesPredictionViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        Card(
            modifier = Modifier.fillMaxWidth() .padding(4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "🏥 Prediksi Diabetes",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "Isi data kesehatan Anda untuk prediksi risiko diabetes",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                )
            }
        }

        // Form Fields
        OutlinedTextField(
            value = uiState.Pregnancies,
            onValueChange = viewModel::updatePregnancies,
            label = { Text("Jumlah Kehamilan") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            supportingText = { Text("Contoh: 0, 1, 2, dst. (Pilih 0 jika anda Laki-Laki)") }
        )

        OutlinedTextField(
            value = uiState.Glucose,
            onValueChange = viewModel::updateGlucose,
            label = { Text("Kadar Glukosa (mg/dL)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            supportingText = { Text("Normal: 70-140 mg/dL") }
        )

        OutlinedTextField(
            value = uiState.BloodPressure,
            onValueChange = viewModel::updateBloodPressure,
            label = { Text("Tekanan Darah (mmHg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            supportingText = { Text("Sistolik, contoh: 120") }
        )

        OutlinedTextField(
            value = uiState.SkinThickness,
            onValueChange = viewModel::updateSkinThickness,
            label = { Text("Ketebalan Kulit (mm)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            supportingText = { Text("Lipatan kulit tricep, contoh: 45") }
        )

        OutlinedTextField(
            value = uiState.Insulin,
            onValueChange = viewModel::updateInsulin,
            label = { Text("Insulin (μU/mL)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            supportingText = { Text("Kadar insulin serum, contoh: 180") }
        )

        OutlinedTextField(
            value = uiState.BMI,
            onValueChange = viewModel::updateBmi,
            label = { Text("BMI (Body Mass Index)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth(),
            supportingText = { Text("Contoh: 25.6") }
        )

        OutlinedTextField(
            value = uiState.DiabetesPedigreeFunction,
            onValueChange = viewModel::updateDiabetesPedigreeFunction,
            label = { Text("Diabetes Pedigree Function") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth(),
            supportingText = { Text("Riwayat keluarga, contoh: 0.3") }
        )

        OutlinedTextField(
            value = uiState.Age,
            onValueChange = viewModel::updateAge,
            label = { Text("Umur (tahun)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            supportingText = { Text("Contoh: 30") }
        )

        // Predict Button
        Button(
            onClick = viewModel::predictDiabetes,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            enabled = !uiState.isLoading
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = if (uiState.isLoading) "Memproses..." else "🔍 Prediksi Diabetes",
                fontSize = 16.sp
            )
        }

        // Error Message
        uiState.errorMessage?.let { error ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "⚠️ $error",
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier.weight(1f)
                    )
                    TextButton(
                        onClick = viewModel::clearError
                    ) {
                        Text("Tutup")
                    }
                }
            }
        }

        // Result
        uiState.result?.let { result ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                contentAlignment = Alignment.TopCenter
            )
            {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .wrapContentHeight(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (result.probability < 0.5)
                            MaterialTheme.colorScheme.secondaryContainer
                        else
                            MaterialTheme.colorScheme.errorContainer
                    ),
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = if (result.probability < 0.5) "Hasil Prediksi" else "Hasil Prediksi",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = result.message,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Medium,
                                    modifier = Modifier.fillMaxWidth()
                        )

                        Text(
                            text = "Probabilitas: ${String.format("%.2f", result.probability * 100)}%",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = viewModel::clearResult,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            )
                        ) {
                            Text("Prediksi Ulang")
                        }

                    }
                }

            }

        }
    }
}
