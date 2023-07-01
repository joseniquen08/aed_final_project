package pe.edu.utp.final_project.domain.dashboard;

public class FiltersRequest {
  private String value;
  private int header;

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public int getHeader() {
    return header;
  }

  public void setHeader(int header) {
    this.header = header;
  }
}
