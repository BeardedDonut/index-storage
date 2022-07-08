package com.example.indexstorage.dto;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndexRecordDto {
    @CsvBindByPosition(position = 0)
    private Integer quarter; // 1 or 2
    @CsvBindByPosition(position = 1)
    private String stock;

    @CsvBindByPosition(position = 2)
    private String date;    // e.g. 1/7/2011

    @CsvBindByPosition(position = 3)
    private String open;    // e.g. $12.32
    @CsvBindByPosition(position = 4)
    private String high;    // e.g. $12.32
    @CsvBindByPosition(position = 5)
    private String low;     // e.g. $12.32
    @CsvBindByPosition(position = 6)
    private String close;   // e.g. $12.32
    @CsvBindByPosition(position = 7)
    private Integer volume;

    @CsvBindByPosition(position = 8)
    private Double percentChangePrice;
    @CsvBindByPosition(position = 9)
    private Double percentChangeVolumeOverLastWeek;
    @CsvBindByPosition(position = 10)
    private Integer previousWeeksVolume;

    @CsvBindByPosition(position = 11)
    private String nextWeeksOpen;   // e.g. $12.32
    @CsvBindByPosition(position = 12)
    private String nextWeeksClose;  // e.g. $12.32
    @CsvBindByPosition(position = 13)
    private Double percentChangeNextWeeksPrice;

    @CsvBindByPosition(position = 14)
    private Integer daysToNextDividend;
    @CsvBindByPosition(position = 15)
    private Double percentReturnNextDividend;
}


