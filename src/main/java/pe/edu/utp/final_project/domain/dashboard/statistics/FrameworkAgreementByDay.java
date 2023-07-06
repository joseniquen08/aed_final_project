package pe.edu.utp.final_project.domain.dashboard.statistics;

public class FrameworkAgreementByDay {
  private String name;
  private Month[] months;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Month[] getMonths() {
    return months;
  }

  public void setMonths(Month[] months) {
    this.months = months;
  }
}
