package com.example.indexstorage.util;

import com.example.indexstorage.dto.IndexRecordDto;
import com.example.indexstorage.model.IndexRecord;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


@Component
public class IndexRecordUtil {
    private Double convertDollarAmountStringToDouble (String amount) {
        return Double.parseDouble(amount.replace("$", ""));
    }

    public IndexRecord map(IndexRecordDto indexRecordDto) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
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

    public List<IndexRecordDto> csvByteStreamToListOfRecordDto(byte[] byteStream) throws InterruptedException {
        InputStream dataInputStream = new ByteArrayInputStream(byteStream);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(dataInputStream))) {
            String line = reader.readLine();

            return new CsvToBeanBuilder<IndexRecordDto>(reader)
                    .withType(IndexRecordDto.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
