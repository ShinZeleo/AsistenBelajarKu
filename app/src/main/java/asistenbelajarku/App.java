package asistenbelajarku;

import asistenbelajarku.service.PenyimpananService;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class App extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // TOD (Ryan): Implementasikan pemuatan file FXML untuk scene utama (DashboardScene.fxml).
        // Penjelasan: Gunakan FXMLLoader untuk memuat file FXML dari folder resources/fxml/.
        //           Buat objek Scene dari hasil load FXML tersebut.
        //           Atur judul untuk primaryStage (misalnya, "AsistenBelajarKu").
        //           Set Scene yang sudah dibuat ke primaryStage.
        //           Tampilkan primaryStage menggunakan primaryStage.show().
        //           Pastikan path ke FXML sudah benar (misalnya, "/fxml/DashboardScene.fxml").
        //           Atur juga ukuran awal window (lebar dan tinggi) yang sesuai untuk Scene.

                // --- Implementasi TODo ---
        // Pastikan path "/fxml/DashboardScene.fxml" benar relatif terhadap folder 'resources'
        this.primaryStage = primaryStage;
        PenyimpananService penyimpananService = new PenyimpananService();
        penyimpananService.muatSemuaData();

        showDashboardScene();
    }

    private void showDashboardScene() throws IOException {
        URL fxmlLocation = getClass().getResource("/fxml/DashboardScene.fxml");
        if (fxmlLocation == null) {
            // Anda bisa throw IOException atau menangani error ini lebih lanjut
            System.err.println("Tidak dapat menemukan DashboardScene.fxml.");
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR, "File FXML tidak ditemukan. Aplikasi akan ditutup.");
            alert.showAndWait();
            System.exit(1);
        }

        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();
        // DashboardController controller = loader.getController();
        // controller.setApp(this);
        // controller.setData(dataAplikasi);

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("AsistenBelajarKu");
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showManajemenJadwalScene() throws IOException {
        URL fxmlLocation = getClass().getResource("/fxml/ManajemenJadwalScene.fxml");
        if (fxmlLocation == null) {
            System.err.println("Tidak dapat menemukan ManajemenJadwalScene.fxml.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();
        // ManajemenJadwalController controller = loader.getController();
        // controller.setApp(this);
        // controller.setData(dataAplikasi);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
    }

    public void showManajemenTugasScene() throws IOException {
        URL fxmlLocation = getClass().getResource("/fxml/ManajemenTugasScene.fxml");
        if (fxmlLocation == null) {
            System.err.println("Tidak dapat menemukan ManajemenTugasScene.fxml.");
            return;
        }

        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();
        // ManajemenTugasController controller = loader.getController();
        // controller.setApp(this);
        // controller.setData(dataAplikasi);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
    }
        // --- Akhir Implementasi TOD ---
    public static void main(String[] args) {
                        // TODo (Tim): Pastikan metode ini hanya memanggil launch(args) dan tidak ada logika aplikasi lain di sini.
        // Penjelasan: Metode main() ini adalah titik masuk standar untuk aplikasi Java,
        //           dan untuk aplikasi JavaFX, ia hanya perlu memanggil launch(args)
        //           untuk memulai lifecycle JavaFX dan memanggil metode start().
        launch(args);
    }
}