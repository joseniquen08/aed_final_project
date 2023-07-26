package pe.edu.utp.final_project.domain.dashboard;

public class ExportRequestBody {
  private BuyItem[] results;

  public BuyItem[] getResults() {
    return results;
  }

  public void setResults(BuyItem[] results) {
    this.results = results;
  }
}
