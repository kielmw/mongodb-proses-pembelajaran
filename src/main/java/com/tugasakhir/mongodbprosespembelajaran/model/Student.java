package com.tugasakhir.mongodbprosespembelajaran.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
    @Id
    private Long id;
    private String nim;
    private String nama;
    private int kelasId;

    public  Student(){

    }
    public Student(Long id, String nim, String nama, int kelasId) {
        this.id = id;
        this.nim = nim;
        this.nama = nama;
        this.kelasId = kelasId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getKelasId() {
        return kelasId;
    }

    public void setKelasId(int kelasId) {
        this.kelasId = kelasId;
    }
}
