package com.example.indexstorage.repo;

import com.example.indexstorage.model.IndexRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IndexRecordRepo extends JpaRepository<IndexRecord, Long> {
    List<IndexRecord> findAllByStock(String stock);
}
