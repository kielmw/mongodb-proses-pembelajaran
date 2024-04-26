package com.tugasakhir.mongodbprosespembelajaran.model;


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
import java.util.List;


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
    private List<Pdf> pdfs;
    private List<ItemPembelajaran> itemPembelajarans;

    public Proses(String idKelas, String namaKelas, String videoKelas, List<Pdf> pdfs, List<ItemPembelajaran> itemPembelajarans) {
        this.idKelas = idKelas;
        this.namaKelas = namaKelas;
        this.videoKelas = videoKelas;
        this.pdfs = pdfs;
        this.itemPembelajarans = itemPembelajarans;
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
}