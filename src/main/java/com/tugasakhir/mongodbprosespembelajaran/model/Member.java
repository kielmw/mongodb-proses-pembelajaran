package com.tugasakhir.mongodbprosespembelajaran.model;

import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Document(collection = "member")
public class Member {
    private String id;

    private int nim;

//    private String nama;
//
//    private int idKelas;
    @ElementCollection
    private int idKelas;

    public Member(String id, int nim, int idKelas) {
        this.id = id;
        this.nim = nim;
        this.idKelas = idKelas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

//    public List<String> getIdKelas() {
//        return idKelas;
//    }
//
//    public void setIdKelas(List<String> idKelas) {
//        this.idKelas = idKelas;
//    }
}
