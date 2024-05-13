package com.tugasakhir.mongodbprosespembelajaran.service;

import com.tugasakhir.mongodbprosespembelajaran.model.Member;
import com.tugasakhir.mongodbprosespembelajaran.model.Proses;
import com.tugasakhir.mongodbprosespembelajaran.service.prosesService;
import com.tugasakhir.mongodbprosespembelajaran.repository.MemberRepository;
import com.tugasakhir.mongodbprosespembelajaran.repository.prosesRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberUpdater {

    private final prosesRepository prosesRepository;
    private final MemberRepository memberRepository;

    public MemberUpdater(prosesRepository prosesRepository, MemberRepository memberRepository) {
        this.prosesRepository = prosesRepository;
        this.memberRepository = memberRepository;
    }

//    @Scheduled(fixedRate = 5000) // Run every 5 seconds
//    public void updateMembers() {
//        List<Proses> allProses = prosesRepository.findAll();
//        for (Proses proses : allProses) {
//            String idKelas = proses.getIdKelas();
//            List<Member> members = memberRepository.findByIdKelas(idKelas);
//            for (Member member : members) {
//                proses.addMember(member);
//            }
//            prosesRepository.save(proses); // Save the updated Proses
//        }
//    }
}
