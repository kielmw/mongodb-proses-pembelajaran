package com.tugasakhir.mongodbprosespembelajaran.model;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.EmbeddedId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;


@Getter
@Setter
@Document(collection = "proses_updater")
public class ProsesUpdater {
    @Id
    private int idKelas;
    private String namaKelas;
    private String deskripsiKelas;
    private int idGuru;
    private String namaGuru;

    public ProsesUpdater(){

    }
    public ProsesUpdater(int idKelas, String namaKelas, String deskripsiKelas, int idGuru, String namaGuru) {
        this.idKelas = idKelas;
        this.namaKelas = namaKelas;
        this.deskripsiKelas = deskripsiKelas;
        this.idGuru = idGuru;
        this.namaGuru = namaGuru;
    }

    public int getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(int idKelas) {
        this.idKelas = idKelas;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public String getDeskripsiKelas() {
        return deskripsiKelas;
    }

    public void setDeskripsiKelas(String deskripsiKelas) {
        this.deskripsiKelas = deskripsiKelas;
    }

    public int getIdGuru() {
        return idGuru;
    }

    public void setIdGuru(int idGuru) {
        this.idGuru = idGuru;
    }

    public String getNamaGuru() {
        return namaGuru;
    }

    public void setNamaGuru(String namaGuru) {
        this.namaGuru = namaGuru;
    }
}
