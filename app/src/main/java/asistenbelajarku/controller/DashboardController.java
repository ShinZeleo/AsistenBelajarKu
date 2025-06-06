package asistenbelajarku.controller;

import asistenbelajarku.model.DataAplikasi;
import asistenbelajarku.model.JadwalSesi;
import asistenbelajarku.model.Tugas;
import asistenbelajarku.service.iPenyimpananService; // Pastikan nama interface ini benar
import asistenbelajarku.service.PenyimpananService; // Implementasi konkret

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator; // Untuk sorting
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class DashboardController implements Initializable {

    @FXML private ListView<String> jadwalHariIniListView;
    @FXML private ListView<String> tugasMendatangListView;
    @FXML private Button kelolaJadwalButtonHeader; // Pastikan fx:id ini ada di FXML Anda
    @FXML private Button kelolaTugasButtonHeader;  // Pastikan fx:id ini ada di FXML Anda
    // TODO (Imam): Deklarasikan @FXML untuk tombol lain di sidebar jika mereka punya aksi dari sini
    // @FXML private Button mimeSugurButton;
    // @FXML private Button mangTratomgButton;
    // @FXML private Button danaNiariButton;
    // @FXML private Button kelolaTugasButtonSidebar;


    private iPenyimpananService penyimpananService;
    private ObservableList<String> jadwalDisplayData;
    private ObservableList<String> tugasDisplayData;
    private DataAplikasi dataAplikasiSaatIni;

    public DashboardController() {
        // Inisialisasi penyimpananService
        this.penyimpananService = new PenyimpananService(); // Instansiasi implementasi konkret
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inisialisasi ObservableList
        this.jadwalDisplayData = FXCollections.observableArrayList();
        this.tugasDisplayData = FXCollections.observableArrayList();

        // Set ObservableList tersebut sebagai items untuk ListView
        jadwalHariIniListView.setItems(jadwalDisplayData);
        tugasMendatangListView.setItems(tugasDisplayData);

        // Panggil metode untuk memuat data awal dari penyimpananService dan menampilkannya
        muatDanTampilkanDataDashboard();
        System.out.println("DashboardController terinisialisasi.");
    }

// di DashboardController.java

    private void muatDanTampilkanDataDashboard() {
        // Panggil penyimpananService.muatSemuaData() untuk mendapatkan dataAplikasiSaatIni
        dataAplikasiSaatIni = penyimpananService.muatSemuaData();

        // Bersihkan jadwalDisplayData dan tugasDisplayData
        jadwalDisplayData.clear();
        tugasDisplayData.clear();

        // Dari dataAplikasiSaatIni.getDaftarSesi(), tampilkan SEMUA jadwal
        if (dataAplikasiSaatIni != null && dataAplikasiSaatIni.getDaftarSesi() != null) {
            jadwalDisplayData.addAll(
                dataAplikasiSaatIni.getDaftarSesi().stream()
                    .map(JadwalSesi::getRingkasanTampilan) // Hanya format dan tampilkan
                    .collect(Collectors.toList())
            );
        }

        // Dari dataAplikasiSaatIni.getDaftarTugas(), filter hanya yang belum selesai
        if (dataAplikasiSaatIni != null && dataAplikasiSaatIni.getDaftarTugas() != null) {
            tugasDisplayData.addAll(
                dataAplikasiSaatIni.getDaftarTugas().stream()
                    // .filter(tugas -> !tugas.isSelesai())
                    .sorted(Comparator.comparing(Tugas::getTanggalTenggat))
                    .map(Tugas::getRingkasanTampilan)
                    .collect(Collectors.toList())
            );
        }

        // Jika tidak ada jadwal/tugas yang tersimpan, tampilkan pesan yang sesuai di ListView.
        if (jadwalDisplayData.isEmpty()) {
            jadwalDisplayData.add("Belum ada jadwal yang tersimpan.");
        }
        if (tugasDisplayData.isEmpty()) {
            tugasDisplayData.add("Tidak ada tugas yang perlu dikerjakan.");
        }
    }

    // Metode helper untuk navigasi
    private void gantiScene(ActionEvent event, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/" + fxmlFile));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            // Jika ingin ukuran scene baru sama dengan yang lama (atau preferensi FXML)
            // Anda bisa juga set ukuran spesifik: new Scene(root, 900, 600);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Gagal memuat scene: " + fxmlFile);
            e.printStackTrace();
            // TODO (Imam): Tampilkan alert ke pengguna jika diperlukan
        }
    }

    @FXML
    private void handleKelolaJadwalButtonAction(ActionEvent event) {
        System.out.println("Navigasi ke Manajemen Jadwal...");
        gantiScene(event, "ManajemenJadwalScene.fxml");
    }

    @FXML
    private void handleKelolaTugasButtonAction(ActionEvent event) {
        // TODO (Imam): Jika ada dua tombol "Kelola Tugas", pastikan aksinya sesuai atau bedakan method handler-nya.
        //               Untuk saat ini, keduanya akan ke scene yang sama.
        System.out.println("Navigasi ke Manajemen Tugas...");
        gantiScene(event, "ManajemenTugasScene.fxml");
    }

    // TODO (Imam): Tambahkan @FXML metode handler untuk tombol-tombol lain di Dashboard jika ada.
    // Contoh:
    // @FXML
    // private void handleMimeSugurButtonAction(ActionEvent event) {
    //     System.out.println("Tombol Mime Sugur (Nav/Filter 1) diklik");
    //     // Implementasikan logika filter atau aksi lainnya
    // }
}