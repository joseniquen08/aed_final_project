package pe.edu.utp.final_project.services;

import pe.edu.utp.final_project.domain.dashboard.SearchResponse;

public interface IDashboardService {
  SearchResponse searchFisrt(String value, String type, int page);
}
