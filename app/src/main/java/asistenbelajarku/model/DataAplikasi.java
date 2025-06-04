package asistenbelajarku.model;

import java.util.List;
import java.util.ArrayList; // Import ditambahkan

public class DataAplikasi {
    private List<Tugas> daftarTugas;
    private List<JadwalSesi> daftarSesi;
    private List<MataPelajaran> daftarMataPelajaran;

    public DataAplikasi() {
        // TODO (Ryan/Akram): Inisialisasi semua list sebagai ArrayList kosong.
        // Penjelasan: Konstruktor ini penting untuk deserialisasi (misalnya dari JSON)
        //           atau untuk membuat instance DataAplikasi baru yang siap diisi.
    }

    public DataAplikasi(List<Tugas> daftarTugas, List<JadwalSesi> daftarSesi, List<MataPelajaran> daftarMataPelajaran) {
        // TODO (Ryan/Akram): Inisialisasi semua atribut list dengan nilai dari parameter.
    }

    // TODO (Ryan/Akram): Implementasikan semua getter dan setter untuk setiap list.
    public List<Tugas> getDaftarTugas() { return null; /* Placeholder */ }
    public void setDaftarTugas(List<Tugas> daftarTugas) { /* TODO */ }
    public List<JadwalSesi> getDaftarSesi() { return null; /* Placeholder */ }
    public void setDaftarSesi(List<JadwalSesi> daftarSesi) { /* TODO */ }
    public List<MataPelajaran> getDaftarMataPelajaran() { return null; /* Placeholder */ }
    public void setDaftarMataPelajaran(List<MataPelajaran> daftarMataPelajaran) { /* TODO */ }
}