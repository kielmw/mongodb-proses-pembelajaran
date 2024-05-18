package com.tugasakhir.mongodbprosespembelajaran.repository;

import com.tugasakhir.mongodbprosespembelajaran.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends MongoRepository<Member, Long> {
    Optional<Member> findByNim(int nim);
}
