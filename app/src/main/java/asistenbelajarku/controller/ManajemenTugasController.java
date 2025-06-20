package asistenbelajarku.controller;

import asistenbelajarku.model.DataAplikasi;
import asistenbelajarku.model.MataPelajaran;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell; 
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManajemenTugasController implements Initializable {

    @FXML private TableView<Tugas> tugasTableView;
    @FXML private TableColumn<Tugas, String> kolomDeskripsiTugas;
    @FXML private TableColumn<Tugas, String> kolomMataPelajaranTugas;
    @FXML private TableColumn<Tugas, LocalDate> kolomTenggatWaktuTugas;
    @FXML private TableColumn<Tugas, Boolean> kolomStatusTugas; 

    @FXML private TextArea deskripsiTugasArea;
    @FXML private TextField mataPelajaranTugasField;
    @FXML private DatePicker tanggalTenggatPicker;
    @FXML private ComboBox<String> statusTugasComboBox;

    @FXML private Button tambahTugasButton; 
    @FXML private Button hapusTugasButton;
    @FXML private Button bersihkanFormTugasButton;
    @FXML private Button kembaliKeDashboardButtonTugas; 

    @FXML private ImageView logoImageView;
    @FXML private Button minimizeButton;
    @FXML private Button maximizeButton;
    @FXML private Button closeButton;

    private iPenyimpananService penyimpananService;
    private ObservableList<Tugas> daftarTugasObservable;
    private DataAplikasi dataAplikasiSaatIni;
    private Tugas tugasUntukDiedit;

    public ManajemenTugasController() {
        // Inisialisasi penyimpananService
        this.penyimpananService = new PenyimpananService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        kolomDeskripsiTugas.setCellValueFactory(new PropertyValueFactory<>("namaDeskriptif")); 
        kolomMataPelajaranTugas.setCellValueFactory(cellData -> {
            MataPelajaran mapel = cellData.getValue().getMataPelajaranTerkait();
            return new javafx.beans.property.SimpleStringProperty(mapel != null ? mapel.getNamaMapel() : "-");
        });
        kolomTenggatWaktuTugas.setCellValueFactory(new PropertyValueFactory<>("tanggalTenggat"));
        kolomStatusTugas.setCellValueFactory(new PropertyValueFactory<>("selesai"));
        kolomStatusTugas.setCellFactory(column -> new TableCell<Tugas, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item ? "Selesai" : "Belum Selesai");
                }
            }
        });


        // Isi ComboBox status
        statusTugasComboBox.setItems(FXCollections.observableArrayList("Belum Selesai", "Selesai"));

        // Muat data tugas awal
        muatDanTampilkanTugas();

        // Listener untuk TableView
        tugasTableView.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                }
            }
        );
        System.out.println("ManajemenTugasController terinisialisasi.");
    }

    private void tampilkanDetailTugas(Tugas tugas) {
        tugasUntukDiedit = tugas;
        if (tugas != null) {
            deskripsiTugasArea.setText(tugas.getNamaDeskriptif()); 
            mataPelajaranTugasField.setText(tugas.getMataPelajaranTerkait() != null ? tugas.getMataPelajaranTerkait().getNamaMapel() : "");
            tanggalTenggatPicker.setValue(tugas.getTanggalTenggat());
            statusTugasComboBox.setValue(tugas.isSelesai() ? "Selesai" : "Belum Selesai");
            tambahTugasButton.setText("Simpan Perubahan");
        } else {
            bersihkanForm();
        }
    }

    private void muatDanTampilkanTugas() {
        dataAplikasiSaatIni = penyimpananService.muatSemuaData();

        if (dataAplikasiSaatIni != null && dataAplikasiSaatIni.getDaftarTugas() != null) {
            daftarTugasObservable = FXCollections.observableArrayList(dataAplikasiSaatIni.getDaftarTugas());
        } else {
            daftarTugasObservable = FXCollections.observableArrayList();
            if (dataAplikasiSaatIni == null) {
                 dataAplikasiSaatIni = new DataAplikasi();
                 dataAplikasiSaatIni.setDaftarTugas(FXCollections.observableArrayList());
                 dataAplikasiSaatIni.setDaftarMataPelajaran(FXCollections.observableArrayList());
            }
        }
        // Set items untuk tugasTableView
        tugasTableView.setItems(daftarTugasObservable);
    }

    @FXML
    private void handleTambahTugasButtonAction(ActionEvent event) {
        // Validasi input
        String deskripsi = deskripsiTugasArea.getText().trim();
        String namaMapel = mataPelajaranTugasField.getText().trim();
        LocalDate tenggat = tanggalTenggatPicker.getValue();
        String statusStr = statusTugasComboBox.getValue();

        if (deskripsi.isEmpty() || tenggat == null || statusStr == null) {
            showAlert(Alert.AlertType.ERROR, "Input Tidak Valid", "Deskripsi, Tanggal Tenggat, dan Status tidak boleh kosong.");
            return;
        }

        // Cari atau buat MataPelajaran (mirip dengan di ManajemenJadwalController)
        MataPelajaran mapelTerkait = null;
        if (!namaMapel.isEmpty()) {
            mapelTerkait = cariAtauBuatMataPelajaranTugas(namaMapel);
        }

        boolean isSelesai = statusStr.equals("Selesai");

        if (tugasUntukDiedit == null) { 
            Tugas tugasBaru = new Tugas(deskripsi, deskripsi, tenggat, mapelTerkait); 
            tugasBaru.setSelesai(isSelesai);
            dataAplikasiSaatIni.getDaftarTugas().add(tugasBaru);
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Tugas baru berhasil ditambahkan.");
        } else { 
            tugasUntukDiedit.setNamaDeskriptif(deskripsi);
            tugasUntukDiedit.setDetailDeskripsiTugas(deskripsi);
            tugasUntukDiedit.setTanggalTenggat(tenggat);
            tugasUntukDiedit.setSelesai(isSelesai);
            tugasUntukDiedit.setMataPelajaranTerkait(mapelTerkait);
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Tugas berhasil diperbarui.");
        }

        penyimpananService.simpanSemuaData(dataAplikasiSaatIni);
        muatDanTampilkanTugas();
        bersihkanForm();
    }

    // Metode helper untuk mencari atau membuat MataPelajaran khusus untuk Tugas
    private MataPelajaran cariAtauBuatMataPelajaranTugas(String namaMapel) {
         if (dataAplikasiSaatIni.getDaftarMataPelajaran() == null) {
            dataAplikasiSaatIni.setDaftarMataPelajaran(FXCollections.observableArrayList());
        }
        Optional<MataPelajaran> existingMapel = dataAplikasiSaatIni.getDaftarMataPelajaran().stream()
            .filter(m -> m.getNamaMapel().equalsIgnoreCase(namaMapel))
            .findFirst();

        if (existingMapel.isPresent()) {
            return existingMapel.get();
        } else {
            MataPelajaran newMapel = new MataPelajaran(namaMapel, null); 
            dataAplikasiSaatIni.getDaftarMataPelajaran().add(newMapel);
            return newMapel;
        }
    }


    @FXML
    private void handleEditTugasButtonAction(ActionEvent event) {
        Tugas tugasTerpilihDariTabel = tugasTableView.getSelectionModel().getSelectedItem();
        if (tugasTerpilihDariTabel != null) {
            tampilkanDetailTugas(tugasTerpilihDariTabel);
        } else {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih tugas yang akan diedit dari tabel.");
        }
    }

    @FXML
    private void handleHapusTugasButtonAction(ActionEvent event) {
        Tugas tugasTerpilihDariTabel = tugasTableView.getSelectionModel().getSelectedItem();
        if (tugasTerpilihDariTabel != null) {
            Alert konfirmasi = new Alert(Alert.AlertType.CONFIRMATION,
                "Apakah Anda yakin ingin menghapus tugas: " + tugasTerpilihDariTabel.getNamaDeskriptif() + "?",
                ButtonType.YES, ButtonType.NO);
            konfirmasi.setHeaderText("Konfirmasi Hapus Tugas");
            konfirmasi.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    dataAplikasiSaatIni.getDaftarTugas().remove(tugasTerpilihDariTabel);
                    penyimpananService.simpanSemuaData(dataAplikasiSaatIni);
                    muatDanTampilkanTugas();
                    showAlert(Alert.AlertType.INFORMATION, "Sukses", "Tugas berhasil dihapus.");
                }
            });
        } else {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih tugas yang akan dihapus dari tabel.");
        }
    }

    @FXML
    private void handleBersihkanFormTugasButtonAction(ActionEvent event) {
        bersihkanForm();
    }

    private void bersihkanForm() {
        deskripsiTugasArea.clear();
        mataPelajaranTugasField.clear();
        tanggalTenggatPicker.setValue(null);
        statusTugasComboBox.setValue("Belum Selesai"); 
        tugasUntukDiedit = null;
        tambahTugasButton.setText("Tambah Tugas Baru");
        deskripsiTugasArea.requestFocus();
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
            showAlert(Alert.AlertType.ERROR, "Error Navigasi", "Tidak dapat memuat halaman " + fxmlFile);
        }
    }

    @FXML
    private void handleKembaliKeDashboardButtonTugasAction(ActionEvent event) {
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