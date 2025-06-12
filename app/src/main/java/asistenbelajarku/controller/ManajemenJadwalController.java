package asistenbelajarku.controller;

import asistenbelajarku.model.DataAplikasi;
import asistenbelajarku.model.JadwalSesi;
import asistenbelajarku.model.MataPelajaran;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.ResourceBundle;




public class ManajemenJadwalController implements Initializable {

    // --- Deklarasi @FXML ---
    @FXML private TableView<JadwalSesi> jadwalTableView;
    @FXML private TableColumn<JadwalSesi, String> kolomHari;
    @FXML private TableColumn<JadwalSesi, LocalTime> kolomWaktuMulai;
    @FXML private TableColumn<JadwalSesi, LocalTime> kolomWaktuSelesai;
    @FXML private TableColumn<JadwalSesi, String> kolomMataPelajaran; 
    @FXML private TableColumn<JadwalSesi, String> kolomRuangan;
    @FXML private TableColumn<JadwalSesi, String> kolomNamaGuru;

    @FXML private ComboBox<String> hariComboBox;
    @FXML private TextField waktuMulaiField;
    @FXML private TextField waktuSelesaiField;
    @FXML private TextField mataPelajaranField; 
    @FXML private TextField namaGuruField; 
    @FXML private TextField ruanganField;

    @FXML private Button tambahSesiButton; 
    @FXML private Button hapusSesiButton;
    @FXML private Button bersihkanFormButton;
    @FXML private Button kembaliKeDashboardButton;

    @FXML private ImageView logoImageView;
    @FXML private Button minimizeButton;
    @FXML private Button maximizeButton;
    @FXML private Button closeButton;

    // --- Atribut Lain ---
    private iPenyimpananService penyimpananService;
    private ObservableList<JadwalSesi> daftarSesiObservable;
    private DataAplikasi dataAplikasiSaatIni;
    private JadwalSesi sesiUntukDiedit; 
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public ManajemenJadwalController() {
        // Inisialisasi penyimpananService
        this.penyimpananService = new PenyimpananService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Konfigurasi TableView (kolom dan CellValueFactory)
        kolomHari.setCellValueFactory(new PropertyValueFactory<>("hari"));
        kolomWaktuMulai.setCellValueFactory(new PropertyValueFactory<>("waktuMulai"));
        kolomWaktuSelesai.setCellValueFactory(new PropertyValueFactory<>("waktuSelesai"));
        kolomMataPelajaran.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getMataPelajaran().getNamaMapel())
        );
        kolomRuangan.setCellValueFactory(new PropertyValueFactory<>("ruangan"));

        kolomNamaGuru.setCellValueFactory(cellData -> {
            MataPelajaran mapel = cellData.getValue().getMataPelajaran();
            String namaGuru = (mapel != null && mapel.getNamaGuru() != null) ? mapel.getNamaGuru() : "-";
            return new javafx.beans.property.SimpleStringProperty(namaGuru);
        });

        // Isi ComboBox hari
        hariComboBox.setItems(FXCollections.observableArrayList("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"));

        // Muat data jadwal awal
        muatDanTampilkanJadwal();

        // Tambahkan listener ke TableView untuk mengisi form saat item dipilih (untuk mode edit)
        jadwalTableView.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    sesiUntukDiedit = newSelection;
                } else {
                    sesiUntukDiedit = null;
                }
            }
        );
        System.out.println("ManajemenJadwalController terinisialisasi.");
    }

    private void tampilkanDetailSesiKeForm(JadwalSesi sesi) {
        if (sesi != null) {
            hariComboBox.setValue(sesi.getHari());
            waktuMulaiField.setText(sesi.getWaktuMulai().format(timeFormatter));
            waktuSelesaiField.setText(sesi.getWaktuSelesai().format(timeFormatter));
            mataPelajaranField.setText(sesi.getMataPelajaran().getNamaMapel());
            namaGuruField.setText(sesi.getMataPelajaran().getNamaGuru() != null ? sesi.getMataPelajaran().getNamaGuru() : "");
            ruanganField.setText(sesi.getRuangan() != null ? sesi.getRuangan() : "");
            tambahSesiButton.setText("Simpan Perubahan"); 
        } else {
            bersihkanForm();
        }
    }

    private void muatDanTampilkanJadwal() {
        // Muat data dari penyimpananService
        dataAplikasiSaatIni = penyimpananService.muatSemuaData();

        if (dataAplikasiSaatIni != null && dataAplikasiSaatIni.getDaftarSesi() != null) {
            daftarSesiObservable = FXCollections.observableArrayList(dataAplikasiSaatIni.getDaftarSesi());
        } else {
            daftarSesiObservable = FXCollections.observableArrayList();
            if (dataAplikasiSaatIni == null) {
                dataAplikasiSaatIni = new DataAplikasi();
                dataAplikasiSaatIni.setDaftarSesi(FXCollections.observableArrayList());
                if (dataAplikasiSaatIni.getDaftarMataPelajaran() == null) {
                    dataAplikasiSaatIni.setDaftarMataPelajaran(FXCollections.observableArrayList());
                }
            }
        }
        jadwalTableView.setItems(daftarSesiObservable);
    }

    // Metode helper untuk mencari atau membuat MataPelajaran
    private MataPelajaran cariAtauBuatMataPelajaran(String namaMapel, String namaGuru) {
        if (dataAplikasiSaatIni.getDaftarMataPelajaran() == null) {
            dataAplikasiSaatIni.setDaftarMataPelajaran(FXCollections.observableArrayList());
        }
        // Cari mapel yang sudah ada
        for (MataPelajaran mapel : dataAplikasiSaatIni.getDaftarMataPelajaran()) {
            if (mapel.getNamaMapel().equalsIgnoreCase(namaMapel) &&
                ((mapel.getNamaGuru() == null && (namaGuru == null || namaGuru.isEmpty())) ||
                 (mapel.getNamaGuru() != null && mapel.getNamaGuru().equalsIgnoreCase(namaGuru)))) {
                return mapel;
            }
        }
        // Jika tidak ditemukan, buat baru dan tambahkan ke list global
        MataPelajaran mapelBaru = new MataPelajaran(namaMapel, namaGuru.isEmpty() ? null : namaGuru);
        dataAplikasiSaatIni.getDaftarMataPelajaran().add(mapelBaru);
        return mapelBaru;
    }


    @FXML
    private void handleTambahSesiButtonAction(ActionEvent event) {
        // Validasi input dari form
        String hari = hariComboBox.getValue();
        String strWaktuMulai = waktuMulaiField.getText().trim();
        String strWaktuSelesai = waktuSelesaiField.getText().trim();
        String namaMapel = mataPelajaranField.getText().trim();
        String namaGuru = namaGuruField.getText().trim(); 
        String ruangan = ruanganField.getText().trim();

        if (hari == null || strWaktuMulai.isEmpty() || strWaktuSelesai.isEmpty() || namaMapel.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Tidak Valid", "Hari, Waktu Mulai, Waktu Selesai, dan Nama Mata Pelajaran tidak boleh kosong.");
            return;
        }

        LocalTime waktuMulai;
        LocalTime waktuSelesai;
        try {
            waktuMulai = LocalTime.parse(strWaktuMulai, timeFormatter);
            waktuSelesai = LocalTime.parse(strWaktuSelesai, timeFormatter);
            if (waktuSelesai.isBefore(waktuMulai) || waktuSelesai.equals(waktuMulai)) {
                showAlert(Alert.AlertType.ERROR, "Input Tidak Valid", "Waktu Selesai harus setelah Waktu Mulai.");
                return;
            }
        } catch (DateTimeParseException e) {
            showAlert(Alert.AlertType.ERROR, "Format Waktu Salah", "Gunakan format JJ:MM untuk waktu (contoh: 08:00).");
            return;
        }

        MataPelajaran mapel = cariAtauBuatMataPelajaran(namaMapel, namaGuru);

        if (sesiUntukDiedit == null) { 
            String namaDeskriptifSesi = mapel.getNamaMapel() + " (" + hari + ")";
            JadwalSesi sesiBaru = new JadwalSesi(namaDeskriptifSesi, hari, waktuMulai, waktuSelesai, mapel, ruangan);

            if (dataAplikasiSaatIni.getDaftarSesi() == null) {
                dataAplikasiSaatIni.setDaftarSesi(FXCollections.observableArrayList());
            }
            dataAplikasiSaatIni.getDaftarSesi().add(sesiBaru);
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Jadwal sesi baru berhasil ditambahkan.");
        } else { 
            sesiUntukDiedit.setHari(hari);
            sesiUntukDiedit.setWaktuMulai(waktuMulai);
            sesiUntukDiedit.setWaktuSelesai(waktuSelesai);
            sesiUntukDiedit.setMataPelajaran(mapel); 
            sesiUntukDiedit.setRuangan(ruangan);
            sesiUntukDiedit.setNamaDeskriptif(mapel.getNamaMapel() + " (" + hari + ")"); 
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Jadwal sesi berhasil diperbarui.");
        }

        penyimpananService.simpanSemuaData(dataAplikasiSaatIni);
        muatDanTampilkanJadwal(); 
        bersihkanForm(); 
    }

    @FXML
    private void handleEditSesiButtonAction(ActionEvent event) {
        JadwalSesi sesiTerpilihDariTabel = jadwalTableView.getSelectionModel().getSelectedItem();
        if (sesiTerpilihDariTabel != null) {
            sesiUntukDiedit = sesiTerpilihDariTabel; 
            tampilkanDetailSesiKeForm(sesiUntukDiedit); 
        } else {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih jadwal sesi yang akan diedit dari tabel.");
        }
    }

    @FXML
    private void handleHapusSesiButtonAction(ActionEvent event) {
        JadwalSesi sesiTerpilih = jadwalTableView.getSelectionModel().getSelectedItem();
        if (sesiTerpilih != null) {
            Alert konfirmasi = new Alert(Alert.AlertType.CONFIRMATION);
            konfirmasi.setTitle("Konfirmasi Hapus");
            konfirmasi.setHeaderText("Hapus Jadwal Sesi");
            konfirmasi.setContentText("Apakah Anda yakin ingin menghapus jadwal: " + sesiTerpilih.getRingkasanTampilan() + "?");

            Optional<ButtonType> result = konfirmasi.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                dataAplikasiSaatIni.getDaftarSesi().remove(sesiTerpilih);
                penyimpananService.simpanSemuaData(dataAplikasiSaatIni);
                muatDanTampilkanJadwal(); // Refresh tabel
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Jadwal sesi berhasil dihapus.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih jadwal sesi yang akan dihapus dari tabel.");
        }
    }

    @FXML
    private void handleBersihkanFormButtonAction(ActionEvent event) {
        bersihkanForm();
    }

    private void bersihkanForm() {
        hariComboBox.setValue(null);
        waktuMulaiField.clear();
        waktuSelesaiField.clear();
        mataPelajaranField.clear();
        namaGuruField.clear();
        ruanganField.clear();
        sesiUntukDiedit = null; 
        tambahSesiButton.setText("Tambah Jadwal Baru"); 
        jadwalTableView.getSelectionModel().clearSelection(); 
        mataPelajaranField.requestFocus(); 
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null); 
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Metode helper untuk navigasi
    private void gantiScene(ActionEvent event, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/" + fxmlFile));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); 
            showAlert(Alert.AlertType.ERROR, "Error Navigasi", "Tidak dapat memuat halaman '" + fxmlFile + "'.\nDetail: " + e.getMessage());
        }
    }

    @FXML
    private void handleKembaliKeDashboardButtonAction(ActionEvent event) {
        System.out.println("Navigasi kembali ke Dashboard...");
        gantiScene(event, "DashboardScene.fxml");
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
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}