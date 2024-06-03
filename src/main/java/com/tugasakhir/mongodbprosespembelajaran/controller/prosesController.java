package com.tugasakhir.mongodbprosespembelajaran.controller;

import com.tugasakhir.mongodbprosespembelajaran.model.Pdf;
import com.tugasakhir.mongodbprosespembelajaran.model.Proses;
import lombok.RequiredArgsConstructor;
import com.tugasakhir.mongodbprosespembelajaran.service.prosesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
@RequestMapping("/api/proses")
@RequiredArgsConstructor
public class prosesController {

    @Autowired
    private final prosesService ProsesService;

//    @PostMapping
//    public ResponseEntity addProses(@RequestBody Proses proses){
//        ProsesService.addProses(proses);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//    @PutMapping
//    public ResponseEntity updateProses(@RequestBody Proses proses){
//        ProsesService.updateProses(proses);
//        return ResponseEntity.status(HttpStatus.OK).build();
//
//    }
//    @GetMapping
//    public ResponseEntity<List<Proses>> getAllProses(){
//
//        return ResponseEntity.ok(ProsesService.getAllProses());
//    }
    @GetMapping("/{idKelas}")
    public ResponseEntity getProsesIdKelas(@PathVariable String idKelas){
        return ResponseEntity.ok(ProsesService.getProses(idKelas));
    }
//    @DeleteMapping("/delete/{idKelas}")
//    public ResponseEntity deleteProses(@PathVariable String idKelas) {
//        ProsesService.deleteProses(idKelas);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }
    @GetMapping("/nim/{nim}")
    public ResponseEntity<List<Proses>> getProsesByNim(@PathVariable int nim) {
        List<Proses> prosesList = ProsesService.getProsesByNim(nim);
        if (prosesList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No proses found for nim " + nim);
        }
        return ResponseEntity.ok(prosesList);
    }
    @GetMapping("/guru/{idGuru}")
    public ResponseEntity<List<Proses>> getProsesByIdGuru(@PathVariable int idGuru) {
        List<Proses> prosesList = ProsesService.getProsesByIdGuru(idGuru);
        if (prosesList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No proses found for idGuru " + idGuru);
        }
        return ResponseEntity.ok(prosesList);
    }
    @GetMapping("/{idKelas}/pdfs")
    public ResponseEntity<List<Pdf>> getPdfsByIdKelas(@PathVariable String idKelas) {
        List<Pdf> pdfList = ProsesService.getPdfsByIdKelas(idKelas);
        return ResponseEntity.ok(pdfList);
    }
}
