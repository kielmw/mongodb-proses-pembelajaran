package com.tugasakhir.mongodbprosespembelajaran.service;

import com.tugasakhir.mongodbprosespembelajaran.model.Member;
import com.tugasakhir.mongodbprosespembelajaran.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public List<Integer> getNimsByIdKelas(Integer idKelas) {
        List<Member> members = memberRepository.findByIdKelas(idKelas);
        return members.stream().map(Member::getNim).collect(Collectors.toList());
    }
}
