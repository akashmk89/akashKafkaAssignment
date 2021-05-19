package com.producer.utils;

import com.producer.models.Station;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class CsvReader {
        public List<Station> readCSVAndGetRecords() throws IOException, ParseException {
            File file = ResourceUtils.getFile("classpath:stations.csv");
            String delimiter = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
            List<Station> stations = new ArrayList<Station>();
            int count = 0;
            String dataRow = "";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            /**
             * to skip the header column of the csv
             */
            while ((dataRow = bufferedReader.readLine()) != null) {
                if (count == 0) {
                    count++;
                    continue;
                }
                String[] token = dataRow.split(delimiter);
                Station station = new Station(
                        Integer.parseInt(token[0]),
                        token[1],
                        token[2],
                        token[3],
                        token[4],
                        Integer.parseInt(token[5]),
                        Integer.parseInt(token[6]),
                        Boolean.parseBoolean(token[7]),
                        Boolean.parseBoolean(token[8]),
                        Boolean.parseBoolean(token[9])
                        );
                stations.add(station);
            }
            log.info("There are total of " + stations.size() + " records in this database");
            log.info("stations fetched from csv");
            return stations;
        }
}
