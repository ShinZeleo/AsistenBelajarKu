package asistenbelajarku.model;

import java.time.LocalTime;

public class JadwalSesi extends EntitasAkademik {
    private String hari;
    private LocalTime waktuMulai;
    private LocalTime waktuSelesai;
    private MataPelajaran mataPelajaran;
    private String ruangan; // Opsional

    public JadwalSesi() {
        super();
    }

    public JadwalSesi(String namaDeskriptifSesi, String hari, LocalTime waktuMulai, LocalTime waktuSelesai, MataPelajaran mataPelajaran, String ruangan) {
        super(namaDeskriptifSesi);
        this.hari = hari;
        this.waktuMulai = waktuMulai;
        this.waktuSelesai = waktuSelesai;
        this.mataPelajaran = mataPelajaran;
        this.ruangan = ruangan;
    }

    public String getHari() {
        return this.hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public LocalTime getWaktuMulai() {
        return this.waktuMulai;
    }

    public void setWaktuMulai(LocalTime waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    public LocalTime getWaktuSelesai() {
        return this.waktuSelesai;
    }

    public void setWaktuSelesai(LocalTime waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
    }

    public MataPelajaran getMataPelajaran() {
        return this.mataPelajaran;
    }

    public void setMataPelajaran(MataPelajaran mataPelajaran) {
        this.mataPelajaran = mataPelajaran;
    }

    public String getRuangan() {
        return this.ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }

    @Override
    public String getJenisEntitas() {
        return "Jadwal Sesi";
    }

    @Override
    public String getRingkasanTampilan() {
    String namaMapel = (this.mataPelajaran != null) ? this.mataPelajaran.getNamaMapel() : "Tanpa Mapel";
    
    String infoRuangan = (this.ruangan != null && !this.ruangan.isEmpty()) ? " (" + this.ruangan + ")" : "";

    return String.format("%s | %s | %s - %s%s", 
        namaMapel, 
        this.hari, 
        this.waktuMulai, 
        this.waktuSelesai, 
        infoRuangan
    );
}
}