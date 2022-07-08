package com.example.indexstorage.util;

import com.example.indexstorage.IndexRecordDto.IndexRecordDto;
import com.example.indexstorage.model.IndexRecord;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;


@Component
public class IndexRecordMapper {
    private Double convertDollarAmountStringToDouble (String amount) {
        return Double.parseDouble(amount.replace("$", ""));
    }

    public IndexRecord map(IndexRecordDto indexRecordDto) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
        IndexRecord indexRecord = new IndexRecord();
        indexRecord.setQuarter(indexRecordDto.getQuarter());
        indexRecord.setStock(indexRecordDto.getStock());
        indexRecord.setDate(formatter.parse(indexRecordDto.getDate()));

        indexRecord.setClose(convertDollarAmountStringToDouble(indexRecordDto.getClose()));
        indexRecord.setOpen(convertDollarAmountStringToDouble(indexRecordDto.getOpen()));
        indexRecord.setHigh(convertDollarAmountStringToDouble(indexRecordDto.getHigh()));
        indexRecord.setLow(convertDollarAmountStringToDouble(indexRecordDto.getLow()));

        indexRecord.setVolume(indexRecordDto.getVolume());

        indexRecord.setPercentChangePrice(indexRecordDto.getPercentChangePrice());
        indexRecord.setPercentChangeVolumeOverLastWeek(indexRecordDto.getPercentChangeVolumeOverLastWeek());
        indexRecord.setPreviousWeeksVolume(indexRecordDto.getPreviousWeeksVolume());

        indexRecord.setNextWeeksClose(convertDollarAmountStringToDouble(indexRecordDto.getNextWeeksClose()));
        indexRecord.setNextWeeksOpen(convertDollarAmountStringToDouble(indexRecordDto.getNextWeeksOpen()));
        indexRecord.setPercentChangeNextWeeksPrice(indexRecordDto.getPercentChangeNextWeeksPrice());

        indexRecord.setDaysToNextDividend(indexRecordDto.getDaysToNextDividend());
        indexRecord.setPercentReturnNextDividend(indexRecordDto.getPercentReturnNextDividend());

        return indexRecord;
    }
}
