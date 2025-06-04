package asistenbelajarku.model;

public class MataPelajaran {
    private String namaMapel;
    private String namaGuru; // Opsional

    public MataPelajaran(String namaMapel, String namaGuru) {
        // TODO (Akram): Inisialisasi atribut namaMapel dan namaGuru.
    }

    public MataPelajaran(String namaMapel) {
        // TODO (Akram): Inisialisasi atribut namaMapel, dan set namaGuru ke null atau string kosong.
        //               Ini adalah konstruktor alternatif jika namaGuru tidak wajib.
    }

    public String getNamaMapel() {
        // TODO (Akram): Kembalikan nilai atribut namaMapel.
        return null; // Placeholder
    }

    public void setNamaMapel(String namaMapel) {
        // TODO (Akram): Atur nilai atribut namaMapel. Pertimbangkan validasi (tidak null/kosong).
    }

    public String getNamaGuru() {
        // TODO (Akram): Kembalikan nilai atribut namaGuru.
        return null; // Placeholder
    }

    public void setNamaGuru(String namaGuru) {
        // TODO (Akram): Atur nilai atribut namaGuru.
    }

    @Override
    public String toString() {
        // TODO (Akram): Implementasikan representasi String dari objek MataPelajaran.
        // Penjelasan: Berguna untuk debugging dan mungkin untuk menampilkan objek ini di ComboBox
        //           atau komponen UI lain secara default. Contoh: "Matematika (Bu Retno)" atau "Fisika".
        return ""; // Placeholder
    }
}