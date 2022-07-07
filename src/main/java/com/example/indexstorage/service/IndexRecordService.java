package com.example.indexstorage.service;

import com.example.indexstorage.model.IndexRecord;

import java.util.ArrayList;

public interface IndexRecordService {
    void saveIndexRecord(IndexRecord indexRecord);
    ArrayList<IndexRecord> getIndexRecordByStock(String stock);
}
