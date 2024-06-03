package com.tugasakhir.mongodbprosespembelajaran.controller;

import com.tugasakhir.mongodbprosespembelajaran.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/{idKelas}")
    public List<Integer> getNimsByIdKelas(@PathVariable Integer idKelas) {
        return memberService.getNimsByIdKelas(idKelas);
    }
}
