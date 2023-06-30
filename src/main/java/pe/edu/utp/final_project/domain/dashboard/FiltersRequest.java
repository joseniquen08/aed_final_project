package pe.edu.utp.final_project.domain.dashboard;

public class FiltersRequest {
  private String value;
  private String header;

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }
}
