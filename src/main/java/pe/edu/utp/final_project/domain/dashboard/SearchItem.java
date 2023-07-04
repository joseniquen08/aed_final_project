package pe.edu.utp.final_project.domain.dashboard;

public class SearchItem {
  public String fechaProceso;
  public String rucProveedor;
  public String proveedor;
  public String rucEntidad;
  public String entidad;
  public String tipoProcedimiento;
  public String ordenElectronica;
  public String ordenElectronicaGenerada;
  public String estadoOrdenElectronica;
  public String documentoEstadoOcam;
  public String fechaFormalizacion;
  public String fechaUltimoEstado;
  public String subTotal;
  public String igv;
  public String total;
  public String ordenDigitalizada;
  public String descripcionEstado;
  public String descripcionCesionDerechos;
  public String acuerdoMarco;

  public SearchItem(String fechaProceso, String rucProveedor, String proveedor, String rucEntidad, String entidad,
      String tipoProcedimiento, String ordenElectronica, String ordenElectronicaGenerada,
      String estadoOrdenElectronica, String documentoEstadoOcam, String fechaFormalizacion,
      String fechaUltimoEstado, String subTotal, String igv, String total, String ordenDigitalizada,
      String descripcionEstado, String descripcionCesionDerechos, String acuerdoMarco) {
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

  public String getRucProveedor() {
    return rucProveedor;
  }

  public void setRucProveedor(String rucProveedor) {
    this.rucProveedor = rucProveedor;
  }

  public String getProveedor() {
    return proveedor;
  }

  public void setProveedor(String proveedor) {
    this.proveedor = proveedor;
  }

  public String getRucEntidad() {
    return rucEntidad;
  }

  public void setRucEntidad(String rucEntidad) {
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

  public String getSubTotal() {
    return subTotal;
  }

  public void setSubTotal(String subTotal) {
    this.subTotal = subTotal;
  }

  public String getIgv() {
    return igv;
  }

  public void setIgv(String igv) {
    this.igv = igv;
  }

  public String getTotal() {
    return total;
  }

  public void setTotal(String total) {
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
