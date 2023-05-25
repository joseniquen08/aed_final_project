package pe.edu.utp.final_project.services;

import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

@Service
public class DashboardServiceImpl implements IDashboardService {

  @Override
  public boolean searchFisrt(String value, String type) {
    try {
      String filePath = "./src/main/resources/files/ReportePCBienes202201.csv";
      FileReader fileReader = new FileReader(filePath);

      try (CSVReader openCSVReader = new CSVReaderBuilder(fileReader)
          .withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build()) {
        String[] record;

        while ((record = openCSVReader.readNext()) != null) {
          System.out.println(record[Integer.parseInt(type)]);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (CsvValidationException e) {
      e.printStackTrace();
    }
    return true;
  }
}
