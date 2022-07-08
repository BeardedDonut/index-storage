package com.example.indexstorage.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class IndexRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quarter;
    private String stock;

    private Date date;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Integer volume;

    private Double percentChangePrice;
    private Double percentChangeVolumeOverLastWeek;
    private Integer previousWeeksVolume;

    private Double nextWeeksOpen;
    private Double nextWeeksClose;
    private Double percentChangeNextWeeksPrice;

    private Integer daysToNextDividend;
    private Double percentReturnNextDividend;
}
