package com.tugasakhir.mongodbprosespembelajaran.model;

import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Document(collection = "member")
public class Member {
    private String id;
    private int nim;
    private List<Integer> idKelas; // Changed type to List<Integer>

    public Member(String id, int nim, List<Integer> idKelas) {
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

    public List<Integer> getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(List<Integer> idKelas) {
        this.idKelas = idKelas;
    }
}
