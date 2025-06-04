package asistenbelajarku.controller;

import asistenbelajarku.model.DataAplikasi;
import asistenbelajarku.model.MataPelajaran;
import asistenbelajarku.model.Tugas;
import asistenbelajarku.service.iPenyimpananService;
// import asistenbelajarku.service.PenyimpananService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView; // Atau ListView
import javafx.scene.control.TextArea; // Dan komponen form lainnya
// import komponen UI lainnya

import java.net.URL;
import java.util.ResourceBundle;

public class ManajemenTugasController implements Initializable {

    // TODO (Imam): Deklarasikan semua @FXML elemen dari ManajemenTugasScene.fxml
    //               (TableView untuk daftar tugas, TextArea/TextField/DatePicker/ComboBox untuk form input, Button).
    @FXML private TableView<Tugas> tugasTableView;
    // ... FXML lainnya untuk form ...

    private iPenyimpananService penyimpananService;
    private ObservableList<Tugas> daftarTugasObservable;
    private DataAplikasi dataAplikasiSaatIni;
    private Tugas tugasUntukDiedit;

    public ManajemenTugasController() {
        // TODO (Imam): Inisialisasi penyimpananService.
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO (Imam): Konfigurasi TableView (kolom dan CellValueFactory untuk menampilkan data Tugas).
        // TODO (Imam): Isi ComboBox (misalnya untuk status tugas) jika ada.
        // TODO (Imam): Muat data tugas awal dari penyimpananService dan tampilkan di TableView.
        // TODO (Imam): Tambahkan listener ke TableView untuk mengisi form saat item dipilih (untuk mode edit).
    }

    private void muatDanTampilkanTugas() {
        // TODO (Imam): Muat data dari penyimpananService.
        // TODO (Imam): Update daftarTugasObservable dan set sebagai items untuk tugasTableView.
    }

    @FXML
    private void handleTambahTugasButtonAction(ActionEvent event) {
        // TODO (Imam): Validasi input dari form.
        // TODO (Imam): Ambil data dari form, buat objek MataPelajaran jika perlu, lalu buat objek Tugas.
        // TODO (Imam): Jika tugasUntukDiedit == null (mode tambah): tambahkan Tugas baru ke dataAplikasiSaatIni.getDaftarTugas().
        // TODO (Imam): Jika tugasUntukDiedit != null (mode edit): update atribut pada objek tugasUntukDiedit.
        // TODO (Imam): Panggil penyimpananService.simpanSemuaData(dataAplikasiSaatIni).
        // TODO (Imam): Panggil muatDanTampilkanTugas() untuk me-refresh tabel.
        // TODO (Imam): Panggil metode untuk membersihkan form input dan mereset tugasUntukDiedit ke null.
    }

    @FXML
    private void handleEditTugasButtonAction(ActionEvent event) {
        // TODO (Imam): Dapatkan Tugas yang dipilih dari tugasTableView.
        // TODO (Imam): Jika ada yang dipilih, simpan ke tugasUntukDiedit.
        // TODO (Imam): Isi field-field form dengan data dari tugasUntukDiedit.
    }

    @FXML
    private void handleHapusTugasButtonAction(ActionEvent event) {
        // TODO (Imam): Dapatkan Tugas yang dipilih dari tugasTableView.
        // TODO (Imam): Tampilkan dialog konfirmasi penghapusan.
        // TODO (Imam): Jika dikonfirmasi, hapus tugas tersebut dari dataAplikasiSaatIni.getDaftarTugas().
        // TODO (Imam): Panggil penyimpananService.simpanSemuaData(dataAplikasiSaatIni).
        // TODO (Imam): Panggil muatDanTampilkanTugas() untuk me-refresh tabel.
    }

    @FXML
    private void handleBersihkanFormTugasButtonAction(ActionEvent event) {
        // TODO (Imam): Kosongkan semua field input di form.
        // TODO (Imam): Set tugasUntukDiedit ke null.
    }

    @FXML
    private void handleKembaliKeDashboardButtonTugasAction(ActionEvent event) {
        // TODO (Imam): Implementasikan navigasi kembali ke scene Dashboard.
    }
}