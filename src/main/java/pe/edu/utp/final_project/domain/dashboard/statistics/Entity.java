package pe.edu.utp.final_project.domain.dashboard.statistics;

public class Entity {
  private String name;
  private Provider[] providers;

  public Entity(String name, Provider[] providers) {
    this.name = name;
    this.providers = providers;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Provider[] getProviders() {
    return providers;
  }

  public void setProviders(Provider[] providers) {
    this.providers = providers;
  }
}
