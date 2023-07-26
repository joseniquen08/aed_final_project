package pe.edu.utp.final_project.services;

import java.awt.Color;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.final_project.classes.Tree;
import pe.edu.utp.final_project.domain.dashboard.BuyItem;
import pe.edu.utp.final_project.domain.dashboard.FiltersRequest;
import pe.edu.utp.final_project.domain.dashboard.SearchResponse;
import pe.edu.utp.final_project.domain.dashboard.StatisticsResponse;
import pe.edu.utp.final_project.domain.dashboard.statistics.Entity;
import pe.edu.utp.final_project.domain.dashboard.statistics.FrameworkAgreement;
import pe.edu.utp.final_project.domain.dashboard.statistics.Provider;

@Service
public class DashboardServiceImpl implements IDashboardService {

  @Override
  public SearchResponse search(String value, String type, int page, FiltersRequest[] filters) {
    try {
      String filePath = "./src/main/resources/files/ReportePCBienes202201.csv";
      FileReader fileReader = new FileReader(filePath);

      try (CSVReader openCsvReader = new CSVReaderBuilder(fileReader)
          .withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build()) {
        String[] record;

        int cont = 0;
        String[] arrayHeader = new String[19];
        SearchResponse response = new SearchResponse();
        Tree<BuyItem> root = new Tree<>();

        while ((record = openCsvReader.readNext()) != null) {
          if (cont == 0) {
            for (int i = 0; i < 19; i++) {
              arrayHeader[i] = record[i];
            }
          } else {
            boolean hasFilter = false;
            for (int i = 0; i < filters.length; i++) {
              if (record[filters[i].getHeader()].equals(filters[i].getValue())) {
                hasFilter = true;
              }
            }
            if (!hasFilter) {
              BuyItem item = new BuyItem(record[0], Long.parseLong(record[1]), record[2], Long.parseLong(record[3]),
                  record[4], record[5], record[6], record[7], record[8], record[9], record[10], record[11],
                  Double.parseDouble(record[12]), Double.parseDouble(record[13]), Double.parseDouble(record[14]),
                  record[15], record[16], record[17], record[18]);
              root.insertByType(item, Integer.parseInt(type));
            }
          }
          cont++;
        }

        BuyItem[] resultItems = root.getItemsByType(Integer.parseInt(type), value);

        int totalPages = (int) Math.ceil((double) resultItems.length / 5);
        int[] pages = new int[totalPages];

        int length = totalPages == page && resultItems.length % 5 != 0 ? resultItems.length % 5 : 5;

        BuyItem[] items = new BuyItem[resultItems.length != 0 ? length : 0];

        int auxCont = 0;

        for (int i = 0; i < resultItems.length; i++) {
          if (i >= (page - 1) * 5 && i < page * 5) {
            items[auxCont] = resultItems[i];
            auxCont++;
          }
        }

        for (int i = 0; i < pages.length; i++) {
          pages[i] = i + 1;
        }

        response.setHeaders(arrayHeader);
        response.setResults(items);
        response.setResultsTotal(resultItems);
        response.setValue(value);
        response.setType(type);
        response.setTotal(resultItems.length);
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

  @Override
  public void exportPDF(BuyItem[] results, HttpServletResponse response)
      throws DocumentException, IOException {
    Document document = new Document(PageSize.A4.rotate(), 10, 10, 10, 10);
    PdfWriter.getInstance(document, response.getOutputStream());
    document.open();

    Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
    fontTitle.setSize(18);

    Paragraph paragraph = new Paragraph("Resultados de la búsqueda");
    paragraph.setAlignment(Paragraph.ALIGN_CENTER);

    document.add(paragraph);

    PdfPTable table = new PdfPTable(19);
    table.setWidthPercentage(100f);
    table.setWidths(new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 });
    table.setSpacingBefore(10);

    PdfPCell cell = new PdfPCell();
    cell.setBackgroundColor(Color.WHITE);
    cell.setPadding(2);

    Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
    fontHeader.setColor(Color.BLACK);
    fontHeader.setSize(6);

    cell.setPhrase(new Paragraph("FECHA DE PROCESO", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("RUC DE PROVEEDOR", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("PROVEEDOR", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("RUC DE ENTIDAD", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("ENTIDAD", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("TIPO DE PROCEDIMIENTO", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("ORDEN ELECTRÓNICA", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("ORDEN ELECTRÓNICA GENERADA", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("ESTADO DE ORDEN ELECTRÓNICA", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("DOCUMENTO DE ESTADO OCAM", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("FECHA DE FORMALIZACIÓN", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("FECHA DE ÚLTIMO ESTADO", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("SUBTOTAL", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("IGV", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("TOTAL", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("ORDEN DIGITALIZADA", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("DESCRIPCIÓN DE ESTADO", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("DESCRIPCIÓN DE CESIÓN DE DERECHOS", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("ACUERDO MARCO", fontHeader));
    table.addCell(cell);

    Font fontBody = FontFactory.getFont(FontFactory.HELVETICA);
    fontBody.setColor(Color.BLACK);
    fontBody.setSize(7);

    for (BuyItem item : results) {
      cell.setPhrase(new Paragraph(item.getFechaProceso(), fontBody));
      table.addCell(cell);
      cell.setPhrase(new Paragraph(Long.toString(item.getRucProveedor()), fontBody));
      table.addCell(cell);
      cell.setPhrase(new Paragraph(item.getProveedor(), fontBody));
      table.addCell(cell);
      cell.setPhrase(new Paragraph(Long.toString(item.getRucEntidad()), fontBody));
      table.addCell(cell);
      cell.setPhrase(new Paragraph(item.getEntidad(), fontBody));
      table.addCell(cell);
      cell.setPhrase(new Paragraph(item.getTipoProcedimiento(), fontBody));
      table.addCell(cell);
      cell.setPhrase(new Paragraph(item.getOrdenElectronica(), fontBody));
      table.addCell(cell);
      cell.setPhrase(new Paragraph(item.getOrdenElectronicaGenerada(), fontBody));
      table.addCell(cell);
      cell.setPhrase(new Paragraph(item.getEstadoOrdenElectronica(), fontBody));
      table.addCell(cell);
      cell.setPhrase(new Paragraph(item.getDocumentoEstadoOcam(), fontBody));
      table.addCell(cell);
      cell.setPhrase(new Paragraph(item.getFechaFormalizacion(), fontBody));
      table.addCell(cell);
      cell.setPhrase(new Paragraph(item.getFechaUltimoEstado(), fontBody));
      table.addCell(cell);
      cell.setPhrase(new Paragraph(Double.toString(item.getSubTotal()), fontBody));
      table.addCell(cell);
      cell.setPhrase(new Paragraph(Double.toString(item.getIgv()), fontBody));
      table.addCell(cell);
      cell.setPhrase(new Paragraph(Double.toString(item.getTotal()), fontBody));
      table.addCell(cell);
      cell.setPhrase(new Paragraph(item.getOrdenDigitalizada(), fontBody));
      table.addCell(cell);
      cell.setPhrase(new Paragraph(item.getDescripcionEstado(), fontBody));
      table.addCell(cell);
      cell.setPhrase(new Paragraph(item.getDescripcionCesionDerechos(), fontBody));
      table.addCell(cell);
      cell.setPhrase(new Paragraph(item.getAcuerdoMarco(), fontBody));
      table.addCell(cell);
    }

    document.add(table);
    document.close();
  }

  @Override
  public StatisticsResponse<Entity> getStatisticsEntityProvider(FiltersRequest[] filters) {
    try {
      String filePath = "./src/main/resources/files/ReportePCBienes202201.csv";
      FileReader fileReader = new FileReader(filePath);

      try (CSVReader openCSVReader = new CSVReaderBuilder(fileReader)
          .withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build()) {
        String[] record;

        int cont = 0;
        Tree<BuyItem> root = new Tree<>();
        StatisticsResponse<Entity> response = new StatisticsResponse<>();

        while ((record = openCSVReader.readNext()) != null) {
          if (cont != 0) {
            boolean hasFilter = false;
            for (int i = 0; i < filters.length; i++) {
              if (record[filters[i].getHeader()].equals(filters[i].getValue())) {
                hasFilter = true;
              }
            }
            if (!hasFilter) {
              BuyItem item = new BuyItem(record[0], Long.parseLong(record[1]), record[2], Long.parseLong(record[3]),
                  record[4], record[5], record[6], record[7], record[8], record[9], record[10], record[11],
                  Double.parseDouble(record[12]), Double.parseDouble(record[13]), Double.parseDouble(record[14]),
                  record[15], record[16], record[17], record[18]);
              root.insertByType(item, 1);
            }
          }
          cont++;
        }

        BuyItem[] entityItems = root.getUniqueEntities();
        Entity[] entities = new Entity[entityItems.length];

        for (int i = 0; i < entityItems.length; i++) {
          Provider[] providers = root.getUniqueProvidersByEntity(entityItems[i].getEntidad());
          entities[i] = new Entity(entityItems[i].getEntidad(), providers);
        }

        response.setResults(entities);

        return response;
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (CsvValidationException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public StatisticsResponse<FrameworkAgreement> getStatisticsFAProvider(FiltersRequest[] filters) {
    try {
      String filePath = "./src/main/resources/files/ReportePCBienes202201.csv";
      FileReader fileReader = new FileReader(filePath);

      try (CSVReader openCSVReader = new CSVReaderBuilder(fileReader)
          .withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build()) {
        String[] record;

        int cont = 0;
        Tree<BuyItem> root = new Tree<>();
        StatisticsResponse<FrameworkAgreement> response = new StatisticsResponse<>();

        while ((record = openCSVReader.readNext()) != null) {
          if (cont != 0) {
            boolean hasFilter = false;
            for (int i = 0; i < filters.length; i++) {
              if (record[filters[i].getHeader()].equals(filters[i].getValue())) {
                hasFilter = true;
              }
            }
            if (!hasFilter) {
              BuyItem item = new BuyItem(record[0], Long.parseLong(record[1]), record[2], Long.parseLong(record[3]),
                  record[4], record[5], record[6], record[7], record[8], record[9], record[10], record[11],
                  Double.parseDouble(record[12]), Double.parseDouble(record[13]), Double.parseDouble(record[14]),
                  record[15], record[16], record[17], record[18]);
              root.insertByType(item, 1);
            }
          }
          cont++;
        }

        BuyItem[] frameworkAgreementsItems = root.getUniqueFrameworkAgreements();

        FrameworkAgreement[] frameworkAgreements = new FrameworkAgreement[frameworkAgreementsItems.length];

        for (int i = 0; i < frameworkAgreementsItems.length; i++) {
          Provider[] providers = root
              .getUniqueProvidersByFrameworkAgreement(frameworkAgreementsItems[i].getAcuerdoMarco());
          frameworkAgreements[i] = new FrameworkAgreement(frameworkAgreementsItems[i].getAcuerdoMarco(), providers);
        }

        response.setResults(frameworkAgreements);

        return response;
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (CsvValidationException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public StatisticsResponse<Entity> getStatisticsFADayOfMonth() {
    return null;
  }
}
