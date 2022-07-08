package com.example.indexstorage.service;

import com.example.indexstorage.model.IndexRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

public interface IndexRecordService {
    void saveIndexRecord(IndexRecord indexRecord);
    ArrayList<IndexRecord> getIndexRecordByStock(String stock);
}
