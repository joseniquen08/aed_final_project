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
import pe.edu.utp.final_project.domain.dashboard.FiltersRequest;
import pe.edu.utp.final_project.domain.dashboard.SearchItem;
import pe.edu.utp.final_project.domain.dashboard.SearchResponse;

@Service
public class DashboardServiceImpl implements IDashboardService {

  @Override
  public SearchResponse searchFisrt(String value, String type, int page, FiltersRequest[] filters) {
    try {
      String filePath = "./src/main/resources/files/ReportePCBienes202201.csv";
      FileReader fileReader = new FileReader(filePath);

      try (CSVReader openCSVReader = new CSVReaderBuilder(fileReader)
          .withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build()) {
        String[] record;

        int cont = 0;
        String[] arrayHeader = new String[19];
        SearchResponse response = new SearchResponse();
        LinkedList<SearchItem> list = new LinkedList<>();

        System.out.println("--------");
        for (int i = 0; i < filters.length; i++) {
          System.out.println(filters[i].getHeader() + " " + filters[i].getValue());
        }

        while ((record = openCSVReader.readNext()) != null) {
          if (cont == 0) {
            for (int i = 0; i < 19; i++) {
              arrayHeader[i] = record[i];
            }
          } else {
            boolean hasFilter = false;
            if (record[Integer.parseInt(type)].equals(value)) {
              for (int i = 0; i < filters.length; i++) {
                if (record[filters[i].getHeader()].equals(filters[i].getValue())) {
                  hasFilter = true;
                }
              }
              if (!hasFilter) {
                SearchItem item = new SearchItem(record[0], record[1], record[2], record[3], record[4], record[5],
                    record[6], record[7], record[8], record[9], record[10], record[11], record[12], record[13],
                    record[14],
                    record[15], record[16], record[17], record[18]);
                list.addToEnd(item);
              }
            }
          }
          cont++;
        }

        int totalPages = (int) Math.ceil((double) list.size() / 5);
        int[] pages = new int[totalPages];

        int length = totalPages == page && list.size() % 5 != 0 ? list.size() % 5 : 5;

        SearchItem[] items = new SearchItem[list.size() != 0 ? length : 0];
        SearchItem[] itemsTotal = new SearchItem[list.size()];

        Node<SearchItem> currentNode = list.header;
        Node<SearchItem> currentNodeTotal = list.header;

        int auxCont = 0;

        for (int i = 0; i < list.size(); i++) {
          if (i >= (page - 1) * 5 && i < page * 5) {
            items[auxCont] = currentNode.getValue();
            auxCont++;
          }
          currentNode = currentNode.getNext();
        }

        for (int i = 0; i < list.size(); i++) {
          itemsTotal[i] = currentNodeTotal.getValue();
          currentNodeTotal = currentNodeTotal.getNext();
        }

        for (int i = 0; i < pages.length; i++) {
          pages[i] = i + 1;
        }

        response.setHeaders(arrayHeader);
        response.setResults(items);
        response.setResultsTotal(itemsTotal);
        response.setValue(value);
        response.setType(type);
        response.setTotal(list.size());
        response.setPage(page);
        response.setPages(pages);

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
