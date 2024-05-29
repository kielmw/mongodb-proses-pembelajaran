package com.tugasakhir.mongodbprosespembelajaran.service;

import com.tugasakhir.mongodbprosespembelajaran.model.*;
import com.tugasakhir.mongodbprosespembelajaran.repository.KelasRepository;
import com.tugasakhir.mongodbprosespembelajaran.repository.MemberRepository;
import com.tugasakhir.mongodbprosespembelajaran.repository.ProsesUpdaterRepository;
import com.tugasakhir.mongodbprosespembelajaran.repository.StudentRepository;
import com.tugasakhir.mongodbprosespembelajaran.repository.prosesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DataMigrationService {

    private static final Logger logger = LoggerFactory.getLogger(DataMigrationService.class);

    private static final int BATCH_SIZE = 100; // Adjust batch size as needed

    private final StudentRepository studentRepository;
    private final MemberRepository memberRepository;
    private final KelasRepository kelasRepository;
    private final ProsesUpdaterRepository prosesUpdaterRepository;
    private final prosesRepository prosesRepository;

    public DataMigrationService(StudentRepository studentRepository, MemberRepository memberRepository,
                                KelasRepository kelasRepository, ProsesUpdaterRepository prosesUpdaterRepository,
                                prosesRepository prosesRepository) {
        this.studentRepository = studentRepository;
        this.memberRepository = memberRepository;
        this.kelasRepository = kelasRepository;
        this.prosesUpdaterRepository = prosesUpdaterRepository;
        this.prosesRepository = prosesRepository;
    }

    @PostConstruct
    @Scheduled(fixedDelay = 100000)
    public void migrateData() {
        try {
            // Clear existing data in the Member repository
            memberRepository.deleteAll();
            logger.info("Existing data in Member repository cleared.");

            // Retrieve students from the Student repository
            List<Student> students = studentRepository.findAll();
            logger.info("Retrieved {} students from Student repository.", students.size());

            if (students.isEmpty()) {
                logger.warn("No students found in the repository.");
                return;
            }

            Map<Integer, List<Integer>> nimToIdKelasMap = new HashMap<>();

            // Aggregate idKelas for each nim
            for (Student student : students) {
                int nim = student.getNim();
                int idKelas = student.getIdKelas();
                nimToIdKelasMap.computeIfAbsent(nim, k -> new ArrayList<>()).add(idKelas);
            }

            List<Member> members = new ArrayList<>();

            // Convert nimToIdKelasMap entries to Member objects
            for (Map.Entry<Integer, List<Integer>> entry : nimToIdKelasMap.entrySet()) {
                int nim = entry.getKey();
                List<Integer> idKelasList = entry.getValue();

                Member member = new Member();
                member.setNim(nim);
                member.setIdKelas(idKelasList);
                members.add(member);
            }

            // Save members to MongoDB
            memberRepository.saveAll(members);
            logger.info("Saved {} members to Member repository.", members.size());

        } catch (Exception e) {
            logger.error("Error during data migration", e);
        }
    }

    @Scheduled(fixedDelay = 120000)
    public void migrateKelasData() {
        try {
            // Clear existing data in the ProsesUpdater repository
            prosesUpdaterRepository.deleteAll();
            logger.info("Existing data in ProsesUpdater repository cleared.");

            // Retrieve all Kelas from the Kelas repository
            List<Kelas> kelasList = kelasRepository.findAll();
            logger.info("Retrieved {} Kelas from Kelas repository.", kelasList.size());

            if (kelasList.isEmpty()) {
                logger.warn("No Kelas found in the repository.");
                return;
            }

            List<ProsesUpdater> prosesUpdaters = new ArrayList<>();

            // Convert Kelas objects to ProsesUpdater objects
            for (Kelas kelas : kelasList) {
                ProsesUpdater prosesUpdater = new ProsesUpdater(
                        kelas.getIdKelas(),
                        kelas.getNamaKelas(),
                        kelas.getDeskripsiKelas(),
                        kelas.getIdGuru(),
                        kelas.getNamaGuru()
                );
                prosesUpdaters.add(prosesUpdater);
            }

            // Save ProsesUpdater objects to MongoDB
            prosesUpdaterRepository.saveAll(prosesUpdaters);
            logger.info("Saved {} ProsesUpdater records to ProsesUpdater repository.", prosesUpdaters.size());

        } catch (Exception e) {
            logger.error("Error during Kelas data migration", e);
        }
    }

    @Scheduled(fixedRate = 60000)
    public void updateProsesFromProsesUpdater() {
        try {
            // Retrieve all ProsesUpdater records
            List<ProsesUpdater> prosesUpdaters = prosesUpdaterRepository.findAll();
            logger.info("Retrieved {} ProsesUpdater records.", prosesUpdaters.size());

            // Retrieve all existing Proses
            List<Proses> existingProsesList = prosesRepository.findAll();

            // Create a set to store ids from ProsesUpdater
            Set<String> prosesUpdaterIds = prosesUpdaters.stream()
                    .map(ProsesUpdater::getIdKelas)
                    .map(String::valueOf)
                    .collect(Collectors.toSet());

            // Iterate through existing Proses
            for (Proses existingProses : existingProsesList) {
                String existingIdKelas = existingProses.getIdKelas();

                // Check if the idKelas of existing Proses exists in ProsesUpdater
                if (!prosesUpdaterIds.contains(existingIdKelas)) {
                    // Delete Proses that don't have a corresponding idKelas in ProsesUpdater
                    prosesRepository.delete(existingProses);
                    logger.info("Deleted Proses with idKelas: {}", existingIdKelas);
                }
            }

            // Iterate through ProsesUpdater records
            for (ProsesUpdater updater : prosesUpdaters) {
                // Convert idKelas from int to String
                String idKelasString = String.valueOf(updater.getIdKelas());

                // Find corresponding Proses by idKelas
                Optional<Proses> optionalProses = prosesRepository.findById(idKelasString);
                if (optionalProses.isPresent()) {
                    Proses proses = optionalProses.get();
                    // Update Proses fields
                    proses.setNamaKelas(updater.getNamaKelas());
                    proses.setDeskripsiKelas(updater.getDeskripsiKelas());
                    proses.setIdGuru(updater.getIdGuru());
                    proses.setNamaGuru(updater.getNamaGuru());

                    // Save updated Proses back to the repository
                    prosesRepository.save(proses);
                    logger.info("Updated Proses with idKelas: {}", updater.getIdKelas());
                } else {
                    // If Proses does not exist, create a new one
                    Proses newProses = new Proses();
                    newProses.setIdKelas(idKelasString);
                    newProses.setNamaKelas(updater.getNamaKelas());
                    newProses.setDeskripsiKelas(updater.getDeskripsiKelas());
                    newProses.setIdGuru(updater.getIdGuru());
                    newProses.setNamaGuru(updater.getNamaGuru());
                    newProses.setPdfs(new ArrayList<>()); // Initialize empty lists
                    newProses.setItemPembelajarans(new ArrayList<>());

                    // Save the new Proses to the repository
                    prosesRepository.save(newProses);
                    logger.info("Created new Proses with idKelas: {}", updater.getIdKelas());
                }
            }
        } catch (Exception e) {
            logger.error("Error updating Proses from ProsesUpdater", e);
        }
    }
}
