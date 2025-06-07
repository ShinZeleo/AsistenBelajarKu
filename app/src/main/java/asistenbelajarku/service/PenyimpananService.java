package asistenbelajarku.service;

import asistenbelajarku.model.DataAplikasi;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PenyimpananService implements iPenyimpananService {

    private static final String NAMA_FILE_DATA = "asisten_belajar_data.json";
    private final ObjectMapper objectMapper;

    public PenyimpananService() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println("PenyimpananService diinisialisasi.");
    }

    @Override
    public void simpanSemuaData(DataAplikasi dataAplikasi) {
        if (dataAplikasi == null) {
            System.err.println("Gagal menyimpan data: Objek DataAplikasi adalah null.");
            return;
        }
        try (FileWriter writer = new FileWriter(NAMA_FILE_DATA)) {
            objectMapper.writeValue(writer, dataAplikasi);
            System.out.println("Data berhasil disimpan ke " + NAMA_FILE_DATA);
        } catch (IOException e) {
            System.err.println("Gagal menyimpan data: " + e.getMessage());
        }
    }

    @Override
    public DataAplikasi muatSemuaData() {
        File file = new File(NAMA_FILE_DATA);
        if (!file.exists() || file.length() == 0) {
            System.out.println("File '" + NAMA_FILE_DATA + "' tidak ditemukan atau kosong. Mengembalikan DataAplikasi baru (kosong).");
            return new DataAplikasi(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        }
        try (FileReader reader = new FileReader(file)) {
            return objectMapper.readValue(reader, DataAplikasi.class);
        } catch (IOException e) {
            System.err.println("Gagal memuat data: " + e.getMessage());
            return new DataAplikasi(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        }
    }
}