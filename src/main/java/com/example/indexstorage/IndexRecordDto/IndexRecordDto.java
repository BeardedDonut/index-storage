package com.example.indexstorage.IndexRecordDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndexRecordDto {
    private Integer quarter; // 1 or 2
    private String stock;

    private String date;    // e.g. 1/7/2011

    private String open;    // e.g. $12.32
    private String high;    // e.g. $12.32
    private String low;     // e.g. $12.32
    private String close;   // e.g. $12.32
    private Integer volume;

    private Double percentChangePrice;
    private Double percentChangeVolumeOverLastWeek;
    private Integer previousWeeksVolume;

    private String nextWeeksOpen;   // e.g. $12.32
    private String nextWeeksClose;  // e.g. $12.32
    private Double percentChangeNextWeeksPrice;

    private Integer daysToNextDividend;
    private Double percentReturnNextDividend;
}


