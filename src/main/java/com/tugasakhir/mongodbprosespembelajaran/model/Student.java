package com.tugasakhir.mongodbprosespembelajaran.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "kontrol_kelas_student")
public class Student {
    @Id
    @Column(name = "kontrol_kelas", nullable = false)

    private int kelasId;
    @Column(name = "student", nullable = false)
    private int nim;

    public  Student(){

    }

    public Student(int kelasId, int nim) {
        this.kelasId = kelasId;
        this.nim = nim;
    }

    public int getKelasId() {
        return kelasId;
    }

    public void setKelasId(int kelasId) {
        this.kelasId = kelasId;
    }

    public int getNim() {
        return nim;
    }

    public void setNim(int nim) {
        this.nim = nim;
    }
}
