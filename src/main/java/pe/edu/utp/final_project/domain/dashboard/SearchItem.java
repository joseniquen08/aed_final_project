package pe.edu.utp.final_project.domain.dashboard;

public class SearchItem {
  public String fecha_proceso;
  public String ruc_proveedor;
  public String proveedor;
  public String ruc_entidad;
  public String entidad;
  public String tipo_procedimiento;
  public String orden_electronica;
  public String orden_electronica_generada;
  public String estado_orden_electronica;
  public String documento_estado_ocam;
  public String fecha_formalizacion;
  public String fecha_ultimo_estado;
  public String sub_total;
  public String igv;
  public String total;
  public String orden_digitalizada;
  public String descripcion_estado;
  public String descripcion_cesion_derechos;
  public String acuerdo_marco;

  public SearchItem(String fecha_proceso, String ruc_proveedor, String proveedor, String ruc_entidad, String entidad,
      String tipo_procedimiento, String orden_electronica, String orden_electronica_generada,
      String estado_orden_electronica, String documento_estado_ocam, String fecha_formalizacion,
      String fecha_ultimo_estado, String sub_total, String igv, String total, String orden_digitalizada,
      String descripcion_estado, String descripcion_cesion_derechos, String acuerdo_marco) {
    this.fecha_proceso = fecha_proceso;
    this.ruc_proveedor = ruc_proveedor;
    this.proveedor = proveedor;
    this.ruc_entidad = ruc_entidad;
    this.entidad = entidad;
    this.tipo_procedimiento = tipo_procedimiento;
    this.orden_electronica = orden_electronica;
    this.orden_electronica_generada = orden_electronica_generada;
    this.estado_orden_electronica = estado_orden_electronica;
    this.documento_estado_ocam = documento_estado_ocam;
    this.fecha_formalizacion = fecha_formalizacion;
    this.fecha_ultimo_estado = fecha_ultimo_estado;
    this.sub_total = sub_total;
    this.igv = igv;
    this.total = total;
    this.orden_digitalizada = orden_digitalizada;
    this.descripcion_estado = descripcion_estado;
    this.descripcion_cesion_derechos = descripcion_cesion_derechos;
    this.acuerdo_marco = acuerdo_marco;
  }
}
