package pe.edu.utp.final_project.domain.dashboard;

public class StatisticsRequestBody {
  private FiltersRequest[] filters;

  public FiltersRequest[] getFilters() {
    return filters;
  }

  public void setFilters(FiltersRequest[] filters) {
    this.filters = filters;
  }
}
