package pe.edu.utp.final_project.domain.dashboard;

public class SearchResponse {
  private String[] headers;
  private SearchItem[] results;

  public String[] getHeaders() {
    return headers;
  }

  public void setHeaders(String[] headers) {
    this.headers = headers;
  }

  public SearchItem[] getResults() {
    return results;
  }

  public void setResults(SearchItem[] results) {
    this.results = results;
  }
}
