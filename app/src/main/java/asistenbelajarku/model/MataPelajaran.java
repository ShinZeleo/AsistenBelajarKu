package asistenbelajarku.model;

public class MataPelajaran {
    private String namaMapel;
    private String namaGuru; // Opsional

    public MataPelajaran(String namaMapel, String namaGuru) {
        this.namaMapel = namaMapel;
        this.namaGuru = namaGuru;
    }

    public MataPelajaran(String namaMapel) {
        this.namaMapel = namaMapel;
        this.namaGuru = null;
    }

    public String getNamaMapel() {
        return this.namaMapel;
    }

    public void setNamaMapel(String namaMapel) {
        if (namaMapel != null && !namaMapel.isEmpty()) {
            this.namaMapel = namaMapel;
        } else {
            throw new IllegalArgumentException("Nama mata pelajaran tidak boleh null atau kosong.");
        }
    }

    public String getNamaGuru() {
        return this.namaGuru;
    }

    public void setNamaGuru(String namaGuru) {
        this.namaGuru = namaGuru;
    }

    @Override
    public String toString() {
        if (this.namaGuru != null) {
            return this.namaMapel + " (" + this.namaGuru + ")";
        } else {
            return this.namaMapel;
        }
    }
}