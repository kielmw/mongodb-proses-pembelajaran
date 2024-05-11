package com.tugasakhir.mongodbprosespembelajaran.service;

import com.tugasakhir.mongodbprosespembelajaran.model.Member;
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
    private final PdfService pdfService;

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

    public void deleteProses(String idKelas) {
        try {
            // Retrieve the Proses entity by ID
            Proses proses = ProsesRepository.findByIdKelas(idKelas)
                    .orElseThrow(() -> new RuntimeException("Proses with ID " + idKelas + " not found"));

            // Delete any associated PDFs first using an instance of PdfService
            pdfService.deleteAllPdfByProsesId(idKelas);

            // After deleting associated PDFs, delete the Proses entity
            ProsesRepository.deleteById(idKelas);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete Proses: " + e.getMessage());
        }
    }

//    public List<Member> getMembersByKelasId(String idKelas) {
//        // Implementasi untuk mendapatkan members berdasarkan idKelas
//        // ...
//    }
}
