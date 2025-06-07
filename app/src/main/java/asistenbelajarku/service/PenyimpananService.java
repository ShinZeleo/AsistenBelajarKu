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
        try {
            objectMapper.writeValue(new File(NAMA_FILE_DATA), dataAplikasi);
            System.out.println("Data berhasil disimpan ke " + NAMA_FILE_DATA);
        } catch (IOException e) {
            System.err.println("Gagal menyimpan data: " + e.getMessage());
        }
    }

    @Override
    public DataAplikasi muatSemuaData() {
        File file = new File(NAMA_FILE_DATA);
        if (!file.exists()) {
            System.out.println("File tidak ditemukan, mengembalikan DataAplikasi kosong.");
            return new DataAplikasi();
        }
        try {
            return objectMapper.readValue(file, DataAplikasi.class);
        } catch (IOException e) {
            System.err.println("Gagal memuat data: " + e.getMessage());
            return new DataAplikasi();
        }
    }
}