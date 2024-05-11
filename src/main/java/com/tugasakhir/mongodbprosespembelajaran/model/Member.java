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

    private String kelas_id;

    public Member(Long id, String nim, String nama, String kelas_id) {
        this.id = id;
        this.nim = nim;
        this.nama = nama;
        this.kelas_id = kelas_id;
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

    public String getKelas_id() {
        return kelas_id;
    }

    public void setKelas_id(String kelas_id) {
        this.kelas_id = kelas_id;
    }
}
