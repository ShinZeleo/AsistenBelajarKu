package asistenbelajarku.service;

import asistenbelajarku.model.DataAplikasi;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class PenyimpananService implements iPenyimpananService {

    private static final String NAMA_FILE_DATA = "asisten_belajar_data.json";
    private final ObjectMapper objectMapper;

    public PenyimpananService() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
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