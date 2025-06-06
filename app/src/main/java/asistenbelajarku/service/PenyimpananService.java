package asistenbelajarku.service;

import asistenbelajarku.model.DataAplikasi;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;

public class PenyimpananService implements iPenyimpananService {

    private static final String NAMA_FILE_DATA = "asisten_belajar_data.json";
    private final ObjectMapper objectMapper;

    public PenyimpananService() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void simpanSemuaData(DataAplikasi dataAplikasi) {
        try {
            objectMapper.writeValue(new File(NAMA_FILE_DATA), dataAplikasi);
            System.out.println("Data berhasil disimpan ke " + NAMA_FILE_DATA);
        } catch (IOException e) {
            System.err.println("Gagal menyimpan data: " + e.getMessage());
        }
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