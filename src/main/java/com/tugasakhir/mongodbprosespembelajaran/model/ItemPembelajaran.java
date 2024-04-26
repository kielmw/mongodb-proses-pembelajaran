package com.tugasakhir.mongodbprosespembelajaran.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@Document("item_pembelajaran")
public class ItemPembelajaran {
    @Id
    @Indexed(unique = true)
    private String idPertemuan;
    private String idKelas;
    @Field(name = "headingPertemuan")
    private String headingPertemuan;
    @Field(name = "bodyPertemuan")
    private String bodyPertemuan;
    private String idPdf;
    private String fileName;
    @Field(name = "videoPertemuan")
    private String videoPertemuan;

    public ItemPembelajaran(String idPertemuan ,String idKelas, String bodyPertemuan, String idPdf, String fileName, String videoPertemuan) {
        this.idPertemuan = idPertemuan;
        this.idKelas = idKelas;
        this.bodyPertemuan = bodyPertemuan;
        this.idPdf = idPdf;
        this.fileName = fileName;
        this.videoPertemuan = videoPertemuan;
    }

    public String getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(String idKelas) {
        this.idKelas = idKelas;
    }

    public String getIdPertemuan() {
        return idPertemuan;
    }

    public void setIdPertemuan(String idPertemuan) {
        this.idPertemuan = idPertemuan;
    }

    public String getBodyPertemuan() {
        return bodyPertemuan;
    }

    public void setBodyPertemuan(String bodyPertemuan) {
        this.bodyPertemuan = bodyPertemuan;
    }

    public String getIdPdf() {
        return idPdf;
    }

    public void setIdPdf(String idPdf) {
        this.idPdf = idPdf;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getVideoPertemuan() {
        return videoPertemuan;
    }

    public void setVideoPertemuan(String videoPertemuan) {
        this.videoPertemuan = videoPertemuan;
    }
}
