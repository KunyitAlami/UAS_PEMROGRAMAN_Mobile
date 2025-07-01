package com.example.uas_mobile.data.local.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import android.content.Context
import com.example.uas_mobile.data.local.entities.ArticleEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [ArticleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {
        @Volatile
        private var INSTANCE: ArticleDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): ArticleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDatabase::class.java,
                    "article_database"
                )
                    .addCallback(ArticleDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class ArticleDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.articleDao())
                }
            }
        }

        suspend fun populateDatabase(articleDao: ArticleDao) {
            val articles = listOf(
                ArticleEntity(
                    title = "Diabetes Tipe 2: Penyebab, Gejala, dan Pencegahan",
                    summary = "Memahami diabetes tipe 2 sebagai penyakit metabolik yang paling umum dan cara mencegahnya melalui gaya hidup sehat.",
                    content = """
                        Diabetes tipe 2 adalah kondisi kronis yang mempengaruhi cara tubuh mengatur dan menggunakan gula (glukosa) sebagai bahan bakar. Dengan kondisi ini, tubuh Anda tidak dapat menggunakan insulin secara efektif atau tidak memproduksi cukup insulin.
                        
                        Penyebab Diabetes Tipe 2:
                        1. Resistensi insulin - sel tidak merespons insulin dengan normal
                        2. Produksi insulin tidak mencukupi
                        3. Faktor genetik dan keturunan
                        4. Obesitas dan kelebihan berat badan
                        5. Gaya hidup tidak aktif
                        
                        Gejala Utama:
                        - Sering haus dan buang air kecil
                        - Kelelahan berlebihan
                        - Penglihatan kabur
                        - Luka yang lambat sembuh
                        - Penurunan berat badan tanpa sebab
                          
                        Pencegahan:
                        - Menjaga berat badan ideal
                        - Berolahraga secara teratur
                        - Mengonsumsi makanan sehat dan seimbang
                        - Menghindari makanan tinggi gula dan lemak jenuh
                        - Rutin melakukan pemeriksaan kesehatan
                        
                        Diabetes tipe 2 dapat dicegah dengan mengubah gaya hidup menjadi lebih sehat. Konsultasikan dengan dokter untuk mendapatkan rencana pencegahan yang tepat.
                    """.trimIndent(),
                    author = "Dr. Sarah Johnson",
                    publishDate = "2024-01-15",
                    category = "Pencegahan",
                    readTimeMinutes = 5,
                    source = "Journal of Diabetes Care"
                ),
                ArticleEntity(
                    title = "Peran Diet dalam Pengelolaan Diabetes",
                    summary = "Panduan lengkap mengenai pola makan yang tepat untuk penderita diabetes dan cara mengatur kadar gula darah.",
                    content = """
                        Diet memainkan peran krusial dalam pengelolaan diabetes. Pemilihan makanan yang tepat dapat membantu mengontrol kadar gula darah dan mencegah komplikasi.
                        
                        Prinsip Diet untuk Diabetes:
                        1. Mengontrol porsi makan
                        2. Memilih karbohidrat kompleks
                        3. Meningkatkan konsumsi serat
                        4. Membatasi gula dan lemak jenuh
                        5. Makan secara teratur
                        
                        Makanan yang Dianjurkan:
                        - Sayuran hijau dan berwarna
                        - Buah-buahan segar dengan indeks glikemik rendah
                        - Protein tanpa lemak (ikan, ayam tanpa kulit)
                        - Biji-bijian utuh
                        - Kacang-kacangan dan biji-bijian
                        
                        Makanan yang Harus Dibatasi:
                        - Gula dan pemanis buatan
                        - Makanan olahan dan kemasan
                        - Minuman bersoda dan jus buah kemasan
                        - Gorengan dan makanan tinggi lemak
                        - Roti putih dan nasi putih
                        
                        Tips Praktis:
                        - Gunakan metode piring diabetes (1/2 sayuran, 1/4 protein, 1/4 karbohidrat)
                        - Makan dalam porsi kecil tapi sering
                        - Selalu baca label nutrisi
                        - Konsultasi dengan ahli gizi
                        
                        Mengelola diabetes melalui diet memerlukan konsistensi dan pemahaman yang baik tentang nilai gizi makanan.
                    """.trimIndent(),
                    author = "Prof. Michael Chen",
                    publishDate = "2024-01-20",
                    category = "Nutrisi",
                    readTimeMinutes = 7,
                    source = "Nutrition & Diabetes Journal"
                ),
                ArticleEntity(
                    title = "Olahraga dan Aktivitas Fisik untuk Penderita Diabetes",
                    summary = "Manfaat olahraga untuk kontrol gula darah dan rekomendasi jenis aktivitas fisik yang aman untuk penderita diabetes.",
                    content = """
                        Aktivitas fisik adalah salah satu pilar utama dalam pengelolaan diabetes. Olahraga teratur dapat membantu mengontrol kadar gula darah, meningkatkan sensitivitas insulin, dan mengurangi risiko komplikasi.
                        
                        Manfaat Olahraga untuk Diabetes:
                        1. Meningkatkan sensitivitas insulin
                        2. Menurunkan kadar gula darah
                        3. Membantu penurunan berat badan
                        4. Mengurangi risiko penyakit jantung
                        5. Meningkatkan mood dan kualitas hidup
                        
                        Jenis Olahraga yang Dianjurkan:
                        
                        Aerobik (150 menit/minggu):
                        - Jalan cepat
                        - Berenang
                        - Bersepeda
                        - Jogging ringan
                        - Senam aerobik
                        
                        Latihan Kekuatan (2-3x/minggu):
                        - Angkat beban ringan
                        - Resistance band
                        - Push-up dan sit-up
                        - Yoga
                        
                        Precautions dan Tips Keamanan:
                        - Cek gula darah sebelum dan sesudah olahraga
                        - Bawa camilan untuk mengatasi hipoglikemia
                        - Gunakan sepatu yang nyaman
                        - Mulai dengan intensitas rendah
                        - Konsultasi dengan dokter sebelum memulai program
                        
                        Waktu Olahraga yang Ideal:
                        - 30-60 menit setelah makan
                        - Hindari olahraga saat puasa
                        - Konsistensi lebih penting dari intensitas
                        
                        Kombinasi olahraga aerobik dan latihan kekuatan memberikan manfaat optimal untuk pengelolaan diabetes.
                    """.trimIndent(),
                    author = "Dr. Lisa Rodriguez",
                    publishDate = "2024-01-25",
                    category = "Olahraga",
                    readTimeMinutes = 6,
                    source = "Sports Medicine & Diabetes"
                ),
                ArticleEntity(
                    title = "Komplikasi Diabetes dan Cara Pencegahannya",
                    summary = "Mengenal berbagai komplikasi yang dapat terjadi akibat diabetes dan strategi pencegahan yang efektif.",
                    content = """
                        Diabetes yang tidak terkontrol dapat menyebabkan berbagai komplikasi serius. Pemahaman tentang komplikasi ini penting untuk motivasi dalam pengelolaan diabetes yang baik.
                        
                        Komplikasi Akut:
                        
                        Hipoglikemia (gula darah rendah):
                        - Gejala: gemetar, berkeringat, pusing
                        - Penanganan: konsumsi gula cepat
                        
                        Hiperglikemia (gula darah tinggi):
                        - Gejala: haus berlebihan, sering buang air kecil
                        - Penanganan: insulin atau obat diabetes
                        
                        Komplikasi Kronis:
                        
                        Penyakit Kardiovaskular:
                        - Risiko serangan jantung 2-4x lebih tinggi
                        - Stroke dan penyakit pembuluh darah
                        
                        Nefropati Diabetik (kerusakan ginjal):
                        - 20-30% penderita diabetes mengalami
                        - Dapat berujung pada gagal ginjal
                        
                        Retinopati Diabetik (kerusakan mata):
                        - Penyebab utama kebutaan pada orang dewasa
                        - Perlu pemeriksaan mata rutin
                        
                        Neuropati Diabetik (kerusakan saraf):
                        - Kesemutan dan mati rasa
                        - Gangguan pencernaan dan fungsi seksual
                        
                        Kaki Diabetik:
                        - Luka yang sulit sembuh
                        - Risiko amputasi
                        
                        Strategi Pencegahan:
                        1. Kontrol gula darah (HbA1c <7%)
                        2. Manajemen tekanan darah (<140/90 mmHg)
                        3. Kontrol kolesterol
                        4. Berhenti merokok
                        5. Pemeriksaan rutin
                        
                        Pemeriksaan Rutin yang Diperlukan:
                        - HbA1c setiap 3-6 bulan
                        - Pemeriksaan mata tahunan
                        - Tes fungsi ginjal
                        - Pemeriksaan kaki
                        
                        Pencegahan komplikasi diabetes memerlukan pendekatan holistik dan konsistensi dalam pengelolaan.
                    """.trimIndent(),
                    author = "Dr. James Wilson",
                    publishDate = "2024-02-01",
                    category = "Komplikasi",
                    readTimeMinutes = 8,
                    source = "Diabetes Complications Research"
                ),
                ArticleEntity(
                    title = "Teknologi Terbaru dalam Monitoring Diabetes",
                    summary = "Inovasi teknologi kesehatan untuk memudahkan monitoring dan pengelolaan diabetes di era digital.",
                    content = """
                        Teknologi modern telah mengubah cara penderita diabetes memantau dan mengelola kondisi mereka. Berbagai inovasi menawarkan kemudahan dan akurasi yang lebih baik.
                        
                        Continuous Glucose Monitoring (CGM):
                        - Pemantauan gula darah 24/7
                        - Alarm untuk hipoglikemia/hiperglikemia
                        - Data trend dan pola gula darah
                        - Tidak perlu tusuk jari berulang
                        
                        Aplikasi Mobile untuk Diabetes:
                        
                        Fitur Utama:
                        - Log gula darah dan makanan
                        - Reminder obat dan pemeriksaan
                        - Kalkulator karbohidrat
                        - Grafik dan analisis data
                        - Telemedicine dengan dokter
                        
                        Aplikasi Populer:
                        - MySugr
                        - Glucose Buddy
                        - Diabetes:M
                        - Carb Manager
                        
                        Smart Insulin Pens:
                        - Pelacakan dosis insulin otomatis
                        - Reminder waktu injeksi
                        - Sinkronisasi dengan smartphone
                        - Analisis pola penggunaan insulin
                        
                        Wearable Devices:
                        - Smartwatch dengan monitoring kesehatan
                        - Fitness tracker untuk aktivitas fisik
                        - Monitoring heart rate dan stress
                        - Integrasi dengan aplikasi diabetes
                        
                        Artificial Intelligence dalam Diabetes:
                        - Prediksi gula darah
                        - Rekomendasi dosis insulin
                        - Personalisasi rencana perawatan
                        - Deteksi pola dan anomali
                        
                        Telemedicine dan Konsultasi Online:
                        - Konsultasi dengan endokrinologis  
                        - Review data diabetes secara remote
                        - Penyesuaian obat tanpa kunjungan fisik
                        - Edukasi dan support group online
                        
                        Future Technologies:
                        - Closed-loop insulin delivery systems
                        - Non-invasive glucose monitoring
                        - Smart contact lenses
                        - Nanotechnology applications
                        
                        Tips Memilih Teknologi Diabetes:
                        1. Sesuaikan dengan budget dan kebutuhan
                        2. Pertimbangkan kemudahan penggunaan
                        3. Cek kompatibilitas dengan perangkat lain
                        4. Konsultasi dengan tim medis
                        5. Evaluasi fitur keamanan data
                        
                        Teknologi dapat menjadi alat yang powerful dalam manajemen diabetes, namun tetap memerlukan edukasi dan konsistensi dari pengguna.
                    """.trimIndent(),
                    author = "Dr. Emily Zhang",
                    publishDate = "2024-02-05",
                    category = "Teknologi",
                    readTimeMinutes = 9,
                    source = "Digital Health Technology"
                )
            )
            articleDao.insertArticles(articles)
        }
    }
}