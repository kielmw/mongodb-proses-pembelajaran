package com.tugasakhir.mongodbprosespembelajaran.repository;

import com.tugasakhir.mongodbprosespembelajaran.model.ProsesUpdater;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProsesUpdaterRepository extends MongoRepository<ProsesUpdater, Integer> {
}
