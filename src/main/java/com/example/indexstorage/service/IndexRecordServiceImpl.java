package com.example.indexstorage.service;

import com.example.indexstorage.model.IndexRecord;
import com.example.indexstorage.repo.IndexRecordRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class IndexRecordServiceImpl implements IndexRecordService {
    @Autowired
    private IndexRecordRepo indexRecordRepo;


    @Override
    @Transactional
    public void saveIndexRecord(IndexRecord indexRecord) {
        // some basic validations
        // check if record.quarter is either 1 or 2
        if (indexRecord.getQuarter() != 1 && indexRecord.getQuarter() != 2) {
            throw new IllegalStateException("invalid quarters");
        }

        indexRecordRepo.save(indexRecord);
    }

    @Override
    public ArrayList<IndexRecord> getIndexRecordByStock(String stock) {
        List<IndexRecord> indexRecords = indexRecordRepo.findAllByStock(stock);
        if (indexRecords.size() == 0) {
            throw new IllegalStateException("No Index Record is found");
        }

        return new ArrayList<IndexRecord>(indexRecords);
    }
}
