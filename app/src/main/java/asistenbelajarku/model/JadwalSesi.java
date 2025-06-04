package asistenbelajarku.model;

import java.time.LocalTime;

public class JadwalSesi extends EntitasAkademik {
    private String hari;
    private LocalTime waktuMulai;
    private LocalTime waktuSelesai;
    private MataPelajaran mataPelajaran;
    private String ruangan; // Opsional

    public JadwalSesi(String namaDeskriptifSesi, String hari, LocalTime waktuMulai, LocalTime waktuSelesai, MataPelajaran mataPelajaran, String ruangan) {
        // TODO (Akram): Panggil konstruktor superclass (EntitasAkademik) dengan namaDeskriptifSesi.
        // TODO (Akram): Inisialisasi semua atribut spesifik JadwalSesi.
        super(namaDeskriptifSesi); // Placeholder
    }

    // TODO (Akram): Implementasikan semua getter dan setter untuk atribut: hari, waktuMulai, waktuSelesai, mataPelajaran, ruangan.
    //               Pastikan enkapsulasi terjaga.
    public String getHari() { return null; /* Placeholder */ }
    public void setHari(String hari) { /* TODO */ }
    public LocalTime getWaktuMulai() { return null; /* Placeholder */ }
    public void setWaktuMulai(LocalTime waktuMulai) { /* TODO */ }
    public LocalTime getWaktuSelesai() { return null; /* Placeholder */ }
    public void setWaktuSelesai(LocalTime waktuSelesai) { /* TODO */ }
    public MataPelajaran getMataPelajaran() { return null; /* Placeholder */ }
    public void setMataPelajaran(MataPelajaran mataPelajaran) { /* TODO */ }
    public String getRuangan() { return null; /* Placeholder */ }
    public void setRuangan(String ruangan) { /* TODO */ }

    @Override
    public String getJenisEntitas() {
        // TODO (Akram): Kembalikan String "Jadwal Sesi".
        return ""; // Placeholder
    }

    @Override
    public String getRingkasanTampilan() {
        // TODO (Akram): Implementasikan representasi String singkat untuk JadwalSesi.
        // Penjelasan: Akan digunakan untuk ditampilkan di ListView Dashboard atau TableView.
        //           Contoh: "PBO | Senin | 08:00 - 10:00 (Lab Komputer)".
        return ""; // Placeholder
    }
}