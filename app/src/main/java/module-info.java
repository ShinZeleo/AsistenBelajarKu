module asistenbelajarku { // Sesuaikan dengan nama modul Anda
    requires transitive javafx.controls;
    requires javafx.fxml;


    // Tambahkan ini untuk Jackson
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310; // Jika menggunakan modul datatype Java Time

    // Pastikan package model di-opens ke Jackson agar bisa diakses via refleksi
    opens asistenbelajarku.model to com.fasterxml.jackson.databind;

    opens asistenbelajarku to javafx.fxml;
    opens asistenbelajarku.controller to javafx.fxml;

    exports asistenbelajarku;
}