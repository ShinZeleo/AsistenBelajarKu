package asistenbelajarku.controller;

import asistenbelajarku.model.DataAplikasi;
import asistenbelajarku.model.JadwalSesi;
import asistenbelajarku.model.Tugas;
import asistenbelajarku.service.iPenyimpananService; 
import asistenbelajarku.service.PenyimpananService; 

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
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Comparator; 
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class DashboardController implements Initializable {

    @FXML private ListView<String> jadwalHariIniListView;
    @FXML private ListView<String> tugasMendatangListView;
    @FXML private Button kelolaJadwalButtonHeader; 
    @FXML private Button kelolaTugasButtonHeader;  
    @FXML private ComboBox<String> filterHariComboBox;
    @FXML private ComboBox<String> filterTugasStatusComboBox;
    @FXML private ComboBox<String> filterTugasTenggatComboBox;

    private iPenyimpananService penyimpananService;
    private ObservableList<String> jadwalDisplayData;
    private ObservableList<String> tugasDisplayData;
    private DataAplikasi dataAplikasiSaatIni;

    @FXML private ImageView logoImageView;
    @FXML private Button minimizeButton;
    @FXML private Button maximizeButton;
    @FXML private Button closeButton;

    public DashboardController() {
        this.penyimpananService = new PenyimpananService(); 
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inisialisasi ObservableList
        this.jadwalDisplayData = FXCollections.observableArrayList();
        this.tugasDisplayData = FXCollections.observableArrayList();

        // Set ObservableList tersebut sebagai items untuk ListView
        jadwalHariIniListView.setItems(jadwalDisplayData);
        tugasMendatangListView.setItems(tugasDisplayData);

        // Setup item untuk ComboBox filter
        filterHariComboBox.setItems(FXCollections.observableArrayList("Semua Hari", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"));
        filterHariComboBox.setValue("Semua Hari");

        filterTugasStatusComboBox.setItems(FXCollections.observableArrayList("Semua Status", "Belum Selesai", "Selesai"));
        filterTugasStatusComboBox.setValue("Belum Selesai"); // Default

        filterTugasTenggatComboBox.setItems(FXCollections.observableArrayList("Semua", "Mendekati Tenggat", "Lewat Tenggat"));
        filterTugasTenggatComboBox.setValue("Semua");

        // Tambahkan listener ke setiap kontrol filter
        filterHariComboBox.setOnAction(event -> perbaruiTampilanDashboard());
        filterTugasStatusComboBox.setOnAction(event -> perbaruiTampilanDashboard());
        filterTugasTenggatComboBox.setOnAction(event -> perbaruiTampilanDashboard());

        muatDanTampilkanDataDashboard();
        System.out.println("DashboardController terinisialisasi.");
    }

// di DashboardController.java
    private void muatDanTampilkanDataDashboard() {
        dataAplikasiSaatIni = penyimpananService.muatSemuaData();
        perbaruiTampilanDashboard();
    }

    private void perbaruiTampilanDashboard() {
    jadwalDisplayData.clear();
    tugasDisplayData.clear();

    // Ambil nilai filter
    String filterHari = filterHariComboBox.getValue();
    String filterStatus = filterTugasStatusComboBox.getValue();
    String filterTenggat = filterTugasTenggatComboBox.getValue();

    // Filter dan tampilkan jadwal
    if (dataAplikasiSaatIni != null && dataAplikasiSaatIni.getDaftarSesi() != null) {
        jadwalDisplayData.addAll(
            dataAplikasiSaatIni.getDaftarSesi().stream()
                .filter(sesi -> "Semua Hari".equals(filterHari) || sesi.getHari().equalsIgnoreCase(filterHari))
                .map(JadwalSesi::getRingkasanTampilan)
                .collect(Collectors.toList())
        );
    }

    // Filter dan tampilkan tugas
    if (dataAplikasiSaatIni != null && dataAplikasiSaatIni.getDaftarTugas() != null) {
        LocalDate hariIni = LocalDate.now();

        tugasDisplayData.addAll(
            dataAplikasiSaatIni.getDaftarTugas().stream()
                .filter(tugas -> { // Filter Status
                    if ("Belum Selesai".equals(filterStatus)) return !tugas.isSelesai();
                    if ("Selesai".equals(filterStatus)) return tugas.isSelesai();
                    return true; // "Semua Status"
                })
                .filter(tugas -> { // Filter Tenggat
                    if ("Mendekati Tenggat".equals(filterTenggat)) {
                        // Hanya tampilkan yang belum selesai
                        // dan tenggatnya adalah hari ini ATAU dalam 7 hari ke depan (tidak termasuk yang sudah lewat)
                        return !tugas.isSelesai() && 
                               !tugas.getTanggalTenggat().isBefore(hariIni) && 
                               tugas.getTanggalTenggat().isBefore(hariIni.plusDays(8));
                    }
                    if ("Lewat Tenggat".equals(filterTenggat)) {
                        // HANYA periksa tanggalnya, tidak peduli statusnya
                        return tugas.getTanggalTenggat().isBefore(hariIni); // Hapus "!tugas.isSelesai() &&"
                    }
                    return true;
                })
                .sorted(Comparator.comparing(Tugas::getTanggalTenggat))
                .map(Tugas::getRingkasanTampilan)
                .collect(Collectors.toList())
        );
    }

    // Tampilkan pesan jika kosong
        if (jadwalDisplayData.isEmpty()) {
            jadwalDisplayData.add("Tidak ada jadwal yang cocok dengan filter.");
        }
        if (tugasDisplayData.isEmpty()) {
            tugasDisplayData.add("Tidak ada tugas yang cocok dengan filter.");
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
            // TODo (Imam): Tampilkan alert ke pengguna jika diperlukan
        }
    }

    @FXML
    private void handleKelolaJadwalButtonAction(ActionEvent event) {
        System.out.println("Navigasi ke Manajemen Jadwal...");
        gantiScene(event, "ManajemenJadwalScene.fxml");
    }

    @FXML
    private void handleKelolaTugasButtonAction(ActionEvent event) {
        // TODo (Imam): Jika ada dua tombol "Kelola Tugas", pastikan aksinya sesuai atau bedakan method handler-nya.
        //               Untuk saat ini, keduanya akan ke scene yang sama.
        System.out.println("Navigasi ke Manajemen Tugas...");
        gantiScene(event, "ManajemenTugasScene.fxml");
    }

    @FXML
    private void handleMinimizeAction(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void handleMaximizeAction(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setMaximized(!stage.isMaximized());
    }

    @FXML
    private void handleCloseAction(ActionEvent event) {
        // Ambil stage (window) dari elemen yang diklik (yaitu tombol itu sendiri)
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // Tutup aplikasi
        stage.close();
    }

}