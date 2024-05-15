package com.tugasakhir.mongodbprosespembelajaran.service;

import com.tugasakhir.mongodbprosespembelajaran.model.Member;
import com.tugasakhir.mongodbprosespembelajaran.model.Student;
import com.tugasakhir.mongodbprosespembelajaran.repository.MemberRepository;
import com.tugasakhir.mongodbprosespembelajaran.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class DataMigrationService {

    private static final Logger logger = LoggerFactory.getLogger(DataMigrationService.class);

    private static final int BATCH_SIZE = 100; // Adjust batch size as needed

    private final StudentRepository studentRepository;
    private final MemberRepository memberRepository;

    public DataMigrationService(StudentRepository studentRepository, MemberRepository memberRepository) {
        this.studentRepository = studentRepository;
        this.memberRepository = memberRepository;
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
}
