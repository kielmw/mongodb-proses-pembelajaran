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
@Document("proses/pdf")

public class Pdf {
    @Id
    @Indexed(unique = true)
    private String idPdf;
    @Indexed(unique = true)
    private String fileName;
    @Indexed(unique = true)
    private byte[] pdfBytes;

    public Pdf(String idPdf, String fileName, byte[] pdfBytes) {
        this.idPdf = idPdf;
        this.fileName = fileName;
        this.pdfBytes = pdfBytes;
    }

    public String getIdPdf() {
        return idPdf;
    }

    public void setIdPdf(String idPdf) {
        this.idPdf = idPdf;
    }

    public  String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getPdfBytes() {
        return pdfBytes;
    }

    public void setPdfBytes(byte[] pdfBytes) {
        this.pdfBytes = pdfBytes;
    }
}

