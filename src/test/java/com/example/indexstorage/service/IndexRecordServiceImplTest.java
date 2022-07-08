package com.example.indexstorage.service;

import com.example.indexstorage.model.IndexRecord;
import com.example.indexstorage.repo.IndexRecordRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IndexRecordServiceImplTest {

    @Mock
    private IndexRecordRepo indexRecordRepo;
    private IndexRecordService indexRecordService;
    private IndexRecord indexRecord;

    @BeforeEach
    void setup() throws ParseException {
        indexRecordService = new IndexRecordServiceImpl(indexRecordRepo);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        indexRecord = new IndexRecord(null
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
    }

    @Test
    void canSaveIndexRecord() throws ParseException {
        indexRecordService.saveIndexRecord(indexRecord);

        ArgumentCaptor<IndexRecord> indexRecordArgumentCaptor =
                ArgumentCaptor.forClass(IndexRecord.class);

        verify(indexRecordRepo).save(indexRecordArgumentCaptor.capture());
        IndexRecord capturedRecord = indexRecordArgumentCaptor.getValue();
        assertThat(capturedRecord).isEqualTo(indexRecord);
    }

    @Test
    void canGetIndexRecordsByStock() {
        when(indexRecordRepo.findAllByStock(any(String.class))).thenReturn(new ArrayList<>(List.of(indexRecord)));

        ArrayList<IndexRecord> indexRecords = new ArrayList<>(indexRecordService.getIndexRecordByStock("AA"));
        assertThat(indexRecords.size()).isEqualTo(1);
        verify(indexRecordRepo).findAllByStock(argThat(arg -> {
            assertThat(arg).isNotNull();
            assertThat(arg).isEqualTo("AA");
            return true;
        }));
    }

    @Test
    void shouldThrowExceptionWhenNoRecord() {
        when(indexRecordRepo.findAllByStock(any(String.class))).thenReturn(new ArrayList<>());
        Executable executable = () -> indexRecordService.getIndexRecordByStock("AA");
        Exception exception = Assertions.assertThrows(IllegalStateException.class, executable);

        assertThat(exception.getMessage()).isEqualTo("No Index Record is found");
    }

}
