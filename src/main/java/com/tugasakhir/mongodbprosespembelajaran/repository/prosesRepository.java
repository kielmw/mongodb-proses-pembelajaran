package com.tugasakhir.mongodbprosespembelajaran.repository;

import com.tugasakhir.mongodbprosespembelajaran.model.Proses;
import com.tugasakhir.mongodbprosespembelajaran.model.ItemPembelajaran;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface prosesRepository extends MongoRepository <Proses, String> {
    @Query("{'idKelas' : ?0}")
    Optional<Proses>findByIdKelas(String idKelas);
    @Query("{'idKelas' : ?0}")
    List<Proses>findByIdKelas2(String idKelas);
    @Query("{'idPdf' : ?0}")
    Optional<Proses>findByIdPdf(String idKelas);
    Optional<Proses> findByItemPembelajaransIdPertemuan(String idPertemuan);
    @Query(value = "{ 'idKelas': ?0, 'pdfs.fileName': { $exists: true } }", fields = "{ 'pdfs.fileName' : 1 }")
    List<String> findAllFileNameOptions();

}
