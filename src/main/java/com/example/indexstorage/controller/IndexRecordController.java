package com.example.indexstorage.controller;

import com.example.indexstorage.IndexRecordDto.IndexRecordDto;
import com.example.indexstorage.model.IndexRecord;
import com.example.indexstorage.service.IndexRecordService;
import com.example.indexstorage.util.IndexRecordMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;

@RestController
@Slf4j
@RequestMapping("/api/index-record/")
@AllArgsConstructor
public class IndexRecordController {
    private IndexRecordService indexRecordService;
    private IndexRecordMapper indexRecordMapper;

    @GetMapping("{stock}")
    public ResponseEntity<ArrayList<IndexRecord>> getIndexRecordByType(@PathVariable String stock) {
        log.info("Received request to send index records for {}", stock);
        ArrayList<IndexRecord> indexRecords = indexRecordService.getIndexRecordByStock(stock);

        return ResponseEntity.ok(indexRecords);
    }

    @PostMapping()
    public ResponseEntity createNewRecord(@RequestBody IndexRecordDto indexRecordDto) throws ParseException {
        log.info("Received a request to create a record");

        IndexRecord indexRecord = indexRecordMapper.map(indexRecordDto);
        indexRecordService.saveIndexRecord(indexRecord);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
