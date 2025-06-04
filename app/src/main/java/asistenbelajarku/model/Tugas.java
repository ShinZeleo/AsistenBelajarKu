package asistenbelajarku.model;

import java.time.LocalDate;

public class Tugas extends EntitasAkademik {
    private String detailDeskripsiTugas;
    private LocalDate tanggalTenggat;
    private boolean selesai;
    private MataPelajaran mataPelajaranTerkait; // Opsional

    public Tugas(String namaDeskriptif, String detailDeskripsiTugas, LocalDate tanggalTenggat, MataPelajaran mataPelajaranTerkait) {
        // TODO (Akram): Panggil konstruktor superclass (EntitasAkademik) dengan namaDeskriptif.
        // TODO (Akram): Inisialisasi semua atribut spesifik Tugas. Set 'selesai' ke false secara default.
        super(namaDeskriptif); // Placeholder
    }

    // TODO (Akram): Implementasikan semua getter dan setter untuk atribut: detailDeskripsiTugas, tanggalTenggat, selesai, mataPelajaranTerkait.
    public String getDetailDeskripsiTugas() { return null; /* Placeholder */ }
    public void setDetailDeskripsiTugas(String detailDeskripsiTugas) { /* TODO */ }
    public LocalDate getTanggalTenggat() { return null; /* Placeholder */ }
    public void setTanggalTenggat(LocalDate tanggalTenggat) { /* TODO */ }
    public boolean isSelesai() { return false; /* Placeholder */ }
    public void setSelesai(boolean selesai) { /* TODO */ }
    public MataPelajaran getMataPelajaranTerkait() { return null; /* Placeholder */ }
    public void setMataPelajaranTerkait(MataPelajaran mataPelajaranTerkait) { /* TODO */ }

    @Override
    public String getJenisEntitas() {
        // TODO (Akram): Kembalikan String "Tugas".
        return ""; // Placeholder
    }

    @Override
    public String getRingkasanTampilan() {
        // TODO (Akram): Implementasikan representasi String singkat untuk Tugas.
        // Penjelasan: Akan digunakan untuk ditampilkan di ListView Dashboard atau TableView.
        //           Contoh: "Proyek Akhir PBO - Tenggat: 2025-06-10 (Belum Selesai)".
        return ""; // Placeholder
    }
}