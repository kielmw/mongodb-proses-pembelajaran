package com.tugasakhir.mongodbprosespembelajaran.repository;

import com.tugasakhir.mongodbprosespembelajaran.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
