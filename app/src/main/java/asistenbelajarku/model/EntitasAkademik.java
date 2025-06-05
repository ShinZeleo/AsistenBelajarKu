package asistenbelajarku.model;

import java.time.LocalDate;

public abstract class EntitasAkademik {
    protected String namaDeskriptif;
    protected LocalDate tanggalDibuat;

    public EntitasAkademik(String namaDeskriptif) {
        this.namaDeskriptif = namaDeskriptif;
        this.tanggalDibuat = LocalDate.now();
    }

    public String getNamaDeskriptif() {
        return this.namaDeskriptif;
    }

    public void setNamaDeskriptif(String namaDeskriptif) {
        if (namaDeskriptif != null && !namaDeskriptif.isEmpty()) {
            this.namaDeskriptif = namaDeskriptif;
        } else {
            throw new IllegalArgumentException("Nama deskriptif tidak boleh null atau kosong.");
        }
    }

    public LocalDate getTanggalDibuat() {
        return this.tanggalDibuat;
    }
    
    public abstract String getJenisEntitas();
    public abstract String getRingkasanTampilan();

}