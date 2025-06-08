# Proyek Akhir PBO: AsistenBelajarKu

**AsistenBelajarKu** adalah aplikasi desktop yang dirancang untuk membantu siswa dan pelajar dalam mengelola jadwal pelajaran dan melacak tugas-tugas akademik mereka. Aplikasi ini dibuat sebagai Proyek Akhir Laboratorium Pemrograman Berorientasi Objek.

## 👥 Tim Pengembang

- **Imam** - (Fokus: UI/UX, FXML, CSS, Controller)
- **Ryan** - (Fokus: Manajemen Data, Service, App Logic, Testing)
- **Akram** - (Fokus: Model Data, Struktur OOP, Logika Inti)

## 🎨 Tema

**Pendidikan**: Aplikasi untuk manajemen jadwal dan tugas siswa.

## ✨ Fitur Utama

- **Dashboard Utama**: Menampilkan ringkasan jadwal untuk hari ini dan tugas-tugas yang mendekati tenggat waktu.
- **Manajemen Jadwal**: Fungsionalitas CRUD (Create, Read, Update, Delete) untuk mengelola jadwal pelajaran mingguan.
- **Manajemen Tugas**: Fungsionalitas CRUD untuk mencatat, mengedit, menghapus, dan mengubah status penyelesaian tugas.
- **Filter Data**: Memungkinkan pengguna untuk memfilter tampilan jadwal berdasarkan hari, dan tugas berdasarkan status atau kedekatan tenggat waktu.
- **Penyimpanan Data Lokal**: Semua data jadwal dan tugas disimpan secara lokal dalam format file JSON, sehingga data tetap ada saat aplikasi ditutup dan dibuka kembali.
- **Tampilan Kustom**: Antarmuka pengguna yang dirancang agar bersih, modern, dan intuitif, lengkap dengan logo dan tombol kontrol window kustom.

## 🛠️ Teknologi dan Library

- **Bahasa**: Java
- **Framework UI**: JavaFX
- **Build Tool**: Gradle
- **Library**:
- **Jackson**: Untuk serialisasi dan deserialisasi data dari/ke format JSON.

## ⚙️ Cara Menjalankan Aplikasi

Aplikasi ini dibangun menggunakan Gradle. Pastikan Anda memiliki JDK (Java Development Kit) versi 21 atau yang lebih baru terpasang di sistem Anda.

1.  **Clone Repository:**

    ```bash
    git clone https://github.com/ShinZeleo/AsistenBelajarKu.git
    ```

2.  **Pindah ke Direktori Proyek:**

    ```bash
    cd AsistenBelajarKu
    ```

3.  **Jalankan Aplikasi menggunakan Gradle Wrapper:**

    - Di Windows (gunakan Command Prompt atau PowerShell):
      ```bash
      .\gradlew run
      ```
    - Di macOS atau Linux (gunakan Terminal):
      ```bash
      ./gradlew run
      ```

Gradle akan secara otomatis mengunduh semua dependensi yang dibutuhkan dan menjalankan aplikasi.
