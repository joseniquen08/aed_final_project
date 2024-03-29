package pe.edu.utp.final_project.services;

import java.io.IOException;

import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.final_project.domain.dashboard.BuyItem;
import pe.edu.utp.final_project.domain.dashboard.FiltersRequest;
import pe.edu.utp.final_project.domain.dashboard.SearchResponse;
import pe.edu.utp.final_project.domain.dashboard.StatisticsResponse;
import pe.edu.utp.final_project.domain.dashboard.statistics.Entity;
import pe.edu.utp.final_project.domain.dashboard.statistics.FrameworkAgreement;

public interface IDashboardService {
  SearchResponse search(String value, String type, int page, FiltersRequest[] filters);

  void exportPDF(BuyItem[] results, HttpServletResponse response) throws DocumentException, IOException;

  StatisticsResponse<Entity> getStatisticsEntityProvider(FiltersRequest[] filters);

  StatisticsResponse<FrameworkAgreement> getStatisticsFAProvider(FiltersRequest[] filters);

  StatisticsResponse<Entity> getStatisticsFADayOfMonth();

}
