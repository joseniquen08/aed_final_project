package pe.edu.utp.final_project.domain.dashboard.statistics;

public class Month {
  private String name;
  private Day[] days;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Day[] getDays() {
    return days;
  }

  public void setDays(Day[] days) {
    this.days = days;
  }
}
