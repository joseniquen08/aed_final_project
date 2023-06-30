package pe.edu.utp.final_project.domain.dashboard;

public class SearchRequestBody {
  private String value;
  private String type;
  private int page;
  private FiltersRequest[] filters;

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public FiltersRequest[] getFilters() {
    return filters;
  }

  public void setFilters(FiltersRequest[] filters) {
    this.filters = filters;
  }
}
