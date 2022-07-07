package com.example.indexstorage.repo;

import com.example.indexstorage.model.IndexRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.assertj.core.api.Assertions;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@DataJpaTest
class IndexRecordRepoTest {
    
    @Autowired
    IndexRecordRepo indexRecordRepo;

    @Test
    void shouldSaveToDB() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        IndexRecord indexRecord = new IndexRecord(null
                ,1
                ,"AA"
                ,formatter.parse("1/7/2011")
                ,15.82
                ,16.72
                ,15.78
                ,16.42
                ,239655616
                ,3.79267
                ,null
                ,null
                ,16.71
                ,15.97
                ,-4.42849,
                26,
                0.182704);

        indexRecordRepo.save(indexRecord);
        Assertions.assertThat(indexRecord.getId()).isNotNull();
    }

    @Test
    void shouldReturnEmptyListWhenNoRecord() {
       List<IndexRecord> indexRecords =  indexRecordRepo.findAllByStock("AA");
       Assertions.assertThat(indexRecords.size()).isEqualTo(0);
    }

    @Test
    void shouldRetrieveByStock() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        IndexRecord indexRecord = new IndexRecord(null
                ,1
                ,"AA"
                , formatter.parse("1/7/2011")
                ,15.82
                ,16.72
                ,15.78
                ,16.42
                ,239655616
                ,3.79267
                ,null
                ,null
                ,16.71
                ,15.97
                ,-4.42849,
                26,
                0.182704);

        IndexRecord indexRecord2 = new IndexRecord(null
                ,1
                ,"AA"
                ,formatter.parse("1/7/2011")
                ,15.82
                ,16.72
                ,15.78
                ,16.42
                ,239655616
                ,3.79267
                ,null
                ,null
                ,16.71
                ,15.97
                ,-4.42849,
                26,
                0.182704);


        IndexRecord indexRecord3 = new IndexRecord(null
                ,1
                ,"AXP"
                ,formatter.parse("1/7/2011")
                ,15.82
                ,16.72
                ,15.78
                ,16.42
                ,239655616
                ,3.79267
                ,null
                ,null
                ,16.71
                ,15.97
                ,-4.42849,
                26,
                0.182704);

        indexRecordRepo.save(indexRecord);
        indexRecordRepo.save(indexRecord2);
        indexRecordRepo.save(indexRecord3);

        Assertions.assertThat(indexRecord.getId()).isNotNull();
        Assertions.assertThat(indexRecord2.getId()).isNotNull();
        Assertions.assertThat(indexRecord3.getId()).isNotNull();

        List<IndexRecord> indexRecords = indexRecordRepo.findAllByStock("AA");
        Assertions.assertThat(indexRecords.size()).isEqualTo(2);
        Assertions.assertThat(indexRecords.get(0).getStock()).isEqualTo("AA");
        Assertions.assertThat(indexRecords.get(1).getStock()).isEqualTo("AA");
    }
}