package com.tugasakhir.mongodbprosespembelajaran.controller;

import com.tugasakhir.mongodbprosespembelajaran.model.ItemPembelajaran;
import com.tugasakhir.mongodbprosespembelajaran.service.ItemPembelajaranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proses/itemPembelajaran")
public class ItemPembelajaranController {

    private final ItemPembelajaranService itemPembelajaranService;

    @Autowired
    public ItemPembelajaranController(ItemPembelajaranService itemPembelajaranService) {
        this.itemPembelajaranService = itemPembelajaranService;
    }

    @PostMapping("/add/{idKelas}")
    public ResponseEntity<ItemPembelajaran> addItemPembelajaran(@PathVariable String idKelas , @RequestBody ItemPembelajaran itemPembelajaran) {
        ItemPembelajaran newItemPembelajaran = itemPembelajaranService.addItemPembelajaran(itemPembelajaran, idKelas);
        return new ResponseEntity<>(newItemPembelajaran, HttpStatus.CREATED);
    }
    @PutMapping("/update/{idKelas}/{idPertemuan}")
    public ResponseEntity<ItemPembelajaran> updateItemPembelajaran(@PathVariable String idKelas, @PathVariable String idPertemuan, @RequestBody ItemPembelajaran updatedItem) {
        ItemPembelajaran updatedPembelajaran = itemPembelajaranService.updateItemPembelajaran(idKelas, idPertemuan, updatedItem);
        return new ResponseEntity<>(updatedPembelajaran, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idKelas}/{idPertemuan}")
    public ResponseEntity<Void> deleteItemPembelajaran(@PathVariable String idKelas, @PathVariable String idPertemuan) {
        itemPembelajaranService.deleteItemPembelajaran(idKelas, idPertemuan);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
