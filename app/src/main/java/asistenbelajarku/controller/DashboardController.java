package asistenbelajarku.controller;

import asistenbelajarku.model.DataAplikasi;
import asistenbelajarku.model.JadwalSesi; // Perlu di-import jika menggunakan tipe spesifik
import asistenbelajarku.model.Tugas;    // Perlu di-import jika menggunakan tipe spesifik
import asistenbelajarku.service.iPenyimpananService;
// import asistenbelajarku.service.PenyimpananService; // Implementasi konkret
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
// import komponen UI lain dari FXML

import java.net.URL;
import java.util.ResourceBundle;
// import java.util.stream.Collectors; // Untuk filtering jika diperlukan

public class DashboardController implements Initializable {

    // TODO (Imam): Deklarasikan semua elemen @FXML dari DashboardScene.fxml (ListView, Button, Label, dll.)
    @FXML private ListView<String> jadwalHariIniListView; // Pertimbangkan ListView<JadwalSesi> dengan CellFactory kustom
    @FXML private ListView<String> tugasMendatangListView;  // Pertimbangkan ListView<Tugas> dengan CellFactory kustom

    private iPenyimpananService penyimpananService;
    private ObservableList<String> jadwalDisplayData; // Atau ObservableList<JadwalSesi>
    private ObservableList<String> tugasDisplayData;  // Atau ObservableList<Tugas>
    private DataAplikasi dataAplikasiSaatIni;

    public DashboardController() {
        // TODO (Imam): Inisialisasi penyimpananService (misalnya, new PenyimpananService()).
        // Penjelasan: Sebaiknya menggunakan Dependency Injection di masa depan, tapi untuk awal bisa instansiasi langsung.
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO (Imam): Inisialisasi ObservableList (jadwalDisplayData, tugasDisplayData).
        // TODO (Imam): Set ObservableList tersebut sebagai items untuk jadwalHariIniListView dan tugasMendatangListView.
        // TODO (Imam): Panggil metode untuk memuat data awal dari penyimpananService dan menampilkannya di ListView.
    }

    private void muatDanTampilkanDataDashboard() {
        // TODO (Imam): Panggil penyimpananService.muatSemuaData() untuk mendapatkan dataAplikasiSaatIni.
        // TODO (Imam): Bersihkan jadwalDisplayData dan tugasDisplayData.
        // TODO (Imam): Dari dataAplikasiSaatIni.getDaftarSesi(), filter jadwal untuk hari ini,
        //           lalu format setiap JadwalSesi (menggunakan getRingkasanTampilan()) dan tambahkan ke jadwalDisplayData.
        // TODO (Imam): Dari dataAplikasiSaatIni.getDaftarTugas(), filter tugas yang belum selesai,
        //           urutkan berdasarkan tenggat waktu, format setiap Tugas (menggunakan getRingkasanTampilan()),
        //           dan tambahkan ke tugasDisplayData.
        // TODO (Imam): Jika tidak ada jadwal/tugas, tampilkan pesan yang sesuai di ListView.
    }

    @FXML
    private void handleKelolaJadwalButtonAction(ActionEvent event) {
        // TODO (Imam): Implementasikan logika untuk navigasi ke scene Manajemen Jadwal.
        // Penjelasan: Ini mungkin melibatkan pemanggilan metode statis di kelas App untuk mengganti scene,
        //           atau menggunakan mekanisme navigasi lain yang kalian rancang.
    }

    @FXML
    private void handleKelolaTugasButtonAction(ActionEvent event) {
        // TODO (Imam): Implementasikan logika untuk navigasi ke scene Manajemen Tugas.
    }

    // TODO (Imam): Tambahkan @FXML metode handler untuk tombol-tombol lain di Dashboard jika ada.
}