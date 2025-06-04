module asistenbelajarku { // Gunakan nama modul yang sesuai (biasanya sama dengan package utama)
    requires javafx.controls;
    requires javafx.fxml;

    opens asistenbelajarku to javafx.fxml;
    opens asistenbelajarku.controller to javafx.fxml; // Jika controller di package terpisah

    exports asistenbelajarku; // Export package utama agar kelas App bisa dijalankan
}