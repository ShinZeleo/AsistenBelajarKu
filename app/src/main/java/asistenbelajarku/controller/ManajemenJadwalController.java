package asistenbelajarku.controller;

import asistenbelajarku.model.DataAplikasi;
import asistenbelajarku.model.JadwalSesi;
import asistenbelajarku.model.MataPelajaran;
import asistenbelajarku.service.iPenyimpananService;
// import asistenbelajarku.service.PenyimpananService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView; // Atau ListView
import javafx.scene.control.TextField; // Dan komponen form lainnya
// import komponen UI lainnya

import java.net.URL;
import java.util.ResourceBundle;

public class ManajemenJadwalController implements Initializable {

    // TODO (Imam): Deklarasikan semua @FXML elemen dari ManajemenJadwalScene.fxml
    //               (TableView untuk daftar jadwal, TextField/ComboBox/DatePicker untuk form input, Button).
    @FXML private TableView<JadwalSesi> jadwalTableView;
    // ... FXML lainnya untuk form ...

    private iPenyimpananService penyimpananService;
    private ObservableList<JadwalSesi> daftarSesiObservable;
    private DataAplikasi dataAplikasiSaatIni;
    private JadwalSesi sesiUntukDiedit;

    public ManajemenJadwalController() {
        // TODO (Imam): Inisialisasi penyimpananService.
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO (Imam): Konfigurasi TableView (kolom dan CellValueFactory untuk menampilkan data JadwalSesi).
        // TODO (Imam): Isi ComboBox (misalnya untuk hari) jika ada.
        // TODO (Imam): Muat data jadwal awal dari penyimpananService dan tampilkan di TableView.
        // TODO (Imam): Tambahkan listener ke TableView untuk mengisi form saat item dipilih (untuk mode edit).
    }

    private void muatDanTampilkanJadwal() {
        // TODO (Imam): Muat data dari penyimpananService.
        // TODO (Imam): Update daftarSesiObservable dan set sebagai items untuk jadwalTableView.
    }

    @FXML
    private void handleTambahSesiButtonAction(ActionEvent event) {
        // TODO (Imam): Validasi input dari form.
        // TODO (Imam): Ambil data dari form, buat objek MataPelajaran baru (atau pilih dari daftar jika ada), lalu buat objek JadwalSesi.
        // TODO (Imam): Jika sesiUntukDiedit == null (mode tambah): tambahkan JadwalSesi baru ke dataAplikasiSaatIni.getDaftarSesi().
        // TODO (Imam): Jika sesiUntukDiedit != null (mode edit): update atribut pada objek sesiUntukDiedit.
        // TODO (Imam): Panggil penyimpananService.simpanSemuaData(dataAplikasiSaatIni).
        // TODO (Imam): Panggil muatDanTampilkanJadwal() untuk me-refresh tabel.
        // TODO (Imam): Panggil metode untuk membersihkan form input dan mereset sesiUntukDiedit ke null.
    }

    @FXML
    private void handleEditSesiButtonAction(ActionEvent event) {
        // TODO (Imam): Dapatkan JadwalSesi yang dipilih dari jadwalTableView.
        // TODO (Imam): Jika ada yang dipilih, simpan ke sesiUntukDiedit.
        // TODO (Imam): Isi field-field form dengan data dari sesiUntukDiedit.
        // TODO (Imam): (Opsional) Ubah teks tombol "Tambah Sesi" menjadi "Simpan Perubahan".
    }

    @FXML
    private void handleHapusSesiButtonAction(ActionEvent event) {
        // TODO (Imam): Dapatkan JadwalSesi yang dipilih dari jadwalTableView.
        // TODO (Imam): Tampilkan dialog konfirmasi penghapusan.
        // TODO (Imam): Jika dikonfirmasi, hapus sesi tersebut dari dataAplikasiSaatIni.getDaftarSesi().
        // TODO (Imam): Panggil penyimpananService.simpanSemuaData(dataAplikasiSaatIni).
        // TODO (Imam): Panggil muatDanTampilkanJadwal() untuk me-refresh tabel.
    }

    @FXML
    private void handleBersihkanFormButtonAction(ActionEvent event) {
        // TODO (Imam): Kosongkan semua field input di form.
        // TODO (Imam): Set sesiUntukDiedit ke null.
        // TODO (Imam): (Opsional) Kembalikan teks tombol ke "Tambah Sesi".
    }

    @FXML
    private void handleKembaliKeDashboardButtonAction(ActionEvent event) {
        // TODO (Imam): Implementasikan navigasi kembali ke scene Dashboard.
    }
}