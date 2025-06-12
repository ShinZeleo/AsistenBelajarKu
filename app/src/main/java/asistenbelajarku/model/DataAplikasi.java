package asistenbelajarku.model;

import java.util.ArrayList;
import java.util.List;

public class DataAplikasi {
    private List<Tugas> daftarTugas;
    private List<JadwalSesi> daftarSesi;
    private List<MataPelajaran> daftarMataPelajaran;

    public DataAplikasi() {
        this.daftarTugas = new ArrayList<>();
        this.daftarSesi = new ArrayList<>();
        this.daftarMataPelajaran = new ArrayList<>();
    }

    public DataAplikasi(List<Tugas> daftarTugas, List<JadwalSesi> daftarSesi, List<MataPelajaran> daftarMataPelajaran) {
        this.daftarTugas = daftarTugas != null ? new ArrayList<>(daftarTugas) : new ArrayList<>();
        this.daftarSesi = daftarSesi != null ? new ArrayList<>(daftarSesi) : new ArrayList<>();
        this.daftarMataPelajaran = daftarMataPelajaran != null ? new ArrayList<>(daftarMataPelajaran) : new ArrayList<>();
    }

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