package asistenbelajarku;

import asistenbelajarku.service.PenyimpananService;
import asistenbelajarku.model.DataAplikasi;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class App extends Application {
    private Stage primaryStage;
    private DataAplikasi dataAplikasi;

    /**
     * Memulai aplikasi JavaFX dengan menginisialisasi primaryStage dan memuat data aplikasi.
     * Menampilkan dashboard utama melalui metode showDashboardScene.
     * @param primaryStage Stage utama aplikasi
     * @throws IOException Jika terjadi kesalahan saat memuat file FXML
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        PenyimpananService penyimpananService = new PenyimpananService();
        this.dataAplikasi = penyimpananService.muatSemuaData();

        showDashboardScene();
    }

    /**
     * Menampilkan scene dashboard utama dengan memuat DashboardScene.fxml.
     * Mengatur controller dan data aplikasi, serta menampilkan primaryStage.
     * @throws IOException Jika file FXML tidak ditemukan atau gagal dimuat
     */
    private void showDashboardScene() throws IOException {
        URL fxmlLocation = getClass().getResource("/fxml/DashboardScene.fxml");
        if (fxmlLocation == null) {
            System.err.println("Tidak dapat menemukan DashboardScene.fxml.");
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR, "File FXML tidak ditemukan. Aplikasi akan ditutup.");
            alert.showAndWait();
            System.exit(1);
        }

        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();
        DashboardController controller = loader.getController();
        controller.setApp(this);
        controller.setData(dataAplikasi);

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("AsistenBelajarKu");
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Menampilkan scene manajemen jadwal dengan memuat ManajemenJadwalScene.fxml.
     * Mengatur controller dan data aplikasi untuk scene ini.
     * @throws IOException Jika file FXML tidak ditemukan atau gagal dimuat
     */
    public void showManajemenJadwalScene() throws IOException {
        URL fxmlLocation = getClass().getResource("/fxml/ManajemenJadwalScene.fxml");
        if (fxmlLocation == null) {
            System.err.println("Tidak dapat menemukan ManajemenJadwalScene.fxml.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();
        ManajemenJadwalController controller = loader.getController();
        controller.setApp(this);
        controller.setData(dataAplikasi);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
    }

    /**
     * Menampilkan scene manajemen tugas dengan memuat ManajemenTugasScene.fxml.
     * Mengatur controller dan data aplikasi untuk scene ini.
     * @throws IOException Jika file FXML tidak ditemukan atau gagal dimuat
     */
    public void showManajemenTugasScene() throws IOException {
        URL fxmlLocation = getClass().getResource("/fxml/ManajemenTugasScene.fxml");
        if (fxmlLocation == null) {
            System.err.println("Tidak dapat menemukan ManajemenTugasScene.fxml.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();
        ManajemenTugasController controller = loader.getController();
        controller.setApp(this);
        controller.setData(dataAplikasi);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}