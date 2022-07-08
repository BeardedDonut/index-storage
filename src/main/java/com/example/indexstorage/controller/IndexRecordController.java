package com.example.indexstorage.controller;

import com.example.indexstorage.dto.IndexRecordDto;
import com.example.indexstorage.exception.BadRequestException;
import com.example.indexstorage.model.IndexRecord;
import com.example.indexstorage.service.IndexRecordService;
import com.example.indexstorage.util.IndexRecordUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/index-record/")
@AllArgsConstructor
public class IndexRecordController {
    private IndexRecordService indexRecordService;
    private IndexRecordUtil indexRecordUtil;

    @GetMapping("{stock}")
    public ResponseEntity<ArrayList<IndexRecord>> getIndexRecordByType(@PathVariable String stock) {
        log.info("Received request to send index records for {}", stock);
        ArrayList<IndexRecord> indexRecords = indexRecordService.getIndexRecordByStock(stock);

        return ResponseEntity.ok(indexRecords);
    }

    @PostMapping()
    public ResponseEntity createNewRecord(@RequestBody IndexRecordDto indexRecordDto) throws ParseException {
        log.info("Received a request to create a record");

        IndexRecord indexRecord = indexRecordUtil.map(indexRecordDto);
        indexRecordService.saveIndexRecord(indexRecord);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/bulk-upload")
    public ResponseEntity bulkIUploadDataSet
            (@RequestBody MultipartFile file) throws IOException, InterruptedException, ParseException {
        List<IndexRecordDto> recordsDto = indexRecordUtil.csvByteStreamToListOfRecordDto(
                IOUtils.toByteArray(file.getInputStream()));
        if (recordsDto.size() == 0)  {
            throw new BadRequestException("No records to upload!");
        }

        for (IndexRecordDto indexRecord : recordsDto) {
            indexRecordService.saveIndexRecord(indexRecordUtil.map(indexRecord));
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
