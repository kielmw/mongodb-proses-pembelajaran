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
@Document("proses")
public class Proses {
    @Id
    @Indexed(unique = true)
    private String idKelas;
    @Indexed(unique = true)
    @Field(name = "namaKelas ")
    private String namaKelas;
    @Field(name = "videoKelas ")
    private String videoKelas;
    private String fileName;
    private byte[] pdfBytes;

    public Proses(String idKelas, String namaKelas, String videoKelas, String fileName, byte[] pdfBytes) {
        this.idKelas = idKelas;
        this.namaKelas = namaKelas;
        this.videoKelas = videoKelas;
        this.fileName = fileName;
        this.pdfBytes = pdfBytes;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setPdfBytes(byte[] pdfBytes) {
        this.pdfBytes = pdfBytes;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getPdfBytes() {
        return pdfBytes;
    }

    public String getIdKelas() {return idKelas;}

    public String getNamaKelas() {
        return namaKelas;
    }

    public String getVideoKelas() {
        return videoKelas;
    }

    public void setIdKelas(String idKelas) {
        this.idKelas = idKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public void setVideoKelas(String videoKelas) {
        this.videoKelas = videoKelas;
    }


}