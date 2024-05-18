package com.tugasakhir.mongodbprosespembelajaran.model;

import jakarta.persistence.*;

@Entity
@Table(name = "kelas")
public class Kelas {
    @Id
    @Column(name = "id_kelas", nullable = false)
    private int idKelas;
    @Column(name = "nama_Kelas", nullable = false)
    private String namaKelas;
    @Column(name = "deskripsiKelas", nullable = false)
    private String deskripsiKelas;
    @Column(name = "idGuru", nullable = false)
    private int idGuru;
    @Column(name = "namaGuru", nullable = false)
    private String namaGuru;

    public Kelas(){

    }

    public Kelas(int idKelas, String namaKelas, String deskripsiKelas, int idGuru, String namaGuru) {
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
