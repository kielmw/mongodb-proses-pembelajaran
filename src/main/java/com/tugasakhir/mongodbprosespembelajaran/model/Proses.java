package com.tugasakhir.mongodbprosespembelajaran.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Document("proses")
public class Proses {
    @Id
    @Indexed(unique = true)
    private String idKelas;
    @Indexed(unique = true)
    @Field(name = "namaKelas ")
    private String namaKelas;
    private String deskripsiKelas;
    private String namaGuru;
    private int idGuru;
    private List<Pdf> pdfs;
    private List<ItemPembelajaran> itemPembelajarans;

    public Proses(String idKelas, String namaKelas, String deskripsiKelas, String namaGuru, int idGuru, List<Pdf> pdfs, List<ItemPembelajaran> itemPembelajarans) {
        this.idKelas = idKelas;
        this.namaKelas = namaKelas;
        this.deskripsiKelas = deskripsiKelas;
        this.namaGuru = namaGuru;
        this.idGuru = idGuru;
        this.pdfs = pdfs;
        this.itemPembelajarans = itemPembelajarans;
    }

    public String getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(String idKelas) {
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

    public List<Pdf> getPdfs() {
        return pdfs;
    }

    public void setPdfs(List<Pdf> pdfs) {
        this.pdfs = pdfs;
    }

    public List<ItemPembelajaran> getItemPembelajarans() {
        return itemPembelajarans;
    }

    public void setItemPembelajarans(List<ItemPembelajaran> itemPembelajarans) {
        this.itemPembelajarans = itemPembelajarans;
    }

    public String getNamaGuru() {
        return namaGuru;
    }

    public void setNamaGuru(String namaGuru) {
        this.namaGuru = namaGuru;
    }

    public int getIdGuru() {
        return idGuru;
    }

    public void setIdGuru(int idGuru) {
        this.idGuru = idGuru;
    }
}