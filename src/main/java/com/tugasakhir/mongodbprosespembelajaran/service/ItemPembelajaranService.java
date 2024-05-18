package com.tugasakhir.mongodbprosespembelajaran.service;

import com.tugasakhir.mongodbprosespembelajaran.model.ItemPembelajaran;
import com.tugasakhir.mongodbprosespembelajaran.model.Pdf;
import com.tugasakhir.mongodbprosespembelajaran.model.Proses;
import com.tugasakhir.mongodbprosespembelajaran.repository.prosesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemPembelajaranService {

    private final prosesRepository prosesRepository;
    private final PdfService pdfService;

    @Autowired
    public ItemPembelajaranService(prosesRepository prosesRepository, PdfService pdfService) {
        this.prosesRepository = prosesRepository;
        this.pdfService = pdfService;
    }

    public List<String> getAllFileNameOptions() {
        // Retrieve all unique idPdf values from your database
        List<String> fileNameOptions = prosesRepository.findAllFileNameOptions();
        return fileNameOptions;
    }

    public List<ItemPembelajaran> getItemPembelajaran(String idKelas) {
        // Retrieve the Proses entity by idKelas to ensure its existence
        Proses proses = prosesRepository.findByIdKelas(idKelas)
                .orElseThrow(() -> new IllegalArgumentException("Proses with idKelas " + idKelas + " not found"));

        // Return all items of ItemPembelajaran associated with the Proses
        return proses.getItemPembelajarans();
    }

    public ItemPembelajaran updateItemPembelajaran(String idKelas, String idPertemuan, ItemPembelajaran updatedItem) {
        // Retrieve the Proses entity by idKelas to ensure its existence
        Proses proses = prosesRepository.findByIdKelas(idKelas)
                .orElseThrow(() -> new IllegalArgumentException("Proses with idKelas " + idKelas + " not found"));

        // Retrieve the ItemPembelajaran to be updated
        ItemPembelajaran existingItem = proses.getItemPembelajarans().stream()
                .filter(item -> item.getIdPertemuan().equals(idPertemuan))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("ItemPembelajaran with idPertemuan " + idPertemuan + " not found"));

        // Update the fields of the existing ItemPembelajaran with the provided values
        existingItem.setHeadingPertemuan(updatedItem.getHeadingPertemuan());
        existingItem.setBodyPertemuan(updatedItem.getBodyPertemuan());
        existingItem.setVideoPertemuan(updatedItem.getVideoPertemuan());

        // If necessary, update the idPdf and fileName based on Pdf model data
        if (updatedItem.getIdPdf() != null) {
            String idPdf = updatedItem.getIdPdf();
            String fileName = pdfService.getFileName(idKelas, idPdf);
            existingItem.setIdPdf(idPdf);
            existingItem.setFileName(fileName);
        }

        // Save the updated Proses entity
        prosesRepository.save(proses);

        return existingItem;
    }

    public void deleteItemPembelajaran(String idKelas, String idPertemuan) {
        // Retrieve the Proses entity by idKelas to ensure its existence
        Proses proses = prosesRepository.findByIdKelas(idKelas)
                .orElseThrow(() -> new IllegalArgumentException("Proses with idKelas " + idKelas + " not found"));

        // Remove the ItemPembelajaran with the given idPertemuan from the list
        proses.getItemPembelajarans().removeIf(item -> item.getIdPertemuan().equals(idPertemuan));

        // Save the updated Proses entity
        prosesRepository.save(proses);
    }
    public ItemPembelajaran addItemPembelajaran(ItemPembelajaran itemPembelajaran, String idKelas) {
        // Retrieve the Proses entity by idPembelajaran to get idKelas
        String idPertemuan = itemPembelajaran.getIdPertemuan(); // Corrected variable name
        itemPembelajaran.setIdPertemuan(idPertemuan);
        Proses proses = prosesRepository.findByIdKelas(idKelas)
                .orElseThrow(() -> new IllegalArgumentException("Proses with idKelas " + idKelas + " not found"));

        // Set idKelas to ItemPembelajaran
        itemPembelajaran.setIdKelas(idKelas);

        String headingPertemuan = itemPembelajaran.getHeadingPertemuan(); // Corrected variable name
        itemPembelajaran.setHeadingPertemuan(headingPertemuan);

        String bodyPertemuan = itemPembelajaran.getBodyPertemuan(); // Corrected variable name
        itemPembelajaran.setBodyPertemuan(bodyPertemuan);

        String videoPertemuan = itemPembelajaran.getVideoPertemuan(); // Corrected variable name
        itemPembelajaran.setVideoPertemuan(videoPertemuan);

        // Set idPdf and fileName based on Pdf model data
        String idPdf = itemPembelajaran.getIdPdf(); // Assuming you have a getter for idPdf
        String fileName = pdfService.getFileName(idKelas, idPdf); // Call method on the autowired PdfService
        itemPembelajaran.setFileName(fileName);

        // Get the existing list of ItemPembelajarans from the Proses entity
        List<ItemPembelajaran> itemPembelajarans = proses.getItemPembelajarans();
        if (itemPembelajarans == null) {
            itemPembelajarans = new ArrayList<>();
        }

        // Append the new ItemPembelaaran to the list
        itemPembelajarans.add(itemPembelajaran);

        proses.setIdKelas(idKelas);
        proses.setItemPembelajarans(itemPembelajarans);

        prosesRepository.save(proses);

        return itemPembelajaran;
    }

}