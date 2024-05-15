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
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
    @Indexed(unique = true)
    private String idKelas;
    @Indexed(unique = true)
    @Field(name = "namaKelas ")
    private String namaKelas;
    private List<Member> members;
    private List<Pdf> pdfs;
    private List<ItemPembelajaran> itemPembelajarans;

    public Proses(String idKelas, String namaKelas, List<Member> members, List<Pdf> pdfs, List<ItemPembelajaran> itemPembelajarans) {
        this.idKelas = idKelas;
        this.namaKelas = namaKelas;
        this.members = members;
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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
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

    //    public void addMember(Member member) {
//        if (member.getIdKelas().equals(this.idKelas)) {
//            this.members.add(member);
//        } else {
//            // Handle the case where the member's idKelas does not match this Proses' idKelas
//        }
//    }
}