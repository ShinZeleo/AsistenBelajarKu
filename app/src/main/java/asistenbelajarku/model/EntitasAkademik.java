package asistenbelajarku.model;

import java.time.LocalDate;

public abstract class EntitasAkademik {
    protected String namaDeskriptif;
    protected LocalDate tanggalDibuat;

    public EntitasAkademik(String namaDeskriptif) {
        // TODO (Akram): Inisialisasi atribut namaDeskriptif dengan nilai dari parameter.
        // TODO (Akram): Inisialisasi atribut tanggalDibuat dengan tanggal saat ini (LocalDate.now()).
    }

    public String getNamaDeskriptif() {
        // TODO (Akram): Kembalikan nilai atribut namaDeskriptif.
        return null; // Placeholder
    }

    public void setNamaDeskriptif(String namaDeskriptif) {
        // TODO (Akram): Atur nilai atribut namaDeskriptif dengan nilai dari parameter.
        //               Pertimbangkan validasi dasar jika diperlukan (misalnya, tidak boleh null atau kosong).
    }

    public LocalDate getTanggalDibuat() {
        // TODO (Akram): Kembalikan nilai atribut tanggalDibuat.
        return null; // Placeholder
    }

    // Metode abstrak yang WAJIB di-override oleh kelas turunan
    public abstract String getJenisEntitas();
    // TODO (Akram): Jelaskan tujuan metode abstrak ini.
    // Penjelasan: Metode ini akan diimplementasikan oleh setiap kelas turunan (Tugas, JadwalSesi)
    //           untuk mengembalikan String yang mengidentifikasi jenis entitas tersebut (misalnya, "Tugas", "Jadwal Sesi").
    //           Ini berguna untuk polimorfisme atau logika yang bergantung pada tipe.

    public abstract String getRingkasanTampilan();
    // TODO (Akram): Jelaskan tujuan metode abstrak ini.
    // Penjelasan: Metode ini akan diimplementasikan oleh setiap kelas turunan
    //           untuk menghasilkan representasi String singkat dari objek, yang cocok untuk ditampilkan di UI
    //           seperti ListView atau TableView.
}