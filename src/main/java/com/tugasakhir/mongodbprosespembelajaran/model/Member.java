package com.tugasakhir.mongodbprosespembelajaran.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@Document("members")
public class Member {
    private Long id;

    private String nim;

    private String nama;

    private String idKelas;

    public Member(Long id, String nim, String nama, String idKelas) {
        this.id = id;
        this.nim = nim;
        this.nama = nama;
        this.idKelas = idKelas;
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

    public String getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(String idKelas) {
        this.idKelas = idKelas;
    }
}
