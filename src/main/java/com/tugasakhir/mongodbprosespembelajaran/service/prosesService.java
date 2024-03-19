package com.tugasakhir.mongodbprosespembelajaran.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.tugasakhir.mongodbprosespembelajaran.model.Proses;
import com.tugasakhir.mongodbprosespembelajaran.repository.prosesRepository;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Objects;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class prosesService {

    private final prosesRepository ProsesRepository;
    public void addProses(Proses proses){
        ProsesRepository.insert(proses);
    }
    public void updateProses(Proses proses){
        Proses savedProses = ProsesRepository.findById(proses.getIdKelas())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find PROSES ID %s", proses.getIdKelas())));

        savedProses.setIdKelas(proses.getIdKelas());

        ProsesRepository.save(proses);
    }
    public Proses getProses(String idKelas) {
        return ProsesRepository.findByIdKelas(idKelas)
                .orElseThrow(() -> new RuntimeException(String.format("Cannot Find Expense by ID - %s", idKelas)));
    }
    public List<Proses> getAllProses(){
        return ProsesRepository.findAll();
    }
    public void deleteProses (String idKelas){
        ProsesRepository.deleteById(idKelas);
    }
//    public String savePdf(MultipartFile file, String idKelas , Proses proses) throws IOException {
//        String fileName = Objects.requireNonNull(file.getOriginalFilename());
//        try {
//            // Convert MultipartFile to bytes
//            byte[] pdfBytes = file.getBytes();
//
//            // Save PDF bytes to MongoDB with specified class ID
//            proses.setFileName(fileName);
//            proses.setIdKelas(idKelas);
//            proses.setPdfBytes(pdfBytes);
//            ProsesRepository.save(proses);
//
//            return "PDF saved successfully for class ID: " + idKelas;
//        } catch (IOException e) {
//            throw new IOException("Failed to save PDF: " + e.getMessage());
//        }
//    }
    public Optional<Proses> getPdfById(String idKelas) {
        return ProsesRepository.findByIdKelas(idKelas);
    }
}
