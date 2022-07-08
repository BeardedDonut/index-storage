package com.example.indexstorage.util;

import com.example.indexstorage.IndexRecordDto.IndexRecordDto;
import com.example.indexstorage.model.IndexRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class IndexRecordUtilTest {
    IndexRecordUtil indexRecordUtil;

    @BeforeEach
    public void setup() {
       indexRecordUtil = new IndexRecordUtil();
    }

    @Test
    void mapIndexRecordDtoToIndexRecordModel() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String dateString = "1/7/2011";

        IndexRecordDto dto = new IndexRecordDto(1
                ,"AA"
                ,dateString
                ,"15.82"
                ,"16.72"
                ,"15.78"
                ,"16.42"
                ,239655616
                ,3.79267
                ,0.2
                ,1234
                ,"16.71"
                ,"15.97"
                ,-4.42849,
                26,
                0.182704);

        IndexRecord mappedRecord = indexRecordUtil.map(dto);

        assertThat(mappedRecord.getDate()).isEqualTo(formatter.parse(dateString));
        assertThat(mappedRecord.getLow()).isEqualTo(15.78);
        assertThat(mappedRecord.getDaysToNextDividend()).isEqualTo(26);
        assertThat(mappedRecord.getStock()).isEqualTo("AA");
    }

    @Test
    void csvByteStreamToListOfRecordDto() throws InterruptedException {
        String csvLine = "\n1,AA,1/7/2011,$15.82,$16.72,$15.78,$16.42,239655616,3.79267,,,$16.71,$15.97,-4.42849,26,0.182704\n" +
                "1,AA,1/14/2011,$16.71,$16.71,$15.64,$15.97,242963398,-4.42849,1.380223028,239655616,$16.19,$15.79,-2.47066,19,0.187852\n";
        List<IndexRecordDto> records = indexRecordUtil.csvByteStreamToListOfRecordDto(csvLine.getBytes());

        assertThat(records.size()).isEqualTo(2);
        assertThat(records.get(0).getOpen()).isEqualTo("$15.82");
        assertThat(records.get(1).getOpen()).isEqualTo("$16.71");
    }
}