package com.tugasakhir.mongodbprosespembelajaran.controller;

import com.tugasakhir.mongodbprosespembelajaran.service.prosesService;
import com.tugasakhir.mongodbprosespembelajaran.model.Pdf;
import com.tugasakhir.mongodbprosespembelajaran.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;


@Controller
@RequestMapping("/api/proses/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @PostMapping("/upload/{idKelas}")
    public ResponseEntity<String> uploadPdf(@RequestParam("file") MultipartFile file, @PathVariable String idKelas) {
        try {
            String response = pdfService.savePdf(file, idKelas);
            return ResponseEntity.ok().body("PDF saved successfully for class ID: " + idKelas + " with PDF ID: " + response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save PDF: " + e.getMessage());
        }
    }
    @GetMapping("/download/{idKelas}/{pdfId}")
    public ResponseEntity<ByteArrayResource> downloadPdf(@PathVariable String idKelas, @PathVariable String pdfId ) {
        try {
            Optional<byte[]> pdfOptional = pdfService.getPdfById(idKelas, pdfId);
            if (pdfOptional.isPresent()) {
                byte[] pdfBytes = pdfOptional.get();
                ByteArrayResource resource = new ByteArrayResource(pdfBytes);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);

                // Get the filename using PdfService
                String fileName = pdfService.getFileName(idKelas, pdfId);

                // Set the Content-Disposition header to include the filenames
                headers.setContentDispositionFormData("attachment",  fileName + "");

                return ResponseEntity.ok()
                        .headers(headers)
                        .contentLength(pdfBytes.length)
                        .body(resource);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PDF not found");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to download PDF: " + e.getMessage());
        }
    }
    @DeleteMapping("/delete/{idKelas}/{pdfId}")
    public ResponseEntity<String> deletePdfById(@PathVariable String idKelas, @PathVariable String pdfId) {
        try {
            // Call the deletePdfById method from PdfService to delete the PDF
            pdfService.deletePdfById(idKelas, pdfId);

            return ResponseEntity.ok().body("PDF with ID " + pdfId + " deleted successfully from class ID: " + idKelas);
        } catch (IllegalArgumentException e) {
            // If PDF deletion fails due to invalid ID or other reasons
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            // If an unexpected error occurs during PDF deletion
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete PDF: " + e.getMessage());
        }
    }



}
