package asistenbelajarku.service;

import asistenbelajarku.model.DataAplikasi;

public interface iPenyimpananService {
    void simpanSemuaData(DataAplikasi dataAplikasi);
    // TODO (Ryan): Jelaskan tujuan metode ini.
    // Penjelasan: Metode ini mendefinisikan kontrak untuk menyimpan seluruh data aplikasi
    //           (yang terbungkus dalam objek DataAplikasi) ke media penyimpanan permanen (misalnya file).

    DataAplikasi muatSemuaData();
    // TODO (Ryan): Jelaskan tujuan metode ini.
    // Penjelasan: Metode ini mendefinisikan kontrak untuk memuat seluruh data aplikasi
    //           dari media penyimpanan permanen. Jika tidak ada data tersimpan,
    //           sebaiknya mengembalikan objek DataAplikasi baru yang kosong.
}