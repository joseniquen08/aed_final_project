package pe.edu.utp.final_project.domain.dashboard;

public class StatisticsResponse<E> {
  private E[] results;

  public E[] getResults() {
    return results;
  }

  public void setResults(E[] results) {
    this.results = results;
  }
}
