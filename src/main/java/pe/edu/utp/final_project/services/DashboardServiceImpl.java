package pe.edu.utp.final_project.services;

import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import pe.edu.utp.final_project.classes.LinkedList;
import pe.edu.utp.final_project.classes.Node;
import pe.edu.utp.final_project.domain.dashboard.SearchItem;
import pe.edu.utp.final_project.domain.dashboard.SearchResponse;

@Service
public class DashboardServiceImpl implements IDashboardService {

  @Override
  public SearchResponse searchFisrt(String value, String type) {
    try {
      String filePath = "./src/main/resources/files/ReportePCBienes202201.csv";
      FileReader fileReader = new FileReader(filePath);

      try (CSVReader openCSVReader = new CSVReaderBuilder(fileReader)
          .withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build()) {
        String[] record;

        int cont = 0;
        String[] arrayHeader = new String[16];
        SearchResponse response = new SearchResponse();
        LinkedList<SearchItem> list = new LinkedList<>();

        while ((record = openCSVReader.readNext()) != null) {
          if (cont == 0) {
            for (int i = 0; i < 16; i++) {
              arrayHeader[i] = record[i];
            }
          } else {
            if (record[Integer.parseInt(type)].equals(value)) {
              SearchItem item = new SearchItem(record[0], record[1], record[2], record[3], record[4], record[5],
                  record[6], record[7], record[8], record[9], record[10], record[11], record[12], record[13],
                  record[14],
                  record[15]);
              list.addToEnd(item);
            }
          }
          cont++;
        }

        SearchItem[] items = new SearchItem[list.size()];

        Node<SearchItem> currentNode = list.header;

        for (int i = 0; i < list.size(); i++) {
          items[i] = currentNode.getValue();
          currentNode = currentNode.getNext();
        }

        response.setHeaders(arrayHeader);
        response.setResults(items);

        return response;
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (CsvValidationException e) {
      e.printStackTrace();
    }
    return new SearchResponse();
  }
}
