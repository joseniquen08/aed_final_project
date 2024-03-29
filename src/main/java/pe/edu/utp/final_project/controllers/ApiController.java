package pe.edu.utp.final_project.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.final_project.domain.dashboard.ExportRequestBody;
import pe.edu.utp.final_project.domain.dashboard.SearchRequestBody;
import pe.edu.utp.final_project.domain.dashboard.SearchResponse;
import pe.edu.utp.final_project.domain.dashboard.StatisticsRequestBody;
import pe.edu.utp.final_project.domain.dashboard.StatisticsResponse;
import pe.edu.utp.final_project.domain.dashboard.statistics.Entity;
import pe.edu.utp.final_project.domain.dashboard.statistics.FrameworkAgreement;
import pe.edu.utp.final_project.services.DashboardServiceImpl;

@RestController
@RequestMapping("/api")
public class ApiController {

  @Autowired
  private DashboardServiceImpl dashboardServiceImpl;

  @PostMapping("/buscar")
  public @ResponseBody SearchResponse buscar(@RequestBody SearchRequestBody searchRequestBody) {
    return dashboardServiceImpl.search(searchRequestBody.getValue(), searchRequestBody.getType(),
        searchRequestBody.getPage(), searchRequestBody.getFilters());
  }

  @PostMapping("/paginar")
  public @ResponseBody SearchResponse paginar(@RequestBody SearchRequestBody searchRequestBody) {
    return dashboardServiceImpl.search(searchRequestBody.getValue(), searchRequestBody.getType(),
        searchRequestBody.getPage(), searchRequestBody.getFilters());
  }

  @PostMapping("/filtrar")
  public @ResponseBody SearchResponse filtrar(@RequestBody SearchRequestBody searchRequestBody) {
    return dashboardServiceImpl.search(searchRequestBody.getValue(), searchRequestBody.getType(),
        searchRequestBody.getPage(), searchRequestBody.getFilters());
  }

  @PostMapping("/exportar/pdf")
  public void exportarPDF(@RequestBody ExportRequestBody exportRequestBody,
      HttpServletResponse response) throws DocumentException, IOException {
    response.setContentType("application/pdf");
    DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD_HH_MM_SS");
    String currentDateTime = dateFormat.format(new Date());
    String headerKey = "Content-Disposition";
    String headerValue = "attachment; filename=reporte-" + currentDateTime + ".pdf";
    response.setHeader(headerKey, headerValue);
    dashboardServiceImpl.exportPDF(exportRequestBody.getResults(), response);
  }

  @PostMapping("/generar_estadistica_1")
  public @ResponseBody StatisticsResponse<Entity> generarEstadistica1(
      @RequestBody StatisticsRequestBody statisticsRequestBody) {
    return dashboardServiceImpl.getStatisticsEntityProvider(statisticsRequestBody.getFilters());
  }

  @PostMapping("/generar_estadistica_1/filtrar")
  public @ResponseBody StatisticsResponse<Entity> filtrar1(
      @RequestBody StatisticsRequestBody statisticsRequestBody) {
    return dashboardServiceImpl.getStatisticsEntityProvider(statisticsRequestBody.getFilters());
  }

  @PostMapping("/generar_estadistica_2")
  public @ResponseBody StatisticsResponse<FrameworkAgreement> generarEstadistica2(
      @RequestBody StatisticsRequestBody statisticsRequestBody) {
    return dashboardServiceImpl.getStatisticsFAProvider(statisticsRequestBody.getFilters());
  }

  @PostMapping("/generar_estadistica_2/filtrar")
  public @ResponseBody StatisticsResponse<FrameworkAgreement> filtrar2(
      @RequestBody StatisticsRequestBody statisticsRequestBody) {
    return dashboardServiceImpl.getStatisticsFAProvider(statisticsRequestBody.getFilters());
  }

  @PostMapping("/generar_estadistica_3")
  public @ResponseBody StatisticsResponse<Entity> generarEstadistica3(
      @RequestBody StatisticsRequestBody statisticsRequestBody) {
    return dashboardServiceImpl.getStatisticsFADayOfMonth();
  }
}
