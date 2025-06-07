package asistenbelajarku.service;

import asistenbelajarku.model.DataAplikasi;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PenyimpananService implements iPenyimpananService {

    private static final String NAMA_FILE_DATA = "asisten_belajar_data.json";
    private final ObjectMapper objectMapper;

    public PenyimpananService() {
        // TODO (Ryan): Implementasikan logika untuk menyimpan objek DataAplikasi ke file (misal NAMA_FILE_DATA). --> Komentar ini sepertinya salah tempat, ini adalah konstruktor
        // Penjelasan: Gunakan library JSON (seperti Jackson) untuk mengubah objek DataAplikasi menjadi string JSON,
        //           lalu tulis string tersebut ke file. Tangani potensi IOException dengan try-catch
        //           atau try-with-resources. Berikan pesan ke konsol jika berhasil atau gagal.

        // Penjelasan TODO di konstruktor:
        // Inisialisasi komponen yang dibutuhkan untuk penyimpanan.
        // Menggunakan library Jackson untuk JSON, inisialisasi ObjectMapper di sini.
        // Mengkonfigurasi ObjectMapper untuk menangani tipe data Java Time (LocalDate, LocalTime).
        // jika library tersebut membutuhkannya (misalnya dengan JavaTimeModule).
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println("PenyimpananService diinisialisasi.");
    }

    @Override
    public void simpanSemuaData(DataAplikasi dataAplikasi) {
         // TODO (Ryan): Inisialisasi komponen yang dibutuhkan untuk penyimpanan. --> Komentar ini seharusnya untuk konstruktor

        // Penjelasan TODO untuk simpanSemuaData:
        // Implementasi logika untuk menyimpan objek DataAplikasi ke file.
        // Menggunakan library JSON (Jackson) untuk mengubah objek DataAplikasi menjadi string JSON,
        // lalu menulis string tersebut ke file. Menangani potensi IOException dengan try-with-resources.
        // atau try-with-resources. Berikan pesan ke konsol jika berhasil atau gagal.
        if (dataAplikasi == null) {
            System.err.println("Gagal menyimpan data: Objek DataAplikasi adalah null.");
            return;
        }
        try (FileWriter writer = new FileWriter(NAMA_FILE_DATA)) {
            objectMapper.writeValue(writer, dataAplikasi);
            System.out.println("Data berhasil disimpan ke " + NAMA_FILE_DATA);
        } catch (IOException e) {
            System.err.println("Gagal menyimpan data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public DataAplikasi muatSemuaData() {
        // TODO (Ryan): Implementasikan logika untuk memuat objek DataAplikasi dari file.
        // Implementasi logika untuk memuat objek DataAplikasi dari file.
        // Membaca konten file JSON, lalu menggunakan library JSON untuk mengubah string JSON
        // menjadi objek DataAplikasi. Jika file tidak ada atau terjadi error saat membaca/parsing,
        // mengembalikan objek DataAplikasi baru yang kosong untuk menghindari NullPointerException.
        File file = new File(NAMA_FILE_DATA);
        if (!file.exists() || file.length() == 0) {
            System.out.println("File '" + NAMA_FILE_DATA + "' tidak ditemukan atau kosong. Mengembalikan DataAplikasi baru (kosong).");
            return new DataAplikasi(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        }
        try (FileReader reader = new FileReader(file)) {
            // Pastikan list di dalam objek DataAplikasi tidak null setelah deserialisasi
            // Ini penting jika file JSON mungkin tidak memiliki semua field list,
            // atau jika konstruktor default DataAplikasi tidak menginisialisasi list
            // (Meskipun di DataAplikasi.java Anda, konstruktor default sudah menginisialisasi)
            DataAplikasi data = objectMapper.readValue(reader, DataAplikasi.class);
            System.out.println("Data berhasil dimuat dari " + NAMA_FILE_DATA);
            if (data.getDaftarTugas() == null) {
                data.setDaftarTugas(new ArrayList<>());
            }
            if (data.getDaftarSesi() == null) {
                data.setDaftarSesi(new ArrayList<>());
            }
            if (data.getDaftarMataPelajaran() == null) {
                data.setDaftarMataPelajaran(new ArrayList<>());
            }
            return data;
        } catch (IOException e) {
            System.err.println("Gagal memuat data dari file '" + NAMA_FILE_DATA + "': " + e.getMessage());
            e.printStackTrace();
            return new DataAplikasi(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        }
    }
}