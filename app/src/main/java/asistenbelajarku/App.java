package asistenbelajarku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle; // Import yang dibutuhkan
import java.io.IOException;
import java.net.URL;

public class App extends Application {

    // Variabel untuk menyimpan posisi mouse saat menekan window
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL fxmlLocation = getClass().getResource("/fxml/DashboardScene.fxml");
        if (fxmlLocation == null) {
            System.err.println("Tidak dapat menemukan DashboardScene.fxml.");
            System.exit(1);
        }

        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load(); // 'root' sekarang dideklarasikan di sini

        // --- Logika untuk membuat window bisa digeser (draggable) ---
        // Saat mouse ditekan, simpan posisi awal X dan Y
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        // Saat mouse digeser, ubah posisi window sesuai pergeseran mouse
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        // --- Akhir logika draggable ---

        // Menghilangkan baris judul, tombol minimize, maximize, close bawaan window
        primaryStage.initStyle(StageStyle.UNDECORATED);

        Scene scene = new Scene(root, 950, 650); // Ukuran bisa disesuaikan
        primaryStage.setTitle("AsistenBelajarKu"); // Judul ini tetap penting untuk taskbar
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}