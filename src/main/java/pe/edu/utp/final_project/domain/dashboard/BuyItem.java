package pe.edu.utp.final_project.domain.dashboard;

public class BuyItem {
  private String fechaProceso;
  private Long rucProveedor;
  private String proveedor;
  private Long rucEntidad;
  private String entidad;
  private String tipoProcedimiento;
  private String ordenElectronica;
  private String ordenElectronicaGenerada;
  private String estadoOrdenElectronica;
  private String documentoEstadoOcam;
  private String fechaFormalizacion;
  private String fechaUltimoEstado;
  private Double subTotal;
  private Double igv;
  private Double total;
  private String ordenDigitalizada;
  private String descripcionEstado;
  private String descripcionCesionDerechos;
  private String acuerdoMarco;

  public BuyItem(String fechaProceso, Long rucProveedor, String proveedor, Long rucEntidad, String entidad,
      String tipoProcedimiento, String ordenElectronica, String ordenElectronicaGenerada, String estadoOrdenElectronica,
      String documentoEstadoOcam, String fechaFormalizacion, String fechaUltimoEstado, Double subTotal, Double igv,
      Double total, String ordenDigitalizada, String descripcionEstado, String descripcionCesionDerechos,
      String acuerdoMarco) {
    this.fechaProceso = fechaProceso;
    this.rucProveedor = rucProveedor;
    this.proveedor = proveedor;
    this.rucEntidad = rucEntidad;
    this.entidad = entidad;
    this.tipoProcedimiento = tipoProcedimiento;
    this.ordenElectronica = ordenElectronica;
    this.ordenElectronicaGenerada = ordenElectronicaGenerada;
    this.estadoOrdenElectronica = estadoOrdenElectronica;
    this.documentoEstadoOcam = documentoEstadoOcam;
    this.fechaFormalizacion = fechaFormalizacion;
    this.fechaUltimoEstado = fechaUltimoEstado;
    this.subTotal = subTotal;
    this.igv = igv;
    this.total = total;
    this.ordenDigitalizada = ordenDigitalizada;
    this.descripcionEstado = descripcionEstado;
    this.descripcionCesionDerechos = descripcionCesionDerechos;
    this.acuerdoMarco = acuerdoMarco;
  }

  public String getFechaProceso() {
    return fechaProceso;
  }

  public void setFechaProceso(String fechaProceso) {
    this.fechaProceso = fechaProceso;
  }

  public Long getRucProveedor() {
    return rucProveedor;
  }

  public void setRucProveedor(Long rucProveedor) {
    this.rucProveedor = rucProveedor;
  }

  public String getProveedor() {
    return proveedor;
  }

  public void setProveedor(String proveedor) {
    this.proveedor = proveedor;
  }

  public Long getRucEntidad() {
    return rucEntidad;
  }

  public void setRucEntidad(Long rucEntidad) {
    this.rucEntidad = rucEntidad;
  }

  public String getEntidad() {
    return entidad;
  }

  public void setEntidad(String entidad) {
    this.entidad = entidad;
  }

  public String getTipoProcedimiento() {
    return tipoProcedimiento;
  }

  public void setTipoProcedimiento(String tipoProcedimiento) {
    this.tipoProcedimiento = tipoProcedimiento;
  }

  public String getOrdenElectronica() {
    return ordenElectronica;
  }

  public void setOrdenElectronica(String ordenElectronica) {
    this.ordenElectronica = ordenElectronica;
  }

  public String getOrdenElectronicaGenerada() {
    return ordenElectronicaGenerada;
  }

  public void setOrdenElectronicaGenerada(String ordenElectronicaGenerada) {
    this.ordenElectronicaGenerada = ordenElectronicaGenerada;
  }

  public String getEstadoOrdenElectronica() {
    return estadoOrdenElectronica;
  }

  public void setEstadoOrdenElectronica(String estadoOrdenElectronica) {
    this.estadoOrdenElectronica = estadoOrdenElectronica;
  }

  public String getDocumentoEstadoOcam() {
    return documentoEstadoOcam;
  }

  public void setDocumentoEstadoOcam(String documentoEstadoOcam) {
    this.documentoEstadoOcam = documentoEstadoOcam;
  }

  public String getFechaFormalizacion() {
    return fechaFormalizacion;
  }

  public void setFechaFormalizacion(String fechaFormalizacion) {
    this.fechaFormalizacion = fechaFormalizacion;
  }

  public String getFechaUltimoEstado() {
    return fechaUltimoEstado;
  }

  public void setFechaUltimoEstado(String fechaUltimoEstado) {
    this.fechaUltimoEstado = fechaUltimoEstado;
  }

  public Double getSubTotal() {
    return subTotal;
  }

  public void setSubTotal(Double subTotal) {
    this.subTotal = subTotal;
  }

  public Double getIgv() {
    return igv;
  }

  public void setIgv(Double igv) {
    this.igv = igv;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public String getOrdenDigitalizada() {
    return ordenDigitalizada;
  }

  public void setOrdenDigitalizada(String ordenDigitalizada) {
    this.ordenDigitalizada = ordenDigitalizada;
  }

  public String getDescripcionEstado() {
    return descripcionEstado;
  }

  public void setDescripcionEstado(String descripcionEstado) {
    this.descripcionEstado = descripcionEstado;
  }

  public String getDescripcionCesionDerechos() {
    return descripcionCesionDerechos;
  }

  public void setDescripcionCesionDerechos(String descripcionCesionDerechos) {
    this.descripcionCesionDerechos = descripcionCesionDerechos;
  }

  public String getAcuerdoMarco() {
    return acuerdoMarco;
  }

  public void setAcuerdoMarco(String acuerdoMarco) {
    this.acuerdoMarco = acuerdoMarco;
  }
}
