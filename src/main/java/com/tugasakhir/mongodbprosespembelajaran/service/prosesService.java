package com.tugasakhir.mongodbprosespembelajaran.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.tugasakhir.mongodbprosespembelajaran.model.Proses;
import com.tugasakhir.mongodbprosespembelajaran.repository.prosesRepository;

import java.util.List;

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

}
