package pe.edu.utp.final_project.domain.dashboard.statistics;

public class Provider {
  private String name;
  private double quantityAccepted;
  private double quantityPaid;
  private double quantitySuccess;

  public Provider(String name) {
    this.name = name;
    this.quantityAccepted = 1;
    this.quantityPaid = 1;
    this.quantitySuccess = 1;
  }

  public Provider(String name, double quantityAccepted, double quantityPaid, double quantitySuccess) {
    this.name = name;
    this.quantityAccepted = quantityAccepted;
    this.quantityPaid = quantityPaid;
    this.quantitySuccess = quantitySuccess;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getQuantityAccepted() {
    return quantityAccepted;
  }

  public void setQuantityAccepted(double quantityAccepted) {
    this.quantityAccepted = quantityAccepted;
  }

  public double getQuantityPaid() {
    return quantityPaid;
  }

  public void setQuantityPaid(double quantityPaid) {
    this.quantityPaid = quantityPaid;
  }

  public double getQuantitySuccess() {
    return quantitySuccess;
  }

  public void setQuantitySuccess(double quantitySuccess) {
    this.quantitySuccess = quantitySuccess;
  }
}
