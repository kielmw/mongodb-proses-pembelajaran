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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

            List<Member> members = new ArrayList<>();
            Set<String> processedStudents = new HashSet<>(); // To track processed students by composite key
            int count = 0;

            // Log each student to ensure proper data retrieval
            for (Student student : students) {
                String studentKey = student.getNim() + "-" + student.getIdKelas();
                if (processedStudents.contains(studentKey)) {
                    logger.warn("Duplicate student record found: nim={}, kelasId={}", student.getNim(), student.getIdKelas());
                    continue;
                }

                logger.info("Processing student: nim={}, kelasId={}", student.getNim(), student.getIdKelas());
                processedStudents.add(studentKey);

                Member member = new Member();
                member.setNim(student.getNim());
                member.setIdKelas(student.getIdKelas());
                members.add(member);

                if (++count % BATCH_SIZE == 0) {
                    // Save in batches
                    memberRepository.saveAll(members);
                    logger.info("Saved {} members to Member repository in current batch.", members.size());
                    members.clear();
                }
            }

            // Save remaining members
            if (!members.isEmpty()) {
                memberRepository.saveAll(members);
                logger.info("Saved {} members to Member repository in final batch.", members.size());
            }
        } catch (Exception e) {
            logger.error("Error during data migration", e);
        }
    }
}