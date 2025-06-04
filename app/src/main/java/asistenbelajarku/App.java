package asistenbelajarku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // TODO (Ryan/Akram): Implementasikan pemuatan file FXML untuk scene utama (DashboardScene.fxml).
        // Penjelasan: Gunakan FXMLLoader untuk memuat file FXML dari folder resources/fxml/.
        //           Buat objek Scene dari hasil load FXML tersebut.
        //           Atur judul untuk primaryStage (misalnya, "AsistenBelajarKu").
        //           Set Scene yang sudah dibuat ke primaryStage.
        //           Tampilkan primaryStage menggunakan primaryStage.show().
        //           Pastikan path ke FXML sudah benar (misalnya, "/fxml/DashboardScene.fxml").
        //           Atur juga ukuran awal window (lebar dan tinggi) yang sesuai untuk Scene.

                // --- Implementasi TODO ---
        // Pastikan path "/fxml/DashboardScene.fxml" benar relatif terhadap folder 'resources'
        URL fxmlLocation = getClass().getResource("/fxml/DashboardScene.fxml");
        if (fxmlLocation == null) {
            System.err.println("Tidak dapat menemukan file FXML. Pastikan path sudah benar.");
            // Anda bisa throw IOException atau menangani error ini lebih lanjut
            throw new IOException("File FXML tidak ditemukan di path tersebut.");
        }

        Parent root = FXMLLoader.load(fxmlLocation); // FXMLLoader.load() akan memuat FXML
        Scene scene = new Scene(root, 900, 600); // Lebar 900, Tinggi 600 (sesuai prefWidth/prefHeight di FXML)

        primaryStage.setTitle("AsistenBelajarKu");
        primaryStage.setScene(scene);
        primaryStage.show();
        // --- Akhir Implementasi TODO ---
    }

    public static void main(String[] args) {
        // TODO (Tim): Pastikan metode ini hanya memanggil launch(args) dan tidak ada logika aplikasi lain di sini.
        // Penjelasan: Metode main() ini adalah titik masuk standar untuk aplikasi Java,
        //           dan untuk aplikasi JavaFX, ia hanya perlu memanggil launch(args)
        //           untuk memulai lifecycle JavaFX dan memanggil metode start().
        launch(args);
    }
}