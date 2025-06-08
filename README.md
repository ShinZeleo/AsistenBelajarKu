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

## 🏛️ Struktur Kode dan Penerapan OOP

Struktur proyek ini dirancang untuk menerapkan prinsip-prinsip Pemrograman Berorientasi Objek (OOP) secara efektif.

### Struktur Folder

```
AsistenBelajarKu/
├── build.gradle              // Konfigurasi Gradle
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── asistenbelajarku/
│   │   │       ├── App.java          // Kelas utama aplikasi
│   │   │       ├── controller/     // Berisi kelas-kelas Controller (Imam)
│   │   │       ├── model/          // Berisi kelas-kelas Model (Akram)
│   │   │       └── service/        // Berisi kelas-kelas Service (Ryan)
│   │   └── resources/
│   │       ├── fxml/             // Berisi file FXML untuk UI
│   │       └── css/              // Berisi file CSS untuk styling
├── asisten_belajar_data.json // File penyimpanan data
└── README.md                 // Dokumentasi ini
```

### Penerapan 4 Pilar OOP [cite: 2]

1.  **Encapsulation:**

    - Prinsip ini diterapkan pada semua kelas di _package_ `model` (misalnya `Tugas.java`, `JadwalSesi.java`, `MataPelajaran.java`).
    - Semua atribut (fields) dideklarasikan sebagai `private` untuk menyembunyikan data.
    - Akses ke atribut-atribut tersebut hanya bisa dilakukan melalui metode publik (public getters dan setters), yang memungkinkan adanya validasi dan kontrol terhadap data.

2.  **Inheritance:**

    - Struktur pewarisan dibuat dengan kelas abstrak `EntitasAkademik.java`.
    - Kelas `Tugas.java` dan `JadwalSesi.java` mewarisi (`extends`) dari `EntitasAkademik`. Ini memungkinkan mereka untuk mewarisi properti umum seperti `namaDeskriptif` dan `tanggalDibuat`, sehingga mengurangi duplikasi kode.
    - [cite\_start]Ini adalah penerapan pewarisan yang dibuat sendiri dan bukan pewarisan dari kelas JavaFX, sesuai dengan ketentuan proyek. [cite: 2]

3.  **Abstraction:**

    - **Kelas Abstrak**: `EntitasAkademik.java` adalah kelas abstrak yang mendefinisikan "template" untuk semua entitas akademik. Ia memiliki metode abstrak seperti `getJenisEntitas()` dan `getRingkasanTampilan()` yang harus diimplementasikan oleh setiap kelas turunannya.
    - **Interface**: `iPenyimpananService.java` adalah sebuah _interface_ yang mendefinisikan "kontrak" untuk layanan penyimpanan. Ia menentukan metode apa yang harus dimiliki oleh kelas penyimpanan (`simpanSemuaData`, `muatSemuaData`) tanpa peduli bagaimana cara penyimpanannya (misalnya, ke JSON, database, dll.). `PenyimpananService.java` adalah implementasi konkret dari _interface_ ini.

4.  **Polymorphism:**

    - **Melalui Pewarisan**: Jika kita memiliki `List<EntitasAkademik>`, kita bisa memasukkan objek `Tugas` dan `JadwalSesi` ke dalamnya. Saat kita memanggil metode `getRingkasanTampilan()` pada setiap item di list tersebut, Java akan secara otomatis memanggil versi metode yang benar sesuai dengan tipe objeknya (versi `Tugas` atau versi `JadwalSesi`).
    - **Melalui Interface**: _Controller_ berinteraksi dengan `iPenyimpananService` tanpa perlu tahu apakah implementasi di belakangnya adalah `PenyimpananService` (yang menyimpan ke JSON) atau implementasi lain di masa depan. Ini membuat kode lebih fleksibel.
