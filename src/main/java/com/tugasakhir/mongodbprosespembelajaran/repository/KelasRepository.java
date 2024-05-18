package com.tugasakhir.mongodbprosespembelajaran.repository;

import com.tugasakhir.mongodbprosespembelajaran.model.Kelas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KelasRepository extends JpaRepository<Kelas, Integer> {
}
