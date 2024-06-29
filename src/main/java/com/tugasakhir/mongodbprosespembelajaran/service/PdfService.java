package com.tugasakhir.mongodbprosespembelajaran.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.tugasakhir.mongodbprosespembelajaran.model.Proses;
import com.tugasakhir.mongodbprosespembelajaran.model.Pdf;
import com.tugasakhir.mongodbprosespembelajaran.repository.prosesRepository;
import com.tugasakhir.mongodbprosespembelajaran.service.prosesService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PdfService {

    @Autowired
    private prosesRepository prosesRepository;

    public String savePdf(MultipartFile file, String idKelas) throws IOException {
        String fileName = Objects.requireNonNull(file.getOriginalFilename());
        try {
            // Convert MultipartFile to bytes
            byte[] pdfBytes = file.getBytes();

            // Create a new PDF object with sequential ID
            Pdf pdf = new Pdf();
            pdf.setIdPdf(getNextPdfId(idKelas)); // Set sequential ID
            pdf.setFileName(fileName);
            pdf.setPdfBytes(pdfBytes);

            // Get the existing Proses entity by ID
            Proses proses = prosesRepository.findByIdKelas(idKelas)
                    .orElseThrow(() -> new IllegalArgumentException("Proses with ID " + idKelas + " not found"));

            // Get the existing list of PDFs from the Proses entity
            List<Pdf> pdfs = proses.getPdfs();
            if (pdfs == null) {
                pdfs = new ArrayList<>();
            }

            // Append the new PDF to the list
            pdfs.add(pdf);

            // Set the updated list of PDFs back to the Proses entity
            proses.setIdKelas(idKelas);
            proses.setPdfs(pdfs);

            // Save the updated Proses entity
            prosesRepository.save(proses);

            return "File saved successfully for class ID: " + idKelas;
        } catch (IOException e) {
            throw new IOException("Failed to save File: " + e.getMessage());
        }
    }

    // Method to get the next sequential PDF ID
    private String getNextPdfId(String idKelas) {
        // Retrieve the Proses entity by ID
        Proses proses = prosesRepository.findByIdKelas(idKelas)
                .orElseThrow(() -> new IllegalArgumentException("Proses with ID " + idKelas + " not found"));

        // Get the existing list of PDFs from the Proses entity
        List<Pdf> pdfs = proses.getPdfs();
        if (pdfs == null) {
            // If no PDFs exist yet, start with index 0
            return "0";
        } else {
            // Otherwise, increment the ID based on the number of existing PDFs
            return String.valueOf(pdfs.size());
        }
    }

    public Optional<byte[]> getPdfById(String idKelas, String pdfId) {
        try {
            // Retrieve the Proses entity by ID
            Proses proses = prosesRepository.findByIdKelas(idKelas)
                    .orElseThrow(() -> new IllegalArgumentException("Proses with ID " + idKelas + " not found"));

            // Retrieve the list of PDFs from the Proses entity
            List<Pdf> pdfs = proses.getPdfs();
            if (pdfs != null) {
                // Iterate through the list of PDFs to find the PDF by ID
                for (Pdf pdf : pdfs) {
                    if (pdf.getIdPdf().equals(pdfId)) {
                        // Return the PDF content as byte array
                        return Optional.of(pdf.getPdfBytes());
                    }
                }
            }
            // If PDF not found, return empty optional
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to get PDF: " + e.getMessage());
        }
    }
    public String getFileName(String idKelas, String pdfId) {
        // Retrieve the Proses entity by ID
        Proses proses = prosesRepository.findById(idKelas)
                .orElseThrow(() -> new IllegalArgumentException("Proses with ID " + idKelas + " not found"));

        // Get the list of PDFs from the Proses entity
        List<Pdf> pdfs = proses.getPdfs();
        if (pdfs != null && !pdfs.isEmpty()) {
            // Iterate through the list of PDFs to find the PDF with the matching ID
            for (Pdf pdf : pdfs) {
                if (pdf.getIdPdf().equals(pdfId)) {
                    // Return the filename of the matching PDF
                    return pdf.getFileName();
                }
            }
        }

        // If PDF not found, throw exception or return default filename
        throw new IllegalArgumentException("PDF with ID " + pdfId + " not found");
    }
    public void deletePdfById(String idKelas, String pdfId) {
        try {
            // Retrieve the Proses entity by ID
            Proses proses = prosesRepository.findByIdKelas(idKelas)
                    .orElseThrow(() -> new IllegalArgumentException("Proses with ID " + idKelas + " not found"));

            // Retrieve the list of PDFs from the Proses entity
            List<Pdf> pdfs = proses.getPdfs();
            if (pdfs != null && !pdfs.isEmpty()) {
                // Iterate through the list of PDFs to find the PDF by ID
                for (Pdf pdf : pdfs) {
                    if (pdf.getIdPdf().equals(pdfId)) {
                        // Remove the PDF from the list
                        pdfs.remove(pdf);

                        // Set the updated list of PDFs back to the Proses entity
                        proses.setPdfs(pdfs);

                        // Save the updated Proses entity
                        prosesRepository.save(proses);

                        // PDF deleted successfully
                        return;
                    }
                }
            }
            // If PDF not found, throw an exception
            throw new IllegalArgumentException("PDF with ID " + pdfId + " not found");
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to delete PDF: " + e.getMessage());
        }
    }
    public void deleteAllPdfByProsesId(String idKelas) {
        try {
            // Retrieve the Proses entity by ID
            Proses proses = prosesRepository.findByIdKelas(idKelas)
                    .orElseThrow(() -> new IllegalArgumentException("Proses with ID " + idKelas + " not found"));

            // Retrieve the list of PDFs from the Proses entity
            List<Pdf> pdfs = proses.getPdfs();
            if (pdfs != null && !pdfs.isEmpty()) {
                // Iterate through the list of PDFs to delete each one
                for (Pdf pdf : pdfs) {
                    // Call the deletePdfById method to delete each PDF
                    deletePdfById(idKelas, pdf.getIdPdf());
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to delete PDFs associated with Proses ID " + idKelas + ": " + e.getMessage());
        }
    }

}