package pe.edu.utp.final_project.domain.dashboard;

public class SearchResponse {
  private String[] headers;
  private SearchItem[] results;
  private String value;
  private String type;
  private int total;
  private int page;
  private int[] pages;

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

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int[] getPages() {
    return pages;
  }

  public void setPages(int[] pages) {
    this.pages = pages;
  }
}
