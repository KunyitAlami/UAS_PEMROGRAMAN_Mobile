package com.example.uas_mobile.presentation.ui.screens.parameter

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ParameterScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Parameter Prediksi Diabetes",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Penjelasan tentang parameter yang digunakan dalam model prediksi risiko diabetes",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Model Information
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Psychology,
                        contentDescription = "Model",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Model LightWeight Deep Learning",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Multi-Layer Perceptron (MLP)",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Model ini dipilih karena kemampuannya dalam menangani data tabular dengan baik dan memberikan akurasi tinggi untuk klasifikasi risiko diabetes. MLP dapat menangkap pola kompleks dalam data medis dengan efisien (Güldoğan, 2020).",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Parameters Section
        Text(
            text = "Parameter Input",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Age
        ParameterCard(
            title = "Usia (Age)",
            icon = Icons.Default.Person,
            description = "Usia adalah faktor risiko utama diabetes tipe 2. Risiko meningkat seiring bertambahnya usia, terutama setelah 45 tahun.",
            riskExplanation = "Semakin tua usia, semakin tinggi risiko diabetes karena penurunan fungsi pankreas dan resistensi insulin yang meningkat.",
            reference = "US Centers for Disease Control and Prevention (CDC), 2024"
        )

        Spacer(modifier = Modifier.height(12.dp))

        // BMI
        ParameterCard(
            title = "BMI (Body Mass Index)",
            icon = Icons.Default.MonitorWeight,
            description = "BMI mengukur proporsi berat badan terhadap tinggi badan. BMI ≥25 meningkatkan risiko diabetes tipe 2.",
            riskExplanation = "Kelebihan berat badan menyebabkan resistensi insulin. BMI >30 (obesitas) memiliki risiko 7x lebih tinggi terkena diabetes.",
            reference = "Preethi Chandrasekaran, 2024"
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Blood Pressure
        ParameterCard(
            title = "Tekanan Darah (Blood Pressure)",
            icon = Icons.Default.Favorite,
            description = "Hipertensi (tekanan darah tinggi) sering terjadi bersamaan dengan diabetes dan meningkatkan risiko komplikasi.",
            riskExplanation = "Tekanan darah >140/90 mmHg meningkatkan risiko diabetes 2x lipat. Keduanya memiliki faktor risiko yang sama.",
            reference = "China Health and Retirement Longitudinal Study, 2022"
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Glucose Level
        ParameterCard(
            title = "Kadar Glukosa",
            icon = Icons.Default.Bloodtype,
            description = "Kadar glukosa darah puasa normal <100 mg/dL. Prediabetes: 100-125 mg/dL. Diabetes: ≥126 mg/dL.",
            riskExplanation = "Kadar glukosa tinggi menunjukkan gangguan metabolisme gula dan merupakan indikator langsung risiko diabetes.",
            reference = "American Diabetes Association Standards, 2025"
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Family History
        ParameterCard(
            title = "Riwayat Keluarga",
            icon = Icons.Default.FamilyRestroom,
            description = "Memiliki keluarga dengan diabetes tipe 2 meningkatkan risiko 2-6x lipat tergantung hubungan kekerabatan.",
            riskExplanation = "Faktor genetik berperan 40-50% dalam risiko diabetes. Kombinasi gen dan lingkungan menentukan manifestasi penyakit.",
            reference = "Diabetes UK, 2023"
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Physical Activity
        ParameterCard(
            title = "Aktivitas Fisik",
            icon = Icons.Default.DirectionsRun,
            description = "Aktivitas fisik teratur (≥150 menit/minggu) dapat mengurangi risiko diabetes hingga 30-40%.",
            riskExplanation = "Olahraga meningkatkan sensitivitas insulin dan membantu kontrol berat badan, dua faktor kunci pencegahan diabetes.",
            reference = " Diabetes Research and Clinical Practice, 2024"
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun ParameterCard(
    title: String,
    icon: ImageVector,
    description: String,
    riskExplanation: String,
    reference: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header with icon and title
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Description
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Risk explanation
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text(
                        text = "Pengaruh Risiko:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = riskExplanation,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Reference
            Text(
                text = "Referensi: $reference",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Light
            )
        }
    }
}