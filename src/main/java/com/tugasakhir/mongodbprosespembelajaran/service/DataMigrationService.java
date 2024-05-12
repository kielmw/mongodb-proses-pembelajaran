package com.tugasakhir.mongodbprosespembelajaran.service;

import com.tugasakhir.mongodbprosespembelajaran.model.Member;
import com.tugasakhir.mongodbprosespembelajaran.model.Student;
import com.tugasakhir.mongodbprosespembelajaran.repository.MemberRepository;
import com.tugasakhir.mongodbprosespembelajaran.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import oracle.ucp.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class DataMigrationService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MemberRepository memberRepository;

    @PostConstruct
    @Scheduled(fixedDelay = 5000)
    public void migrateData() {
        // Clear existing data in the Member repository
        memberRepository.deleteAll();

        // Retrieve students from the Student repository
        List<Student> students = studentRepository.findAll();
        List<Member> members = new ArrayList<>();

        // Convert Student objects to Member objects
        for (Student student : students) {
            Member member = new Member();
            member.setNim(student.getNim());
            member.setNama(student.getNama());
            member.setIdKelas(String.valueOf(student.getKelasId())); // Convert int to String
            members.add(member);
        }

        // Save the Member objects to the Member repository
        memberRepository.saveAll(members);
    }

}
