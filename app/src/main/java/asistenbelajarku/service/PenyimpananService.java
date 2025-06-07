package asistenbelajarku.service;

import asistenbelajarku.model.DataAplikasi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature; // Untuk pretty print JSON (opsional)
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.FileReader; // Lebih baik untuk membaca karakter
import java.io.FileWriter; // Lebih baik untuk menulis karakter
import java.io.IOException;
import java.util.ArrayList; // Untuk membuat instance default DataAplikasi

public class PenyimpananService implements iPenyimpananService { // Pastikan nama interface iPenyimpananService konsisten

    private static final String NAMA_FILE_DATA = "asisten_belajar_data.json";
    private final ObjectMapper objectMapper; // Sudah final, bagus!

    public PenyimpananService() {
        // TODO (Ryan): Implementasikan logika untuk menyimpan objek DataAplikasi ke file (misal NAMA_FILE_DATA). --> Komentar ini sepertinya salah tempat, ini adalah konstruktor
        // Penjelasan: Gunakan library JSON (seperti Jackson) untuk mengubah objek DataAplikasi menjadi string JSON,
        //           lalu tulis string tersebut ke file. Tangani potensi IOException dengan try-catch
        //           atau try-with-resources. Berikan pesan ke konsol jika berhasil atau gagal.

        // Penjelasan TODO di konstruktor:
        // Inisialisasi komponen yang dibutuhkan untuk penyimpanan.
        // Jika menggunakan library seperti Jackson untuk JSON, inisialisasi ObjectMapper di sini.
        // Pastikan mengkonfigurasi ObjectMapper untuk menangani tipe data Java Time (LocalDate, LocalTime)
        // jika library tersebut membutuhkannya (misalnya dengan JavaTimeModule).
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Membuat file JSON lebih mudah dibaca (opsional)
        System.out.println("PenyimpananService diinisialisasi."); // Pesan konfirmasi inisialisasi
    }

    @Override
    public void simpanSemuaData(DataAplikasi dataAplikasi) {
        // TODO (Ryan): Inisialisasi komponen yang dibutuhkan untuk penyimpanan. --> Komentar ini seharusnya untuk konstruktor

        // Penjelasan TODO untuk simpanSemuaData:
        // Implementasikan logika untuk menyimpan objek DataAplikasi ke file (misal NAMA_FILE_DATA).
        // Gunakan library JSON (seperti Jackson) untuk mengubah objek DataAplikasi menjadi string JSON,
        // lalu tulis string tersebut ke file. Tangani potensi IOException dengan try-catch
        // atau try-with-resources. Berikan pesan ke konsol jika berhasil atau gagal.
        if (dataAplikasi == null) {
            System.err.println("Gagal menyimpan data: Objek DataAplikasi adalah null.");
            return;
        }
        try (FileWriter writer = new FileWriter(NAMA_FILE_DATA)) { // Menggunakan FileWriter untuk teks
            objectMapper.writeValue(writer, dataAplikasi);
            System.out.println("Data berhasil disimpan ke " + NAMA_FILE_DATA);
        } catch (IOException e) {
            System.err.println("Gagal menyimpan data: " + e.getMessage());
            e.printStackTrace(); // Untuk debugging, pertimbangkan logging yang lebih baik di aplikasi nyata
        }
    }

    @Override
    public DataAplikasi muatSemuaData() {
        // TODO (Ryan): Implementasikan logika untuk memuat objek DataAplikasi dari file.
        // Penjelasan: Baca konten file JSON, lalu gunakan library JSON untuk mengubah string JSON
        //           menjadi objek DataAplikasi. Jika file tidak ada atau terjadi error saat membaca/parsing,
        //           kembalikan objek DataAplikasi baru yang kosong (bukan null) untuk menghindari NullPointerException
        //           di bagian lain aplikasi. Tangani IOException. Berikan pesan ke konsol.
        File file = new File(NAMA_FILE_DATA);
        if (!file.exists() || file.length() == 0) { // Tambahkan pengecekan file.length() == 0
            System.out.println("File '" + NAMA_FILE_DATA + "' tidak ditemukan atau kosong. Mengembalikan DataAplikasi baru (kosong).");
            // Kembalikan objek DataAplikasi baru dengan list yang sudah diinisialisasi
            return new DataAplikasi(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        }
        try (FileReader reader = new FileReader(file)) { // Menggunakan FileReader untuk teks
            DataAplikasi data = objectMapper.readValue(reader, DataAplikasi.class);
            System.out.println("Data berhasil dimuat dari " + NAMA_FILE_DATA);

            // Pastikan list di dalam objek DataAplikasi tidak null setelah deserialisasi
            // Ini penting jika file JSON mungkin tidak memiliki semua field list,
            // atau jika konstruktor default DataAplikasi tidak menginisialisasi list
            // (Meskipun di DataAplikasi.java Anda, konstruktor default sudah menginisialisasi)
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
            e.printStackTrace(); // Untuk debugging
            // Jika terjadi error saat memuat, tetap kembalikan data kosong agar aplikasi tidak crash
            return new DataAplikasi(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        }
    }
}