package com.tugasakhir.mongodbprosespembelajaran.repository;

import com.tugasakhir.mongodbprosespembelajaran.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
