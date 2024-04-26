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

    public ItemPembelajaran addItemPembelajaran(ItemPembelajaran itemPembelajaran, String idKelas) {
        // Retrieve the Proses entity by idKelas
        Proses proses = prosesRepository.findById(idKelas)
                .orElseThrow(() -> new IllegalArgumentException("Proses with idKelas " + idKelas + " not found"));

        // Set idKelas to ItemPembelajaran
        itemPembelajaran.setIdKelas(idKelas);

        // Get all available file names
        List<String> fileNameOptions = prosesRepository.findAllFileNameOptions();
        if (fileNameOptions.isEmpty()) {
            throw new IllegalStateException("No fileName options found.");
        }

        // Assuming you want to select the first fileName option
        String fileName = fileNameOptions.get(0); // Selecting the first option here, you might want to implement your own logic to select the appropriate fileName
        itemPembelajaran.setFileName(fileName);

        // Set idPdf based on fileName
        String idPdf = fileName; // Assuming idPdf follows the fileName
        itemPembelajaran.setIdPdf(idPdf);

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
