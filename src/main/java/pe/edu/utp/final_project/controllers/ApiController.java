package pe.edu.utp.final_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.utp.final_project.domain.dashboard.SearchRequestBody;
import pe.edu.utp.final_project.domain.dashboard.SearchResponse;
import pe.edu.utp.final_project.services.DashboardServiceImpl;

@RestController
@RequestMapping("/api")
public class ApiController {

  @Autowired
  private DashboardServiceImpl dashboardServiceImpl;

  @PostMapping("/buscar")
  public @ResponseBody SearchResponse buscar(@RequestBody SearchRequestBody searchRequestBody) {
    return dashboardServiceImpl.searchFisrt(searchRequestBody.getValue(), searchRequestBody.getType(),
        searchRequestBody.getPage(), searchRequestBody.getFilters());
  }

  @PostMapping("/paginar")
  public @ResponseBody SearchResponse paginar(@RequestBody SearchRequestBody searchRequestBody) {
    return dashboardServiceImpl.searchFisrt(searchRequestBody.getValue(), searchRequestBody.getType(),
        searchRequestBody.getPage(), searchRequestBody.getFilters());
  }
}
