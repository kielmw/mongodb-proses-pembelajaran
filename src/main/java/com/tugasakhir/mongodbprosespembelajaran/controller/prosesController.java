package com.tugasakhir.mongodbprosespembelajaran.controller;

import com.tugasakhir.mongodbprosespembelajaran.model.Proses;
import com.tugasakhir.mongodbprosespembelajaran.repository.prosesRepository;
import lombok.RequiredArgsConstructor;
import com.tugasakhir.mongodbprosespembelajaran.service.prosesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


import java.util.List;

@RestController
@RequestMapping("/api/proses")
@RequiredArgsConstructor
public class prosesController {

    private final prosesService ProsesService;

    @PostMapping
    public ResponseEntity addProses(@RequestBody Proses proses){
        ProsesService.addProses(proses);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping
    public ResponseEntity updateProses(@RequestBody Proses proses){
        ProsesService.updateProses(proses);
        return ResponseEntity.status(HttpStatus.OK).build();

    }
    @GetMapping
    public ResponseEntity<List<Proses>> getAllProses(){
        return ResponseEntity.ok(ProsesService.getAllProses());
    }
    @GetMapping("/{idKelas}")
    public ResponseEntity getProsesIdKelas(@PathVariable String idKelas){
        return ResponseEntity.ok(ProsesService.getProses(idKelas));
    }
    @DeleteMapping("/delete/{idKelas}")
    public ResponseEntity deleteProses(@PathVariable String idKelas) {
        ProsesService.deleteProses(idKelas);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PostMapping("/upload/{idKelas}")
    public ResponseEntity<String> savePdf(@PathVariable String idKelas ,@RequestParam("file") MultipartFile file , Proses proses) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a PDF file.");
        }
        try {
            String result = ProsesService.savePdf(file,idKelas,proses);
            return ResponseEntity.ok().body(result);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload PDF: " + e.getMessage());
        }
    }
}
