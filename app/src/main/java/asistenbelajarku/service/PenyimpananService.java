package asistenbelajarku.service;

import asistenbelajarku.model.DataAplikasi;
// import com.fasterxml.jackson.databind.ObjectMapper; // Contoh jika pakai Jackson JSON
// import lainnya yang dibutuhkan untuk file I/O dan JSON processing

public class PenyimpananService implements iPenyimpananService {

    private static final String NAMA_FILE_DATA = "asisten_belajar_data.json";
    // private ObjectMapper objectMapper; // Atribut jika menggunakan Jackson

    public PenyimpananService() {
        // TODO (Ryan): Inisialisasi komponen yang dibutuhkan untuk penyimpanan.
        // Penjelasan: Jika menggunakan library seperti Jackson untuk JSON, inisialisasi ObjectMapper di sini.
        //           Pastikan mengkonfigurasi ObjectMapper untuk menangani tipe data Java Time (LocalDate, LocalTime)
        //           jika library tersebut membutuhkannya (misalnya dengan JavaTimeModule).
    }

    @Override
    public void simpanSemuaData(DataAplikasi dataAplikasi) {
        // TODO (Ryan): Implementasikan logika untuk menyimpan objek DataAplikasi ke file (misal NAMA_FILE_DATA).
        // Penjelasan: Gunakan library JSON (seperti Jackson) untuk mengubah objek DataAplikasi menjadi string JSON,
        //           lalu tulis string tersebut ke file. Tangani potensi IOException dengan try-catch
        //           atau try-with-resources. Berikan pesan ke konsol jika berhasil atau gagal.
    }

    @Override
    public DataAplikasi muatSemuaData() {
        // TODO (Ryan): Implementasikan logika untuk memuat objek DataAplikasi dari file.
        // Penjelasan: Baca konten file JSON, lalu gunakan library JSON untuk mengubah string JSON
        //           menjadi objek DataAplikasi. Jika file tidak ada atau terjadi error saat membaca/parsing,
        //           kembalikan objek DataAplikasi baru yang kosong (bukan null) untuk menghindari NullPointerException
        //           di bagian lain aplikasi. Tangani IOException. Berikan pesan ke konsol.
        return null; // Placeholder
    }
}