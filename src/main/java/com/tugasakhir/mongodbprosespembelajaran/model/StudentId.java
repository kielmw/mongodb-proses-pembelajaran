package com.tugasakhir.mongodbprosespembelajaran.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity// Annotation to indicate that this is an embeddable class
public class StudentId implements Serializable {

    private int nim;

    private int idKelas;

    public StudentId() {
        // Default constructor
    }

    public StudentId(int nim, int idKelas) {
        this.nim = nim;
        this.idKelas = idKelas;
    }

    public int getNim() {
        return nim;
    }

    public void setNim(int nim) {
        this.nim = nim;
    }

    public int getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(int idKelas) {
        this.idKelas = idKelas;
    }

    // Equals and hashCode methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentId studentId = (StudentId) o;
        return nim == studentId.nim && idKelas == studentId.idKelas;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nim, idKelas);
    }
}

