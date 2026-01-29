package model;

import java.time.LocalDate;

public class Solicitud {
    private String idSolicitud;
    private String tipoSolicitante;
    private String tipoActuacion;
    private int anoFinActuacion; 
    private double totalAhorroSolicitado;
    private String comunidadAutonoma;
    private int numeroActuacion; 
    private double ahorroActuacion;
    private String idActuacion;
    private String codigoTitulo;
    private String descripcion; 
    private LocalDate fecha;
    private String estado;
    private String sector;
    
    //constructor     
    public Solicitud(String idSolicitud, String tipoSolicitante, String tipoActuacion, int anoFinActuacion, double totalAhorroSolicitado, String comunidadAutonoma, int numeroActuacion, double ahorroActuacion, String idActuacion, String codigoTitulo, String descripcion, LocalDate fecha, String estado, String sector) {
        this.idSolicitud = idSolicitud;
        this.tipoSolicitante = tipoSolicitante;
        this.tipoActuacion = tipoActuacion;
        this.anoFinActuacion = anoFinActuacion;
        this.totalAhorroSolicitado = totalAhorroSolicitado;
        this.comunidadAutonoma = comunidadAutonoma;
        this.numeroActuacion = numeroActuacion;
        this.ahorroActuacion = ahorroActuacion;
        this.idActuacion = idActuacion;
        this.codigoTitulo = codigoTitulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.sector = sector;
    }   

    //getters y setters
    public String getIdSolicitud() {
        return idSolicitud;
    }
    public void setIdSolicitud(String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
    public String getTipoSolicitante() {
        return tipoSolicitante;
    }
    public void setTipoSolicitante(String tipoSolicitante) {
        this.tipoSolicitante = tipoSolicitante;
    }
    public String getTipoActuacion() {
        return tipoActuacion;
    }
    public void setTipoActuacion(String tipoActuacion) {
        this.tipoActuacion = tipoActuacion;
    }
    public int getAnoFinActuacion() {
        return anoFinActuacion;
    }
    public void setAnoFinActuacion(int anoFinActuacion) {
        this.anoFinActuacion = anoFinActuacion;
    }
    public double getTotalAhorroSolicitado() {
        return totalAhorroSolicitado;
    }
    public void setTotalAhorroSolicitado(double totalAhorroSolicitado) {
        this.totalAhorroSolicitado = totalAhorroSolicitado;
    }
    public String getComunidadAutonoma() {
        return comunidadAutonoma;
    }
    public void setComunidadAutonoma(String comunidadAutonoma) {
        this.comunidadAutonoma = comunidadAutonoma;
    }
    public int getNumeroActuacion() {
        return numeroActuacion;
    }
    public void setNumeroActuacion(int numeroActuacion) {
        this.numeroActuacion = numeroActuacion;
    }
    public double getAhorroActuacion() {
        return ahorroActuacion;
    }
    public void setAhorroActuacion(double ahorroActuacion) {
        this.ahorroActuacion = ahorroActuacion;
    }
    public String getIdActuacion() {
        return idActuacion;
    }
    public void setIdActuacion(String idActuacion) {
        this.idActuacion = idActuacion;
    }
    public String getCodigoTitulo() {
        return codigoTitulo;
    }
    public void setCodigoTitulo(String codigoTitulo) {
        this.codigoTitulo = codigoTitulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getSector() {
        return sector;
    }
    public void setSector(String sector) {
        this.sector = sector;
    }

    @Override
    public String toString() {
        return "Solicitud [idSolicitud=" + idSolicitud + ", tipoSolicitante=" + tipoSolicitante + ", tipoActuacion=" + tipoActuacion + ", anoFinActuacion=" + anoFinActuacion + ", totalAhorroSolicitado=" + totalAhorroSolicitado + ", comunidadAutonoma=" + comunidadAutonoma + ", numeroActuacion=" + numeroActuacion + ", ahorroActuacion=" + ahorroActuacion + ", idActuacion=" + idActuacion + ", codigoTitulo=" + codigoTitulo + ", descripcion=" + descripcion + ", fecha=" + fecha + ", estado=" + estado + ", sector=" + sector + "]";
    }
    
}   