package asistenbelajarku.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore; 

public class Tugas extends EntitasAkademik {
    private String detailDeskripsiTugas;
    private LocalDate tanggalTenggat;
    private boolean selesai;
    private MataPelajaran mataPelajaranTerkait; // Opsional

    public Tugas() {
        super();
    }

    public Tugas(String namaDeskriptif, String detailDeskripsiTugas, LocalDate tanggalTenggat, MataPelajaran mataPelajaranTerkait) {
        super(namaDeskriptif);
        this.detailDeskripsiTugas = detailDeskripsiTugas;
        this.tanggalTenggat = tanggalTenggat;
        this.mataPelajaranTerkait = mataPelajaranTerkait;
        this.selesai = false;
    }

    public String getDetailDeskripsiTugas() {
        return this.detailDeskripsiTugas;
    }

    public void setDetailDeskripsiTugas(String detailDeskripsiTugas) {
        this.detailDeskripsiTugas = detailDeskripsiTugas;
    }

    public LocalDate getTanggalTenggat() {
        return this.tanggalTenggat;
    }

    public void setTanggalTenggat(LocalDate tanggalTenggat) {
        this.tanggalTenggat = tanggalTenggat;
    }

    public boolean isSelesai() {
        return this.selesai;
    }

    public void setSelesai(boolean selesai) {
        this.selesai = selesai;
    }

    public MataPelajaran getMataPelajaranTerkait() {
        return this.mataPelajaranTerkait;
    }

    public void setMataPelajaranTerkait(MataPelajaran mataPelajaranTerkait) {
        this.mataPelajaranTerkait = mataPelajaranTerkait;
    }

    @Override
    public String getJenisEntitas() {
        return "Tugas";
    }

    @JsonIgnore
    @Override
    public String getRingkasanTampilan() {
        String status = this.selesai ? "Selesai" : "Belum Selesai";
        return this.getNamaDeskriptif() + " - Tenggat: " + this.tanggalTenggat + " (" + status + ")";
    }
}