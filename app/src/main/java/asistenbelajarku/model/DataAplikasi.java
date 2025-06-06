package asistenbelajarku.model;

import java.util.ArrayList;
import java.util.List;

public class DataAplikasi {
    private List<Tugas> daftarTugas;
    private List<JadwalSesi> daftarSesi;
    private List<MataPelajaran> daftarMataPelajaran;

    public DataAplikasi() {
        // TODO (Ryan): Inisialisasi semua list sebagai ArrayList kosong.
        // Penjelasan: Konstruktor ini penting untuk deserialisasi (misalnya dari JSON)
        //           atau untuk membuat instance DataAplikasi baru yang siap diisi.
        this.daftarTugas = new ArrayList<>();
        this.daftarSesi = new ArrayList<>();
        this.daftarMataPelajaran = new ArrayList<>();
    }

    public DataAplikasi(List<Tugas> daftarTugas, List<JadwalSesi> daftarSesi, List<MataPelajaran> daftarMataPelajaran) {
        // TODO (Ryan): Inisialisasi semua atribut list dengan nilai dari parameter.
        this.daftarTugas = daftarTugas != null ? new ArrayList<>(daftarTugas) : new ArrayList<>();
        this.daftarSesi = daftarSesi != null ? new ArrayList<>(daftarSesi) : new ArrayList<>();
        this.daftarMataPelajaran = daftarMataPelajaran != null ? new ArrayList<>(daftarMataPelajaran) : new ArrayList<>();
    }
        // TODO (Ryan): Implementasikan semua getter dan setter untuk setiap list.
    public List<Tugas> getDaftarTugas() {
        return daftarTugas != null ? daftarTugas : new ArrayList<>();
    }

    public void setDaftarTugas(List<Tugas> daftarTugas) {
        this.daftarTugas = daftarTugas != null ? new ArrayList<>(daftarTugas) : new ArrayList<>();
    }

    public List<JadwalSesi> getDaftarSesi() {
        return daftarSesi != null ? daftarSesi : new ArrayList<>();
    }

    public void setDaftarSesi(List<JadwalSesi> daftarSesi) {
        this.daftarSesi = daftarSesi != null ? new ArrayList<>(daftarSesi) : new ArrayList<>();
    }

    public List<MataPelajaran> getDaftarMataPelajaran() {
        return daftarMataPelajaran != null ? daftarMataPelajaran : new ArrayList<>();
    }

    public void setDaftarMataPelajaran(List<MataPelajaran> daftarMataPelajaran) {
        this.daftarMataPelajaran = daftarMataPelajaran != null ? new ArrayList<>(daftarMataPelajaran) : new ArrayList<>();
    }
}

class JadwalSesi {
    private String hari;
    private String waktuMulai;
    private String waktuSelesai;
    private String mataPelajaran;
    private String ruangan;

    public JadwalSesi(String hari, String waktuMulai, String waktuSelesai, String mataPelajaran, String ruangan) {
        this.hari = hari;
        this.waktuMulai = waktuMulai;
        this.waktuSelesai = waktuSelesai;
        this.mataPelajaran = mataPelajaran;
        this.ruangan = ruangan;
    }

    public String getHari() { return hari; }
    public String getWaktuMulai() { return waktuMulai; }
    public String getWaktuSelesai() { return waktuSelesai; }
    public String getMataPelajaran() { return mataPelajaran; }
    public String getRuangan() { return ruangan; }
}

class Tugas {
    private String deskripsi;
    private String mataPelajaran;
    private String tanggalTenggat;
    private String status;

    public Tugas(String deskripsi, String mataPelajaran, String tanggalTenggat, String status) {
        this.deskripsi = deskripsi;
        this.mataPelajaran = mataPelajaran;
        this.tanggalTenggat = tanggalTenggat;
        this.status = status;
    }

    public String getDeskripsi() { return deskripsi; }
    public String getMataPelajaran() { return mataPelajaran; }
    public String getTanggalTenggat() { return tanggalTenggat; }
    public String getStatus() { return status; }
}

class MataPelajaran {
    private String nama;

    public MataPelajaran(String nama) {
        this.nama = nama;
    }

    public String getNama() { return nama; }
}