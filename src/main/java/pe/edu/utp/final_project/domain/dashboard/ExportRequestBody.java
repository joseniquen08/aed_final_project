package pe.edu.utp.final_project.domain.dashboard;

public class ExportRequestBody {
  private SearchItem[] results;

  public SearchItem[] getResults() {
    return results;
  }

  public void setResults(SearchItem[] results) {
    this.results = results;
  }
}
