package com.tugasakhir.mongodbprosespembelajaran.model;

import jakarta.persistence.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.io.Serializable;

@Entity
@Table(name = "kontrol_kelas_student")
@IdClass(StudentId.class) // Define the primary key class
public class Student implements Serializable {

    @Id
    @Column(name = "student", nullable = false)
    private int nim;

    @Id
    @Column(name = "kontrolKelas", nullable = false)
    private int idKelas;

    public Student(){

    }
    public Student(int nim, int idKelas) {
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

    public void setIdKelas(int IdKelas) {
        this.idKelas = idKelas;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Student student = (Student) o;
//
//        if (nim != student.nim) return false;
//        return kelas != null ? kelas.equals(student.kelas) : student.kelas == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = nim;
//        result = 31 * result + (kelas != null ? kelas.hashCode() : 0);
//        return result;
//    }
    // Other fields, constructors, getters, and setters
}
