package com.tugasakhir.mongodbprosespembelajaran.controller;

import com.tugasakhir.mongodbprosespembelajaran.service.prosesService;
import com.tugasakhir.mongodbprosespembelajaran.model.Pdf;
import com.tugasakhir.mongodbprosespembelajaran.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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
            return ResponseEntity.ok().body("File saved successfully for class ID: " + idKelas + " with File ID: " + response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save File: " + e.getMessage());
        }
    }
    @GetMapping("/download/{idKelas}/{pdfId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String idKelas, @PathVariable String pdfId) {
        try {
            Optional<byte[]> fileOptional = pdfService.getPdfById(idKelas, pdfId); // Assuming pdfService.getFileById() returns the file bytes
            if (fileOptional.isPresent()) {
                byte[] fileBytes = fileOptional.get();
                ByteArrayResource resource = new ByteArrayResource(fileBytes);

                HttpHeaders headers = new HttpHeaders();

                // Determine the file extension from pdfId or use pdfService.getFileExtension(idKelas, pdfId) if available
                String fileExtension = pdfId.substring(pdfId.lastIndexOf('.') + 1);

                // Set the Content-Type header based on file extension
                String contentType = getContentType(fileExtension);
                headers.setContentType(MediaType.parseMediaType(contentType));

                // Get the filename using PdfService or use pdfId as filename
                String fileName = pdfService.getFileName(idKelas, pdfId);

                // Set the Content-Disposition header to include the filename
                headers.setContentDispositionFormData("attachment", fileName);

                return ResponseEntity.ok()
                        .headers(headers)
                        .contentLength(fileBytes.length)
                        .body(resource);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to download file: " + e.getMessage());
        }
    }

    private String getContentType(String fileExtension) {
        switch (fileExtension.toLowerCase()) {
            case "pdf":
                return "application/pdf";
            case "doc":
            case "docx":
                return "application/msword";
            case "xls":
            case "xlsx":
                return "application/vnd.ms-excel";
            case "ppt":
            case "pptx":
                return "application/vnd.ms-powerpoint";
            case "png":
                return "image/png";
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "gif":
                return "image/gif";
            default:
                return "application/octet-stream"; // Default to binary data if content type is unknown
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
