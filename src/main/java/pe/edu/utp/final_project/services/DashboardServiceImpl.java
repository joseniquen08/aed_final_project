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

  @Override
  public void exportPDF(SearchItem[] results, HttpServletResponse response)
      throws DocumentException, IOException {
    Document document = new Document(PageSize.A4.rotate(), 10, 10, 10, 10);
    PdfWriter.getInstance(document, response.getOutputStream());
    document.open();

    Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
    fontTitle.setSize(16);

    Paragraph paragraph = new Paragraph("Resultados de la búsqueda");
    paragraph.setAlignment(Paragraph.ALIGN_CENTER);

    document.add(paragraph);

    PdfPTable table = new PdfPTable(19);
    table.setWidthPercentage(100f);
    table.setWidths(new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 });
    table.setSpacingBefore(10);

    PdfPCell cell = new PdfPCell();
    cell.setBackgroundColor(Color.WHITE);
    cell.setPadding(3);

    Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
    fontHeader.setColor(Color.BLACK);
    fontHeader.setSize(8);

    cell.setPhrase(new Paragraph("FECHA_PROCESO", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("RUC_PROVEEDOR", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("PROVEEDOR", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("RUC_ENTIDAD", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("ENTIDAD", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("TIPO_PROCEDIMIENTO", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("ORDEN_ELECTRÓNICA", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("ORDEN_ELECTRÓNICA_GENERADA", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("ESTADO_ORDEN_ELECTRÓNICA", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("DOCUMENTO_ESTADO_OCAM", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("FECHA_FORMALIZACIÓN", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("FECHA_ÚLTIMO_ESTADO", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("SUB_TOTAL", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("IGV", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("TOTAL", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("ORDEN_DIGITALIZADA", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("DESCRIPCIÓN_ESTADO", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("DESCRIPCIÓN_CESIÓN_DERECHOS", fontHeader));
    table.addCell(cell);
    cell.setPhrase(new Paragraph("ACUERDO_MARCO", fontHeader));
    table.addCell(cell);

    Font fontBody = FontFactory.getFont(FontFactory.HELVETICA);
    fontBody.setColor(Color.BLACK);
    fontBody.setSize(8);

    for (SearchItem item : results) {
      cell.setPhrase(new Paragraph(item.getFechaProceso(), fontBody));
      table.addCell(cell);
      cell.setPhrase(new Paragraph(item.getRucProveedor(), fontBody));
      table.addCell(cell);
      cell.setPhrase(new Paragraph(item.getProveedor(), fontBody));
      table.addCell(cell);
      cell.setPhrase(new Paragraph(item.getRucEntidad(), fontBody));
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
      cell.setPhrase(new Paragraph(item.getSubTotal(), fontBody));
      table.addCell(cell);
      cell.setPhrase(new Paragraph(item.getIgv(), fontBody));
      table.addCell(cell);
      cell.setPhrase(new Paragraph(item.getTotal(), fontBody));
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
}
