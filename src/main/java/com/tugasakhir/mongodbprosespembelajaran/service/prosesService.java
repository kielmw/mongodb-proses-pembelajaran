package com.tugasakhir.mongodbprosespembelajaran.service;

import com.tugasakhir.mongodbprosespembelajaran.model.Pdf;
import com.tugasakhir.mongodbprosespembelajaran.model.Proses;
import com.tugasakhir.mongodbprosespembelajaran.repository.prosesRepository;
import com.tugasakhir.mongodbprosespembelajaran.model.Member;
import com.tugasakhir.mongodbprosespembelajaran.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class prosesService {

    private final prosesRepository prosesRepository;
    private final PdfService pdfService;
    private final MemberRepository memberRepository;

    public void addProses(Proses proses) {
        prosesRepository.save(proses);
    }

    public void updateProses(Proses proses) {
        Proses savedProses = prosesRepository.findByIdKelas(proses.getIdKelas())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot find Proses with ID %s", proses.getIdKelas())));

        savedProses.setIdKelas(proses.getIdKelas());

        prosesRepository.save(savedProses);
    }

    public Proses getProses(String id) {
        return prosesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Cannot find Proses by ID - %s", id)));
    }

    public List<Proses> getAllProses() {
        return prosesRepository.findAll();
    }

    public void deleteProses(String id) {
        try {
            // Retrieve the Proses entity by ID
            Proses proses = prosesRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Proses with ID " + id + " not found"));

            // Delete any associated PDFs first using an instance of PdfService
            pdfService.deleteAllPdfByProsesId(id);

            // After deleting associated PDFs, delete the Proses entity
            prosesRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete Proses: " + e.getMessage());
        }
    }

    public List<Proses> getProsesByNim(int nim) {
        // Retrieve Member by nim to get idKelas
        Optional<Member> memberOptional = memberRepository.findByNim(nim);

        if (!memberOptional.isPresent()) {
            throw new RuntimeException("Member with nim " + nim + " not found");
        }

        Member member = memberOptional.get();
        List<Integer> idKelasList = member.getIdKelas();

        // Convert idKelasList to list of strings
        List<String> idKelasStringList = new ArrayList<>();
        for (Integer idKelas : idKelasList) {
            idKelasStringList.add(String.valueOf(idKelas));
        }

        // Retrieve Proses for each idKelas in the idKelasList
        List<Proses> prosesList = prosesRepository.findByIdKelasList(idKelasStringList);

        if (prosesList.isEmpty()) {
            throw new RuntimeException("No Proses found for nim " + nim);
        }

        return prosesList;
    }
    public List<Proses> getProsesByIdGuru(int idGuru) {
        return prosesRepository.findByidGuru(idGuru);
    }
    public List<Pdf> getPdfsByIdKelas(String idKelas) {
        Proses proses = prosesRepository.findByIdKelas(idKelas)
                .orElseThrow(() -> new RuntimeException(String.format("Cannot find Proses with ID %s", idKelas)));
        return proses.getPdfs();
    }

}
